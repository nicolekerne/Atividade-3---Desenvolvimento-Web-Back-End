
package com.example.ativ3.services;

import com.example.ativ3.dtos.DadosProjetoDTO;
import com.example.ativ3.dtos.FuncionarioDTO;
import com.example.ativ3.exceptions.RegraNegocioException;
import com.example.ativ3.models.Funcionario;
import com.example.ativ3.models.Projeto;
import com.example.ativ3.models.Setor;
import com.example.ativ3.repositories.FuncionarioRepository;
import com.example.ativ3.repositories.SetorRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    public FuncionarioServiceImpl(
            FuncionarioRepository funcionarioRepository,
            SetorRepository setorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
    }

    @Override
    @Transactional
    public void adicionar(FuncionarioDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RegraNegocioException(
                "O nome do funcionário é obrigatório."
            );
        }

        if (dto.getSetorId() == null) {
            throw new RegraNegocioException(
                "O ID do setor é obrigatório."
            );
        }

        Setor setor = setorRepository.findById(dto.getSetorId())
            .orElseThrow(() ->
                new RegraNegocioException("Setor não encontrado.")
            );

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome().trim());
        funcionario.setSetor(setor);

        funcionarioRepository.save(funcionario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarProjetos(
            Integer idFuncionario) {

        if (!funcionarioRepository.existsById(idFuncionario)) {
            throw new RegraNegocioException(
                "Funcionário não encontrado."
            );
        }

        return funcionarioRepository.buscarProjetos(idFuncionario)
            .stream()
            .map(this::converterProjeto)
            .toList();
    }

    private DadosProjetoDTO converterProjeto(Projeto projeto) {
        return new DadosProjetoDTO(
            projeto.getId(),
            projeto.getDescricao(),
            projeto.getDataInicio(),
            projeto.getDataFim(),
            projeto.getFuncionarios().stream()
                .map(f -> new FuncionarioDTO(
                    f.getId(),
                    f.getNome(),
                    f.getSetor().getId()
                ))
                .toList()
        );
    }
}
