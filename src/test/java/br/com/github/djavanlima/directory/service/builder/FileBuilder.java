package br.com.github.djavanlima.directory.service.builder;


import br.com.github.djavanlima.directory.model.File;

public class FileBuilder {

    private File file;

    public static FileBuilder oneDirectory() {
        FileBuilder fileBuilder = new FileBuilder();
        fileBuilder.file.setName("Root");
        return fileBuilder;
    }

    public static FileBuilder otherDirectory() {
        FileBuilder FileBuilder = new FileBuilder();
        FileBuilder.file.setName("Root 2");
        return FileBuilder;
    }

    public static FileBuilder sonDirectory() {
        FileBuilder FileBuilder = new FileBuilder();
        FileBuilder.file.setName("subdirectory 1");
        return FileBuilder;
    }

    public static FileBuilder otherSonDirectory() {
        FileBuilder FileBuilder = new FileBuilder();
        FileBuilder.file.setName("subdirectory 2");
        return FileBuilder;
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
