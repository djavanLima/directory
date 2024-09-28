package br.com.github.djavanlima.directory.service.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.github.djavanlima.directory.model.Directory;
import br.com.github.djavanlima.directory.model.presenter.DirectoryWithFile;
import br.com.github.djavanlima.directory.repository.DirectoryRepository;
import br.com.github.djavanlima.directory.service.IDirectoryService;
import br.com.github.djavanlima.directory.service.IFileService;
import br.com.github.djavanlima.directory.service.exception.DirectoryNotFoundException;

@Service
public class DirectoryService implements IDirectoryService {

    @Autowired
    private DirectoryRepository directoryRepository;

    @Autowired
    private IFileService fileService;

    @Override
    public List<Directory> findAll() {
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
        if(!fileService.findByDirectorId(id).isEmpty()){
            fileService.delete(id);
        }
        directoryRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Directory directory) {
        var oldDirectory = findById(id);
        oldDirectory.changeState(directory);
        directoryRepository.save(oldDirectory);
    }

    @Override
    public List<DirectoryWithFile> findDirectoriesWithFiles() {
        var direcotories = findParents();
        List<DirectoryWithFile> directoryWithFiles = new ArrayList<>();

        if(direcotories.isEmpty()) {
            return directoryWithFiles;
        }

        for(Directory directory: direcotories) {
            directoryWithFiles.add(createHierarchy(directory.toDirectoryWithFile()));
        }

        return directoryWithFiles; 
    }

    @Override
    public List<Directory> findParents() {
        return directoryRepository.findParents();
    }

    @Override
    public DirectoryWithFile findDirectoryWithFileByParentDirectory(Long id) {
        var root = findById(id);
        var directoryWithFile = root.toDirectoryWithFile();
     
        return createHierarchy(directoryWithFile);
    }

    private DirectoryWithFile createHierarchy(DirectoryWithFile directoryWithFile) {
        if(directoryWithFile.getIdDirectory() == null) {
            return directoryWithFile;
        }

        List<Directory> childsDirectory = findChildsById(directoryWithFile.getIdDirectory());
        List<DirectoryWithFile> subDirectoryWithFiles = new ArrayList<>();

        if(childsDirectory.isEmpty()) {
            return directoryWithFile;
        }

        directoryWithFile.setFiles(fileService.findByDirectorId(directoryWithFile.getIdDirectory()));
        
        for(Directory subdirectory : childsDirectory) {
            subDirectoryWithFiles.add(createHierarchy(subdirectory.toDirectoryWithFile()));
        }
        
        directoryWithFile.setSubDirectories(subDirectoryWithFiles);

        return directoryWithFile;
    }

    public List<Directory> findChildsById(Long parentId) {
      return  directoryRepository.findSubDirectorysByParent(parentId);
    }
}
