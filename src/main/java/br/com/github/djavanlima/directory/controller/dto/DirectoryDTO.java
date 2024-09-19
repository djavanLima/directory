package br.com.github.djavanlima.directory.controller.dto;

import java.util.Set;

import br.com.github.djavanlima.directory.model.Directory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectoryDTO {

    private Long idDirectory;

    private String name;

    private Directory parentDirectory;

    private Set<Directory> subDirectories;

    public static DirectoryDTO fromDomain(Directory directory) {
        DirectoryDTO dto = new DirectoryDTO();

        dto.setIdDirectory(directory.getIdDirectory());
        dto.setName(directory.getName());
        dto.setParentDirectory(directory.getParentDirectory());
        dto.setSubDirectories(directory.getSubDirectories());
 
        return dto;
    }

    public Directory toDomain() {
        return new Directory(
            this.idDirectory,
            this.name,
            this.parentDirectory,
            this.subDirectories
        );
    }
}
