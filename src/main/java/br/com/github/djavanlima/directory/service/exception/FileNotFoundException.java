package br.com.github.djavanlima.directory.service.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException() {
        super("Arquivo não encontrado");
    }
}
