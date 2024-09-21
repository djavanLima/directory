package br.com.github.djavanlima.directory.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.github.djavanlima.directory.model.File;
import br.com.github.djavanlima.directory.repository.FileRepository;
import br.com.github.djavanlima.directory.service.IFileService;
import br.com.github.djavanlima.directory.service.exception.FileNotFoundException;

@Service
public class FileService implements IFileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public List<File> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public File findById(Long id) {
        return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public File createFile(File file) {
       return fileRepository.save(file);
    }

    @Override
    public void delete(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public void update(Long id, File file) {
        findById(id);
        fileRepository.save(file);
    }
}
