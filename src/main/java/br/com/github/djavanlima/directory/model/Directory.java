package br.com.github.djavanlima.directory.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import br.com.github.djavanlima.directory.model.presenter.DirectoryWithFile;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Directory {
    @Id
    @Column(name = "id_directory", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDirectory;

    @Column(unique = true)
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parent_directory")
    private Directory parentDirectory;

    @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL)
    private Set<Directory> subDirectories = new HashSet<>();

    public void changeState(Directory directory){
        setName(directory.getName()); 
    }

    public DirectoryWithFile toDirectoryWithFile() {
        return new DirectoryWithFile(this.idDirectory, this.name, getParentDirectory(),  new ArrayList<>(),  new ArrayList<>());
    }
}
