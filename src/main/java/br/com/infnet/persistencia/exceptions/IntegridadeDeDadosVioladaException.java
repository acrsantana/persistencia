package br.com.infnet.persistencia.exceptions;

public class IntegridadeDeDadosVioladaException extends RuntimeException{
    public IntegridadeDeDadosVioladaException(String message) {
        super(message);
    }

    public IntegridadeDeDadosVioladaException(String message, Throwable cause) {
        super(message, cause);
    }
}
