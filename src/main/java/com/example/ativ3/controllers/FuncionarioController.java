package com.example.ativ3.controllers;

import com.example.ativ3.dtos.FuncionarioDTO;
import com.example.ativ3.dtos.DadosProjetoDTO;
import com.example.ativ3.services.FuncionarioService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "http://localhost:5173")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(
            FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody FuncionarioDTO funcionario) {
        funcionarioService.adicionar(funcionario);
    }

    @GetMapping("/{idFuncionario}/projetos")
    public List<DadosProjetoDTO> buscarProjetos(
            @PathVariable Integer idFuncionario) {
        return funcionarioService.buscarProjetos(idFuncionario);
    }
}

