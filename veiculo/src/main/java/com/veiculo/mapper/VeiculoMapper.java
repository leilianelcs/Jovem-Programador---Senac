package com.veiculo.mapper;

import com.veiculo.dto.VeiculoDTO;
import com.veiculo.entity.Veiculo;

public final class VeiculoMapper {

    private VeiculoMapper() {
    }

    public static VeiculoDTO toDTO(Veiculo entity) {
        if (entity == null)
            return null;
        return new VeiculoDTO(entity.getId(), entity.getPlaca(), entity.getCor(), entity.getValor(), entity.getAno(),
                entity.getDescricao(), entity.getDataCadastro(), entity.getModelo());
    }

    public static Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null)
            return null;
        Veiculo f = new Veiculo();
        f.setId(dto.getId());
        f.setPlaca(dto.getPlaca());
        f.setCor(dto.getCor());
        f.setValor(dto.getValor());
        f.setAno(dto.getAno());
        f.setDescricao(dto.getDescricao());
        f.setDataCadastro(dto.getDataCadastro());
        f.setModelo(dto.getModelo());
        return f;
    }
}