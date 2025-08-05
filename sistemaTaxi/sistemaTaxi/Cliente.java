package sistemaTaxi.sistemaTaxi;

public class Cliente {
    private String rg;  
    private int idCliente;

    public Cliente(String nome, String cpf, String rg, String telefone, Endereco endereco) {
        super(nome, cpf, telefone, endereco);
        this.rg = rg;       
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getRg() {
        return rg;
    }
   
    public void setRg(String rg) {
        this.rg = rg;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("RG: " + rg);       
    }
    
    @Override
    public String toString() {
        return """
            Nome: %s
            CPF: %s
            RG: %s
            Telefone: %s            
            Endereço: %s, Nº %d, %s - %s/%s | CEP: %s
            """.formatted(
                getNome(),
                getCpf(),
                rg,
                getTelefone(),
                getEndereco().getLogradouro(),
                getEndereco().getNumero(),
                getEndereco().getComplemento(),
                getEndereco().getCidade(),
                getEndereco().getEstado(),
                getEndereco().getCep()
            );
    }
}
