package com.veiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veiculo.entity.Fabricante;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
    boolean existsByNome(String nome);

    String findByNome(String nome);

    String findByPaisOrigem(String paisOrigem);
}
