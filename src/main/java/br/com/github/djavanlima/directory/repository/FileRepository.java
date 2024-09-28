package br.com.github.djavanlima.directory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.github.djavanlima.directory.model.File;

public interface FileRepository extends JpaRepository<File, Long> {
    
    @Query(value= "SELECT DISTINCT f FROM File f WHERE f.directoryOfFile.idDirectory = :id")
    public List<File> findByDirectorId(@Param("id") Long id);

    @Query(value= "delete from File f where f.directoryOfFile.idDirectory = :idDirectory ")
    public void deleteByParentDirectory(@Param("idDirectory") Long idDirectory);
}
