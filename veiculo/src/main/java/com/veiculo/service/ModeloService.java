package com.veiculo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.veiculo.dto.ModeloDTO;
import com.veiculo.entity.Modelo;
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

    @Transactional(readOnly = true)
    public ModeloDTO buscarPorId(Long id) {
        return repository.findById(id).map(ModeloMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));
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

    @Transactional
    public ModeloDTO atualizar(Long id, ModeloDTO dto) {
        Modelo existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));
        existente.setNome(dto.getNome());
        return ModeloMapper.toDTO(repository.save(existente));
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Modelo não encontrado");
        }
        repository.deleteById(id);
    }

}
