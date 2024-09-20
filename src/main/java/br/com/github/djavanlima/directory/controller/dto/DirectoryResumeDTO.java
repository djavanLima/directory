package br.com.github.djavanlima.directory.controller.dto;

import java.util.HashSet;

import br.com.github.djavanlima.directory.model.Directory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectoryResumeDTO {
    
    private Long idDirectory;

    private String name;

    private Long idParentDirectory;

    public Directory toDomain() {
        Directory parentDirectory = new Directory();
        parentDirectory.setIdDirectory(this.idDirectory);
        return new Directory(
            this.idDirectory,
            this.name,
            parentDirectory,
            new HashSet<>()
        );
    }
}
