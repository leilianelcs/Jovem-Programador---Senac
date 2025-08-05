package sistemaTaxi.sistemaTaxi;

public class Veiculo {
    
    private String placa;
    private String modelo;
    private int ano;
    private String cor;
    private String marca;
    private int idVeiculo;

    public Veiculo(String placa, String modelo, int ano, String cor, String marca) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.marca = marca;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "\nVeículo {" +
                "Placa: '" + placa + '\'' +
                ", Modelo: '" + modelo + '\'' +
                ", Ano: " + ano +
                ", Cor: '" + cor + '\'' +
                ", Marca: '" + marca + '\'' +
                '}';
    }
}
