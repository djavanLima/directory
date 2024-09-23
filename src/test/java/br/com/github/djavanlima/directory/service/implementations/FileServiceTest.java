package br.com.github.djavanlima.directory.service.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
    public void testFindAll() {
        List<File> files = new ArrayList<>();
        files.add(FileBuilder.oneFile().withId().build());

        when(fileRepository.findAll()).thenReturn(files);

        List<File> result = fileService.findAll();
        assertEquals(files, result);
        verify(fileRepository).findAll();
    }
    
}
