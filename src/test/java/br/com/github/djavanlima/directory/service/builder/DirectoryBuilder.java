package br.com.github.djavanlima.directory.service.builder;


import java.util.HashSet;

import br.com.github.djavanlima.directory.model.Directory;

public class DirectoryBuilder {

    private Directory directory;

    public static DirectoryBuilder oneDirectory() {
        DirectoryBuilder directoryBuilder = new DirectoryBuilder();
        directoryBuilder.directory = new Directory(null, "Root 1", null, new HashSet<>());
        return directoryBuilder;
    }

    public static DirectoryBuilder otherDirectory() {
        DirectoryBuilder directoryBuilder = new DirectoryBuilder();
        directoryBuilder.directory = new Directory(null, "Root 2", null, new HashSet<>());
        return directoryBuilder;
    }

    public static DirectoryBuilder sonDirectory() {
        DirectoryBuilder directoryBuilder = new DirectoryBuilder();
        directoryBuilder.directory.setName("subdirectory 1");
        return directoryBuilder;
    }

    public static DirectoryBuilder otherSonDirectory() {
        DirectoryBuilder directoryBuilder = new DirectoryBuilder();
        directoryBuilder.directory.setName("subdirectory 2");
        return directoryBuilder;
    }

    public DirectoryBuilder withId() {
        directory.setIdDirectory(1L);
        return this;
    }

    public DirectoryBuilder withOtherId() {
        directory.setIdDirectory(2L);
        return this;
    }

    public DirectoryBuilder withOtherIdOne() {
        directory.setIdDirectory(3L);
        return this;
    }

    public DirectoryBuilder withOtherIdTwo() {
        directory.setIdDirectory(4L);
        return this;
    }

    public Directory build() {
        return this.directory;
    }

}
