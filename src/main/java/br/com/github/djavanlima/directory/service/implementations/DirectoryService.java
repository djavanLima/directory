package br.com.github.djavanlima.directory.service.implementations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.github.djavanlima.directory.model.Directory;
import br.com.github.djavanlima.directory.repository.DirectoryRepository;
import br.com.github.djavanlima.directory.service.IDirectoryService;

@Service
public class DirectoryService implements IDirectoryService {

    @Autowired
    private DirectoryRepository directoryRepository;

    @Override
    public Set<Directory> findAll() {
        return directoryRepository.findAllDirectories();
    }
    
}
