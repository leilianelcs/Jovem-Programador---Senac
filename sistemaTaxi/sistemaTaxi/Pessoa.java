package sistemaTaxi.sistemaTaxi;

public class Pessoa {
    
	private String nome;
	private String cpf;	
	private String telefone;
	private Endereco endereco;
	
	public Pessoa (String nome, String cpf, String telefone, Endereco endereco) {
		if (!Validacao.validarCPF(cpf)) {
			throw new IllegalArgumentException("CPF inválido: " + cpf);
		}
		
		if (!Validacao.validarTelefone(telefone)) {
			throw new IllegalArgumentException("Telefone inválido: " + telefone);
			
		}
		
		this.nome = nome;
		this.cpf = cpf;		
		this.telefone = telefone;
		this.endereco = endereco;
	
	}
	
	 
	public String getNome() {
		return nome;
	}
 
	public void setNome(String nome) {
		this.nome = nome;
	}
 
	public String getCpf() {
		return cpf;
	}
 
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
 
 
	public String getTelefone() {
		return telefone;
	}
 
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
 
	public Endereco getEndereco() {
		return endereco;
	}
 
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
 
	public void exibirDados() {
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);		
		System.out.println("Telefone: " + telefone);
		System.out.println("Endereço: " + endereco);
	}
}
