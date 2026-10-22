package com.example.ativ3.dtos;

public class FuncionarioDTO {

    private Integer id;
    private String nome;
    private Integer setorId;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Integer id, String nome, Integer setorId) {
        this.id = id;
        this.nome = nome;
        this.setorId = setorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSetorId() {
        return setorId;
    }

    public void setSetorId(Integer setorId) {
        this.setorId = setorId;
    }
}

