package com.example.ativ3.services;

import com.example.ativ3.dtos.SetorDTO;
import com.example.ativ3.dtos.DadosSetorDTO;

public interface SetorService {

    void adicionar(SetorDTO dto);

    DadosSetorDTO buscarSetorPorId(Integer idSetor);
}
