package br.com.github.djavanlima.directory.service.builder;


import br.com.github.djavanlima.directory.model.File;

public class FileBuilder {

    private File file;

    public static FileBuilder oneFile() {
        FileBuilder fileBuilder = new FileBuilder();
        fileBuilder.file = new  File(null, "file 1", null );
        return fileBuilder;
    }

    public static FileBuilder otherFile() {
        FileBuilder fileBuilder = new FileBuilder();
        fileBuilder.file = new  File(null, "File 2", null );
        return fileBuilder;
    }

    public FileBuilder withId() {
        file.setIdFile(1L);
        return this;
    }

    public FileBuilder withOtherId() {
        file.setIdFile(2L);
        return this;
    }

    public FileBuilder withOtherIdOne() {
        file.setIdFile(3L);
        return this;
    }

    public FileBuilder withOtherIdTwo() {
        file.setIdFile(4L);
        return this;
    }

    public File build() {
        return this.file;
    }

}
