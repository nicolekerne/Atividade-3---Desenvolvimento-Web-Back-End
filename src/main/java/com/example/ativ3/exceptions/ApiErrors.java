package com.example.ativ3.exceptions;

import java.util.List;

public class ApiErrors {

    private List<String> errors;

    public ApiErrors(String mensagemErro) {
        this.errors = List.of(mensagemErro);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
