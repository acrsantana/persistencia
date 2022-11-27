package br.com.infnet.persistencia.exceptions;

public class ClienteInexistenteException extends RuntimeException{
    public ClienteInexistenteException(String message) {
        super(message);
    }

    public ClienteInexistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
