package com.veiculo.mapper;

import com.veiculo.dto.ModeloDTO;
import com.veiculo.entity.Modelo;

public final class ModeloMapper {

    private ModeloMapper() {
    }

    public static ModeloDTO toDTO(Modelo entity) {
        if (entity == null)
            return null;
        return new ModeloDTO(entity.getId(), entity.getNome(), entity.getFabricante());
    }

    public static Modelo toEntity(ModeloDTO dto) {
        if (dto == null)
            return null;
        Modelo f = new Modelo();
        f.setId(dto.getId());
        f.setNome(dto.getNome());
        f.setFabricante(dto.getFabricante());
        return f;
    }
}