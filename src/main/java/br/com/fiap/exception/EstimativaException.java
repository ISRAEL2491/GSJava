package br.com.fiap.exception;

public class EstimativaException extends Exception {

    // Construtor sem parâmetros
    public EstimativaException() {
        super("Erro relacionado à Estimativa.");
    }

    // Construtor com mensagem personalizada
    public EstimativaException(String message) {
        super(message);
    }

    // Construtor com mensagem personalizada e causa (exceção original)
    public EstimativaException(String message, Throwable cause) {
        super(message, cause);
    }

    // Construtor com a causa original
    public EstimativaException(Throwable cause) {
        super(cause);
    }
}
