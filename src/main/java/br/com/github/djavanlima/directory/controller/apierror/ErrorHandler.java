package br.com.github.djavanlima.directory.controller.apierror;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.github.djavanlima.directory.service.exception.DirectoryNotFoundException;
import br.com.github.djavanlima.directory.service.exception.FileNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
    
    @ExceptionHandler({
		DirectoryNotFoundException.class,
		FileNotFoundException.class
	})
    public ResponseEntity<List<String>> handleNotFoundException(RuntimeException ex) {
		List<String> mensagem = new ArrayList<>();
		mensagem.add(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
	}
}
