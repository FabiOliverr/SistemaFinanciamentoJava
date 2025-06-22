package com.seuprojeto.financiamentos.modelo;

public class AumentoMaiorDoQueJurosException extends RuntimeException {
    public AumentoMaiorDoQueJurosException(String message) {
        super(message);
    }
}