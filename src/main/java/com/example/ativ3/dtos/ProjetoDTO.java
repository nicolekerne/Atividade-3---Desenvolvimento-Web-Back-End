package com.example.ativ3.dtos;

import java.time.LocalDate;

public class ProjetoDTO {

    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public ProjetoDTO() {
    }

    public ProjetoDTO(String descricao, LocalDate dataInicio,
                      LocalDate dataFim) {
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
