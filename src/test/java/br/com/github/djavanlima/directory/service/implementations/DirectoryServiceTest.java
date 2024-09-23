package br.com.github.djavanlima.directory.service.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.github.djavanlima.directory.model.Directory;
import br.com.github.djavanlima.directory.repository.DirectoryRepository;
import br.com.github.djavanlima.directory.service.builder.DirectoryBuilder;
import br.com.github.djavanlima.directory.service.exception.DirectoryNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DirectoryServiceTest {
    
    @InjectMocks
    private DirectoryService directoryService;

    @Mock
    private DirectoryRepository directoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Set<Directory> directories = new HashSet<>();
        directories.add(DirectoryBuilder.oneDirectory().withId().build());

        when(directoryRepository.findAllDirectories()).thenReturn(directories);

        Set<Directory> result = directoryService.findAll();
        assertEquals(1, result.size());
        verify(directoryRepository).findAllDirectories();
    }

    @Test
    void testFindByIdFound() {
        Directory directory = DirectoryBuilder.oneDirectory().withId().build();
        when(directoryRepository.findById(1L)).thenReturn(Optional.of(directory));

        Directory result = directoryService.findById(1L);
        assertEquals(directory, result);
        verify(directoryRepository).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(directoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DirectoryNotFoundException.class, () -> {
            directoryService.findById(1L);
        });
    }

    @Test
    void testCreateDirectory() {
        Directory directory = DirectoryBuilder.oneDirectory().withId().build();
        when(directoryRepository.save(directory)).thenReturn(directory);

        Directory result = directoryService.createDirectory(directory);
        assertEquals(directory, result);
        verify(directoryRepository).save(directory);
    }

    @Test
    void testDelete() {
        Directory directory = DirectoryBuilder.oneDirectory().withId().build();
        when(directoryRepository.findById(1L)).thenReturn(Optional.of(directory));

        directoryService.delete(1L);
        verify(directoryRepository).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        when(directoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DirectoryNotFoundException.class, () -> {
            directoryService.delete(1L);
        });
    }

    @Test
    void testUpdate() {
        Directory oldDirectory = DirectoryBuilder.oneDirectory().withId().build();
        when(directoryRepository.findById(1L)).thenReturn(Optional.of(oldDirectory));
        
        Directory newDirectory = DirectoryBuilder.otherDirectory().build();
        
        directoryService.update(1L, newDirectory);
        verify(directoryRepository).save(any(Directory.class));
    }
}
