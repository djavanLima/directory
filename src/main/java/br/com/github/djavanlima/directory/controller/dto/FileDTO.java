package br.com.github.djavanlima.directory.controller.dto;

import br.com.github.djavanlima.directory.model.Directory;
import br.com.github.djavanlima.directory.model.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    
    private Long idFile;

    private String name;

    private Directory directoryOfFile;

    public static FileDTO fromDomain(File file) {
        FileDTO dto = new FileDTO();

        dto.setIdFile(file.getIdFile());
        dto.setName(file.getName());
        dto.setDirectoryOfFile(file.getDirectoryOfFile());
 
        return dto;
    }

    public File toDomain() {
        return new File(
            this.idFile,
            this.name,
            this.directoryOfFile
        );
    }
}
