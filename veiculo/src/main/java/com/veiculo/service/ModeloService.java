package com.veiculo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.veiculo.dto.ModeloDTO;
import com.veiculo.mapper.ModeloMapper;
import com.veiculo.repository.ModeloRepository;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository repository;

    @Transactional(readOnly = true)
    public List<ModeloDTO> listar() {
        return ModeloMapper.toDTOList(repository.findAll());
    }

    @Transactional
    public ModeloDTO criar(ModeloDTO dto) {

        if (dto.getId() != null) {
            throw new IllegalArgumentException("Novo modelo não deve ter ID");
        }
        if (repository.existsByNome(dto.getNome())) {
            throw new IllegalArgumentException("  Já existe modelo com esse nome");
        }

        /*
         * if (dto.getFabricante() == null || dto.getFabricante().getId() == null) {
         * throw new IllegalArgumentException("Fabricante inválido");
         * }
         * if (FabricanteRepository.findById(dto.getFabricante().getId().isEmpty())) {
         * throw new IllegalArgumentException("Fabricante não encontrado");
         * }
         */

        return ModeloMapper.toDTO(repository.save(ModeloMapper.toEntity(dto)));
    }

}
