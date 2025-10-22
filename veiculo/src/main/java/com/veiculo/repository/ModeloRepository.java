package com.veiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.veiculo.entity.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    boolean existsByNome(String nome); // validar no banco para verificar se é possível inserir o dado (um nome ou cpf
                                       // com unique, por exemplo, que não pode cadastrar igual)

    // remove registros pelo nome e retorna a quantidade excluída
    long deleteByNome(String nome);

    // verifica se existem veículos associados ao modelo
    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Veiculo v WHERE v.modelo.id = :modeloId")
    boolean temVeiculosAssociados(@Param("modeloId") Long modeloId);
}
