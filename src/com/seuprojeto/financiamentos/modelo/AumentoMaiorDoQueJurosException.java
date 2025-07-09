package com.seuprojeto.financiamentos.modelo;

import java.io.Serializable;

public class AumentoMaiorDoQueJurosException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    public AumentoMaiorDoQueJurosException(String message) {
        super(message);
    }
}