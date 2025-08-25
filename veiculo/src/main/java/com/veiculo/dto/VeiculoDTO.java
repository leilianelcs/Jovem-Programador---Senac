package com.veiculo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.veiculo.entity.Modelo;

public class VeiculoDTO {
    private Long id;
    private String placa;
    private String cor;
    private BigDecimal valor;
    private Integer ano;
    private String descricao;
    private LocalDateTime dataCadastro;
    private Modelo modelo;

    public VeiculoDTO() {
    }

    public VeiculoDTO(Long id, String placa, String cor, BigDecimal valor, Integer ano, String descricao,
            LocalDateTime dataCadastro, Modelo modelo) {
        this.id = id;
        this.placa = placa;
        this.cor = cor;
        this.valor = valor;
        this.ano = ano;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.modelo = modelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

}
