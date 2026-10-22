package com.example.ativ3.controllers;

import com.example.ativ3.dtos.SetorDTO;
import com.example.ativ3.dtos.DadosSetorDTO;
import com.example.ativ3.services.SetorService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/setores")
@CrossOrigin(origins = "http://localhost:5173")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody SetorDTO setor) {
        setorService.adicionar(setor);
    }

    @GetMapping("/{idSetor}")
    public DadosSetorDTO buscarSetorPorId(
            @PathVariable Integer idSetor) {
        return setorService.buscarSetorPorId(idSetor);
    }
}
