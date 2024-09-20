package br.com.github.djavanlima.directory.controller;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.github.djavanlima.directory.controller.dto.DirectoryDTO;
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

    @PostMapping
    public ResponseEntity<DirectoryDTO> createDirectory(@RequestBody DirectoryDTO directoryDTO) {
        var directory = directoryDTO.toDomain();
        directoryDTO = DirectoryDTO.fromDomain(directoryService.createDirectory(directory));
       
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(directoryDTO.getIdDirectory()).toUri();
         
        return ResponseEntity.created(uri).body(directoryDTO);
    }

}
