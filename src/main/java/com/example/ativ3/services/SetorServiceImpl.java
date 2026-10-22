package com.example.ativ3.services;

import com.example.ativ3.dtos.DadosSetorDTO;
import com.example.ativ3.dtos.FuncionarioDTO;
import com.example.ativ3.dtos.SetorDTO;
import com.example.ativ3.exceptions.RegraNegocioException;
import com.example.ativ3.models.Setor;
import com.example.ativ3.repositories.SetorRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;

    public SetorServiceImpl(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Override
    @Transactional
    public void adicionar(SetorDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RegraNegocioException(
                "O nome do setor é obrigatório."
            );
        }

        Setor setor = new Setor();
        setor.setNome(dto.getNome().trim());

        setorRepository.save(setor);
    }

    @Override
    @Transactional(readOnly = true)
    public DadosSetorDTO buscarSetorPorId(Integer idSetor) {
        Setor setor = setorRepository.buscarComFuncionarios(idSetor)
            .orElseThrow(() ->
                new RegraNegocioException("Setor não encontrado.")
            );

        return new DadosSetorDTO(
            setor.getId(),
            setor.getNome(),
            setor.getFuncionarios().stream()
                .map(f -> new FuncionarioDTO(
                    f.getId(),
                    f.getNome(),
                    setor.getId()
                ))
                .toList()
        );
    }
}
