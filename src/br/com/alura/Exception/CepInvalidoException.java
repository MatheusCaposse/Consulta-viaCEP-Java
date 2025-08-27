package br.com.alura.Exception;

public class CepInvalidoException extends RuntimeException {

  private String message;

  public CepInvalidoException(String message) {
    this.message = message;
  }

  public String getMessage(){
    return message;
  }
}
