package com.veiculo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veiculo.entity.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    boolean existsByPlaca(String placa); // validar no banco para verificar se é possível inserir o dado (um nome ou cpf
                                         // com unique, por exemplo, que não pode cadastrar igual)

    Optional<Veiculo> findByPlaca(String placa);

}
