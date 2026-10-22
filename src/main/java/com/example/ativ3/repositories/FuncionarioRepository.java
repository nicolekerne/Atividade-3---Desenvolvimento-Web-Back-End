package com.example.ativ3.repositories;

import com.example.ativ3.models.Funcionario;
import com.example.ativ3.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioRepository
        extends JpaRepository<Funcionario, Integer> {

    @Query("""
        SELECT DISTINCT p
        FROM Projeto p
        JOIN p.funcionarios f
        WHERE f.id = :idFuncionario
    """)
    List<Projeto> buscarProjetos(
        @Param("idFuncionario") Integer idFuncionario
    );
}
