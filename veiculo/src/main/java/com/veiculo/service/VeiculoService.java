package com.veiculo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.veiculo.dto.VeiculoDTO;
import com.veiculo.mapper.VeiculoMapper;
import com.veiculo.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    @Transactional
    public VeiculoDTO criar(VeiculoDTO dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Novo veículo não deve ter ID");
        }
        if (repository.existsByPlaca(dto.getPlaca())) {
            throw new IllegalArgumentException("  Esta placa já está cadastrada");
        }

        return VeiculoMapper.toDTO(repository.save(VeiculoMapper.toEntity(dto)));
    }

}
