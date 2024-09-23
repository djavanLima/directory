package br.com.github.djavanlima.directory.service.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.github.djavanlima.directory.model.File;
import br.com.github.djavanlima.directory.repository.FileRepository;
import br.com.github.djavanlima.directory.service.builder.FileBuilder;
import br.com.github.djavanlima.directory.service.exception.FileNotFoundException;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FileServiceTest {

    @InjectMocks
    private FileService fileService;

    @Mock
    private FileRepository fileRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<File> files = new ArrayList<>();
        files.add(FileBuilder.oneFile().withId().build());

        when(fileRepository.findAll()).thenReturn(files);

        List<File> result = fileService.findAll();
        assertEquals(files, result);
        verify(fileRepository).findAll();
    }

    @Test
    void testByIdFound() {
        File file = FileBuilder.oneFile().withId().build();

        when(fileRepository.findById(1L)).thenReturn(Optional.of(file));
        File result = fileService.findById(1L);

        assertEquals(file, result);
        verify(fileRepository).findById(1L);

    }

    @Test
    void testFindByIdNotFound() {
        when(fileRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FileNotFoundException.class, () -> {
            fileService.findById(1L);
        });
    }

    @Test
    void testDelete() {
        File file = FileBuilder.oneFile().withId().build();
        when(fileRepository.findById(1L)).thenReturn(Optional.of(file));

        fileService.delete(1L);
        verify(fileRepository).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        when(fileRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FileNotFoundException.class, () -> {
            fileService.delete(1L);
        });
    }

    @Test
    void testUpdate() {
        File oldFile = FileBuilder.oneFile().withId().build();
        when(fileRepository.findById(1L)).thenReturn(Optional.of(oldFile));
        
        File newFile = FileBuilder.otherFile().build();
        
        fileService.update(1L, newFile);
        verify(fileRepository).save(any(File.class));
    }
    
}
