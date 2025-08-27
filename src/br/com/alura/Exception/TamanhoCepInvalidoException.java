package br.com.alura.Exception;

public class TamanhoCepInvalidoException extends RuntimeException {
    private String message;

    public TamanhoCepInvalidoException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
