package com.example.ativ3.dtos;

import java.util.List;

public class DadosSetorDTO {

    private Integer id;
    private String nome;
    private List<FuncionarioDTO> funcionarios;

    public DadosSetorDTO() {
    }

    public DadosSetorDTO(Integer id, String nome,
                         List<FuncionarioDTO> funcionarios) {
        this.id = id;
        this.nome = nome;
        this.funcionarios = funcionarios;
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

    public List<FuncionarioDTO> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(
            List<FuncionarioDTO> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
