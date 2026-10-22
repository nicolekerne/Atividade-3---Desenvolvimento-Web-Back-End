
package com.example.ativ3.services;

import com.example.ativ3.dtos.DadosProjetoDTO;
import com.example.ativ3.dtos.FuncionarioDTO;
import com.example.ativ3.dtos.ProjetoDTO;
import com.example.ativ3.exceptions.RegraNegocioException;
import com.example.ativ3.models.Funcionario;
import com.example.ativ3.models.Projeto;
import com.example.ativ3.repositories.FuncionarioRepository;
import com.example.ativ3.repositories.ProjetoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoServiceImpl(
            ProjetoRepository projetoRepository,
            FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    @Transactional
    public void adicionar(ProjetoDTO dto) {
        validarDatas(dto.getDataInicio(), dto.getDataFim());

        if (dto.getDescricao() == null
                || dto.getDescricao().isBlank()) {
            throw new RegraNegocioException(
                "A descrição do projeto é obrigatória."
            );
        }

        Projeto projeto = new Projeto();
        projeto.setDescricao(dto.getDescricao().trim());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setDataFim(dto.getDataFim());

        projetoRepository.save(projeto);
    }

    @Override
    @Transactional(readOnly = true)
    public DadosProjetoDTO buscarProjetoPorId(Integer id) {
        Projeto projeto = projetoRepository
            .buscarComFuncionarios(id)
            .orElseThrow(() ->
                new RegraNegocioException("Projeto não encontrado.")
            );

        return converter(projeto);
    }

    @Override
    @Transactional
    public void vincularFuncionario(
            Integer idProjeto, Integer idFuncionario) {

        Projeto projeto = projetoRepository.findById(idProjeto)
            .orElseThrow(() ->
                new RegraNegocioException("Projeto não encontrado.")
            );

        Funcionario funcionario = funcionarioRepository
            .findById(idFuncionario)
            .orElseThrow(() ->
                new RegraNegocioException("Funcionário não encontrado.")
            );

        boolean jaVinculado = projeto.getFuncionarios().stream()
            .anyMatch(f -> f.getId().equals(idFuncionario));

        if (jaVinculado) {
            throw new RegraNegocioException(
                "Funcionário já vinculado a este projeto."
            );
        }

        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> listarTodos() {
        return projetoRepository.findAll()
            .stream()
            .map(this::converter)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarPorPeriodo(
            LocalDate inicio, LocalDate fim) {

        validarDatas(inicio, fim);

        return projetoRepository
            .findByDataInicioBetween(inicio, fim)
            .stream()
            .map(this::converter)
            .toList();
    }

    private void validarDatas(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            throw new RegraNegocioException(
                "As datas de início e fim são obrigatórias."
            );
        }

        if (fim.isBefore(inicio)) {
            throw new RegraNegocioException(
                "A data final não pode ser anterior à data inicial."
            );
        }
    }

    private DadosProjetoDTO converter(Projeto projeto) {
        List<FuncionarioDTO> funcionarios = projeto.getFuncionarios()
            .stream()
            .map(f -> new FuncionarioDTO(
                f.getId(),
                f.getNome(),
                f.getSetor().getId()
            ))
            .toList();

        return new DadosProjetoDTO(
            projeto.getId(),
            projeto.getDescricao(),
            projeto.getDataInicio(),
            projeto.getDataFim(),
            funcionarios
        );
    }
}
