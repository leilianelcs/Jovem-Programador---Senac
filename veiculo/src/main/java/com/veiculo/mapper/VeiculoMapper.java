package com.veiculo.mapper;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<VeiculoDTO> toDTOList(List<Veiculo> list) {
        return list == null ? List.of() : list.stream().map(VeiculoMapper::toDTO).collect(Collectors.toList());
    }

    public static List<Veiculo> toEntityList(List<VeiculoDTO> list) {
        return list == null ? List.of() : list.stream().map(VeiculoMapper::toEntity).collect(Collectors.toList());
    }
}