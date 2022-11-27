package br.com.infnet.persistencia.exceptions;

public class BancoDeDadosIndisponivelException extends RuntimeException{
    public BancoDeDadosIndisponivelException(String message) {
        super(message);
    }

    public BancoDeDadosIndisponivelException(String message, Throwable cause) {
        super(message, cause);
    }
}
