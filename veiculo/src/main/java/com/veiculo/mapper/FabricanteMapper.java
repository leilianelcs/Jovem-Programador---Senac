package com.veiculo.mapper;

import com.veiculo.dto.FabricanteDTO;
import com.veiculo.entity.Fabricante;

public final class FabricanteMapper {

    private FabricanteMapper() {
    }

    public static FabricanteDTO toDTO(Fabricante entity) {
        if (entity == null)
            return null;
        return new FabricanteDTO(entity.getId(), entity.getNome(), entity.getPaisOrigem());
    }

    public static Fabricante toEntity(FabricanteDTO dto) {
        if (dto == null)
            return null;
        Fabricante f = new Fabricante();
        f.setId(dto.getId());
        f.setNome(dto.getNome());
        f.setPaisOrigem(dto.getPaisOrigem());
        return f;
    }
}