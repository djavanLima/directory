package br.com.github.djavanlima.directory.model.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.github.djavanlima.directory.model.Directory;
import br.com.github.djavanlima.directory.model.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectoryWithFile {
    private Long idDirectory;

    private String name;

    private Long parentId;

    private List<File> files = new ArrayList<>();

    private List<DirectoryWithFile> subDirectories = new ArrayList<>();

    public void changeState(Directory directory){
        setName(directory.getName()); 
    }

    public DirectoryWithFile(Long id, String name, Directory parentDirectory, List<File> files, List<DirectoryWithFile> subdirectories) {
        this.idDirectory = id;
        this.name = name;
        this.parentId = parentDirectory == null? null : parentDirectory.getIdDirectory();
        this.files = files;
        this.subDirectories = subdirectories;
    }
}
