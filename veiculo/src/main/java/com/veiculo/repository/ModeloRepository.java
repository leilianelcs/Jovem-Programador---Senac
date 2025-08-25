package com.veiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veiculo.entity.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    boolean existsByNome(String nome); // validar no banco para verificar se é possível inserir o dado (um nome ou cpf
                                       // com unique, por exemplo, que não pode cadastrar igual)
}
