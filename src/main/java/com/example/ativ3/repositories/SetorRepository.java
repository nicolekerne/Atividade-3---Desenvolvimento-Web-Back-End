package com.example.ativ3.repositories;

import com.example.ativ3.models.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SetorRepository
        extends JpaRepository<Setor, Integer> {

    @Query("""
        SELECT DISTINCT s
        FROM Setor s
        LEFT JOIN FETCH s.funcionarios
        WHERE s.id = :id
    """)
    Optional<Setor> buscarComFuncionarios(
        @Param("id") Integer id
    );
}
