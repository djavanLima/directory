package br.com.github.djavanlima.directory.service;

import java.util.List;

import br.com.github.djavanlima.directory.model.Directory;
import br.com.github.djavanlima.directory.model.presenter.DirectoryWithFile;

public interface IDirectoryService {

    public List<Directory> findAll();

    public Directory findById(Long id);

    public Directory createDirectory(Directory directory); 

    public void delete(Long id);

    public void update(Long id, Directory directory);

    public List<Directory> findParents();
    
    public DirectoryWithFile findDirectoryWithFileByParentDirectory(Long id);

    public List<DirectoryWithFile> findDirectoriesWithFiles();
}
