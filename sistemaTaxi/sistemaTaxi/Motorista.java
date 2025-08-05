package sistemaTaxi.sistemaTaxi;

public class Motorista {
    
    private String cnh;
    private int idMotorista;

    public Motorista(String nome, String cpf, String cnh, String telefone, Endereco endereco) {
        super(nome, cpf, telefone, endereco);
        this.setCnh(cnh);

        if (!Validacao.validarCNH(cnh)) {
            throw new IllegalArgumentException("CNH inválida: " + cnh);
        }
    }

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
    }

   
    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("CNH: " + cnh);
    }
}

