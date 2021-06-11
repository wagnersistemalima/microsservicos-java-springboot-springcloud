package br.com.wagner.trabalhador.exceptions;

public class ResourceNotFoundException extends  RuntimeException{

    // construtor

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
