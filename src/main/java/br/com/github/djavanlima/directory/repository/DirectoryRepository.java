package br.com.github.djavanlima.directory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.github.djavanlima.directory.model.Directory;

public interface DirectoryRepository extends JpaRepository<Directory, Long> {

    @Query(value = "select distinct d from Directory d")
    public List<Directory> findAllDirectories();

    @Query(value = "SELECT d FROM Directory d where d.parentDirectory is null order by d.name asc ")
    public List<Directory> findParents();

    @Query(value="SELECT d from Directory d where d.parentDirectory.idDirectory = :directoryId")
    public List<Directory> findSubDirectorysByParent(@Param("directoryId") Long directoryId);
    
    @Query(value="SELECT d from Directory d where d.parentDirectory <> null ")
    public List<Directory> findOnlySubdirectories();
}
