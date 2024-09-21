package br.com.github.djavanlima.directory.controller;

import java.net.URI;
import java.util.List;
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

import br.com.github.djavanlima.directory.controller.dto.FileDTO;
import br.com.github.djavanlima.directory.service.IFileService;

@RequestMapping("/files")
@RestController
public class FileController {

    @Autowired
    private IFileService fileService;
    
    @GetMapping
    public ResponseEntity<List<FileDTO>> findAll() {
        var files = fileService.findAll();
        var dto = files.stream().map(FileDTO::fromDomain).collect(Collectors.toList());
    
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDTO> findFileById(@PathVariable Long id) {
        var directory = fileService.findById(id);
        var fileDTO = FileDTO.fromDomain(directory);
        return ResponseEntity.ok().body(fileDTO);
    }

    @PostMapping
    public ResponseEntity<FileDTO> createFile(@RequestBody FileDTO fileDTO) {
        var file = fileDTO.toDomain();
        fileDTO = FileDTO.fromDomain(fileService.createFile(file));
       
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(fileDTO.getIdFile()).toUri();
         
        return ResponseEntity.created(uri).body(fileDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fileService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody FileDTO fileDTO) {
        var file = fileDTO.toDomain();
        fileService.update(id, file);
        return ResponseEntity.noContent().build();
    }
}
