package com.example.ativ3.services;

import com.example.ativ3.dtos.FuncionarioDTO;
import com.example.ativ3.dtos.DadosProjetoDTO;

import java.util.List;

public interface FuncionarioService {

    void adicionar(FuncionarioDTO dto);

    List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario);
}
