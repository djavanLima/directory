package br.com.github.djavanlima.directory.service;

import java.util.List;

import br.com.github.djavanlima.directory.model.File;

public interface IFileService {

    public List<File> findAll();

    public File findById(Long id);

    public File createFile(File file); 

    public void delete(Long id);

    public void update(Long id, File file);

    public List<File> findByDirectorId(Long id);

    public void deleteByParentDirectory(Long idDirectory);

}
