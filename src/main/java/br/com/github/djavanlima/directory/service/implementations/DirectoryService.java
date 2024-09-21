package br.com.github.djavanlima.directory.service.implementations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.github.djavanlima.directory.model.Directory;
import br.com.github.djavanlima.directory.repository.DirectoryRepository;
import br.com.github.djavanlima.directory.service.IDirectoryService;
import br.com.github.djavanlima.directory.service.exception.DirectoryNotFoundException;

@Service
public class DirectoryService implements IDirectoryService {

    @Autowired
    private DirectoryRepository directoryRepository;

    @Override
    public Set<Directory> findAll() {
        return directoryRepository.findAllDirectories();
    }

    @Override
    public Directory findById(Long id) {
        return directoryRepository.findById(id).orElseThrow(DirectoryNotFoundException::new);
    }

    @Override
    @Transactional
    public Directory createDirectory(Directory directory) {
        return directoryRepository.save(directory); 
    }

    @Override
    public void delete(Long id) {
        findById(id);
        directoryRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Directory directory) {
        var oldDirectory = findById(id);
        oldDirectory.changeState(directory);
        directoryRepository.save(oldDirectory);
    }
}
