package com.example.ativ3.exceptions;

public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(String mensagemErro) {
        super(mensagemErro);
    }
}
