package com.veiculo.dto;

import com.veiculo.entity.Fabricante;

public class ModeloDTO {
    private Long id;
    private String nome;
    private Fabricante fabricante;

    public ModeloDTO() {
    }

    public ModeloDTO(Long id, String nome, Fabricante fabricante) {
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

}
