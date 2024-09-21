package br.com.github.djavanlima.directory.service.exception;


public class DirectoryNotFoundException extends RuntimeException {
    
    public DirectoryNotFoundException() {
        super("Diretório não encontrado");
    }
}
