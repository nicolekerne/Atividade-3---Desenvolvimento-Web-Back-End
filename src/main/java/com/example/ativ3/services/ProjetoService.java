package com.example.ativ3.services;

import com.example.ativ3.dtos.ProjetoDTO;
import com.example.ativ3.dtos.DadosProjetoDTO;

import java.time.LocalDate;
import java.util.List;

public interface ProjetoService {

    void adicionar(ProjetoDTO dto);

    DadosProjetoDTO buscarProjetoPorId(Integer id);

    void vincularFuncionario(
        Integer idProjeto, Integer idFuncionario
    );

    List<DadosProjetoDTO> listarTodos();

    List<DadosProjetoDTO> buscarPorPeriodo(
        LocalDate inicio, LocalDate fim
    );
}
