package com.veiculo.dto;

public class FabricanteDTO {
    private Long id;
    private String nome;
    private String paisOrigem;

    public FabricanteDTO() {
    }

    public FabricanteDTO(Long id, String nome, String paisOrigem) {
        this.id = id;
        this.nome = nome;
        this.paisOrigem = paisOrigem;
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

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

}
