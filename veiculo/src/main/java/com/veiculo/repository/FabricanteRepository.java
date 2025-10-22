package com.veiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.veiculo.entity.Fabricante;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
    boolean existsByNome(String nome);

    String findByNome(String nome);

    String findByPaisOrigem(String paisOrigem);

    // long deleteByNome(String nome);

    // @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Modelo m
    // WHERE m.fabricante.id = :fabricanteId")
    // boolean temModelosAssociados(@Param("fabricanteId") Long fabricanteId);
}
