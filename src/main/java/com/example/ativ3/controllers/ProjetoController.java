package com.example.ativ3.controllers;

import com.example.ativ3.dtos.ProjetoDTO;
import com.example.ativ3.dtos.DadosProjetoDTO;
import com.example.ativ3.services.ProjetoService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/projetos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody ProjetoDTO projeto) {
        projetoService.adicionar(projeto);
    }

    @GetMapping("/{id}")
    public DadosProjetoDTO buscarProjetoPorId(
            @PathVariable Integer id) {
        return projetoService.buscarProjetoPorId(id);
    }

    @PostMapping("/{idProjeto}/funcionarios/{idFuncionario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincularFuncionario(
            @PathVariable Integer idProjeto,
            @PathVariable Integer idFuncionario) {
        projetoService.vincularFuncionario(
            idProjeto, idFuncionario
        );
    }

    @GetMapping
    public List<DadosProjetoDTO> listarTodos() {
        return projetoService.listarTodos();
    }

    @GetMapping("/periodo")
    public List<DadosProjetoDTO> buscarPorPeriodo(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate inicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fim) {
        return projetoService.buscarPorPeriodo(inicio, fim);
    }
}
