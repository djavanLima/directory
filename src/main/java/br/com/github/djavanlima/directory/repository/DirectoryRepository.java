package br.com.github.djavanlima.directory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.djavanlima.directory.model.Directory;

public interface DirectoryRepository extends JpaRepository<Directory, Long> {
    
}
