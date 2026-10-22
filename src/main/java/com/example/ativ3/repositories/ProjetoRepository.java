package com.example.ativ3.repositories;

import com.example.ativ3.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjetoRepository
        extends JpaRepository<Projeto, Integer> {

    @Query("""
        SELECT DISTINCT p
        FROM Projeto p
        LEFT JOIN FETCH p.funcionarios
        WHERE p.id = :id
    """)
    Optional<Projeto> buscarComFuncionarios(
        @Param("id") Integer id
    );

    List<Projeto> findByDataInicioBetween(
        LocalDate inicio,
        LocalDate fim
    );
}
