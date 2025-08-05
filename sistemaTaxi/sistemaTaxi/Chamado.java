package sistemaTaxi.sistemaTaxi;

import java.time.LocalDate;
import java.time.LocalTime;

public class Chamado {
    
    private int idChamado;
    private Cliente cliente;
    private Motorista motorista;
    private Veiculo veiculo;
    private String origem;
    private String destino;
    private LocalDate data;
    private TipoChamado tipoChamado;
    private double kmInicial;
    private double kmFinal;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private double valorTotal;

    public Chamado(Cliente cliente, Motorista motorista, Veiculo veiculo,
                   String origem, String destino, LocalDate data, TipoChamado tipoChamado,
                   double kmInicial, double kmFinal,
                   LocalTime horaInicio, LocalTime horaFim, double valorTotal) {
        this.cliente = cliente;
        this.motorista = motorista;
        this.veiculo = veiculo;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.tipoChamado = tipoChamado;
        this.kmInicial = kmInicial;
        this.kmFinal = kmFinal;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.valorTotal = valorTotal;
    }

        
    public int getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(int idChamado) {
        this.idChamado = idChamado;
    }

    public Cliente getCliente() { return cliente; }
    public Motorista getMotorista() { return motorista; }
    public Veiculo getVeiculo() { return veiculo; }
    public String getOrigem() { return origem; }
    public String getDestino() { return destino; }
    public LocalDate getData() { return data; }
    public TipoChamado getTipoChamado() { return tipoChamado; }
    public double getKmInicial() { return kmInicial; }
    public double getKmFinal() { return kmFinal; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFim() { return horaFim; }
    public double getValorTotal() { return valorTotal; }

    public void setOrigem(String origem) { this.origem = origem; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setData(LocalDate data) { this.data = data; }
    public void setTipoChamado(TipoChamado tipoChamado) { this.tipoChamado = tipoChamado; }
    public void setKmInicial(double kmInicial) { this.kmInicial = kmInicial; }
    public void setKmFinal(double kmFinal) { this.kmFinal = kmFinal; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFim(LocalTime horaFim) { this.horaFim = horaFim; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    
    @Override
    public String toString() {
        return "ID: " + idChamado + " | Cliente: " + cliente.getNome() +
               " | Motorista: " + motorista.getNome() +
               " | Origem: " + origem + " → Destino: " + destino +
               " | Data: " + data + " | Valor: R$ " + valorTotal;
    }
}
