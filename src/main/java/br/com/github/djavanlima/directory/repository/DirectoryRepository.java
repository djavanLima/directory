package br.com.github.djavanlima.directory.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.github.djavanlima.directory.model.Directory;

public interface DirectoryRepository extends JpaRepository<Directory, Long> {

    @Query(value = "select distinct d from Directory d")
    public Set<Directory> findAllDirectories();
}
