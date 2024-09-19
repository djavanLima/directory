package br.com.github.djavanlima.directory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.djavanlima.directory.model.File;

public interface FileRepository extends JpaRepository<File, Long> {
    
}
