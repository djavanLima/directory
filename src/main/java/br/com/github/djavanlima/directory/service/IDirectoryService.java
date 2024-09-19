package br.com.github.djavanlima.directory.service;

import java.util.Set;

import br.com.github.djavanlima.directory.model.Directory;

public interface IDirectoryService {

    public Set<Directory> findAll(); 
    
}
