package br.com.github.djavanlima.directory.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.github.djavanlima.directory.controller.dto.DirectoryDTO;
import br.com.github.djavanlima.directory.model.presenter.DirectoryWithFile;
import br.com.github.djavanlima.directory.service.IDirectoryService;



@RestController
@RequestMapping("/directories")
public class DirectoryController {

    @Autowired
    private IDirectoryService directoryService;
    
    @GetMapping
    public ResponseEntity<Set<DirectoryDTO>> findAll() {
        var directories = directoryService.findAll();
        var dto = directories.stream().map(DirectoryDTO::fromDomain).collect(Collectors.toSet());
    
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/parents")
    public ResponseEntity<List<DirectoryDTO>> findParents() {
        var directories = directoryService.findParents();
        var dto = directories.stream().map(DirectoryDTO::fromDomain).collect(Collectors.toList());
    
        return ResponseEntity.ok().body(dto);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<DirectoryDTO> findDirectoryByid(@PathVariable Long id) {
        var directory = directoryService.findById(id);
        var directoryDTO = DirectoryDTO.fromDomain(directory);
        return ResponseEntity.ok().body(directoryDTO);
    }

    @PostMapping
    public ResponseEntity<DirectoryDTO> createDirectory(@RequestBody DirectoryDTO directoryDTO) {
        var directory = directoryDTO.toDomain();
        directoryDTO = DirectoryDTO.fromDomain(directoryService.createDirectory(directory));
       
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(directoryDTO.getIdDirectory()).toUri();
         
        return ResponseEntity.created(uri).body(directoryDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody DirectoryDTO directoryDTO) {
        var directory = directoryDTO.toDomain();
        directoryService.update(id, directory);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="directory-with-file/{id}")
    public ResponseEntity<DirectoryWithFile> findDirectoryWithFileByParentDirectory(@PathVariable Long id) {
        DirectoryWithFile directoryWithFile = directoryService.findDirectoryWithFileByParentDirectory(id);
        return ResponseEntity.ok().body(directoryWithFile);
    }
    
    @GetMapping(value="directory-with-file/hierarchy")
    public ResponseEntity<List<DirectoryWithFile>> findDirectoryWithFileByHierarchy() {
        List<DirectoryWithFile> directoriesWithFiles = directoryService.findDirectoriesWithFiles();
        return ResponseEntity.ok().body(directoriesWithFiles);
    }

}
