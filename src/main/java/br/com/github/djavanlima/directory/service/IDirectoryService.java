package br.com.github.djavanlima.directory.service;

import java.util.Set;

import br.com.github.djavanlima.directory.model.Directory;

public interface IDirectoryService {

    public Set<Directory> findAll();

    public Directory findById(Long id);

    public Directory createDirectory(Directory directory); 

    public void delete(Long id);

    public void update(Long id, Directory directory);
    
}
