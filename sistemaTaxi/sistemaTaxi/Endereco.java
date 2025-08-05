package sistemaTaxi.sistemaTaxi;

public class Endereco {
    
		private String logradouro;
		private int numero;
		private String cep;
		private String complemento;
		private String estado;
		private String cidade;

	public Endereco (String logradouro, int numero, String cep, String complemento, String estado, String cidade) {
		if (!Validacao.validarCEP(cep)) {
			throw new IllegalArgumentException("CEP inválido: " + cep); 
			
		}
	
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.estado = estado;
		this.cidade = cidade;
	}
	
	
	
	public String getLogradouro() {
		return logradouro;
	}



	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}



	public int getNumero() {
		return numero;
	}



	public void setNumero(int numero) {
		this.numero = numero;
	}



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getComplemento() {
		return complemento;
	}



	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getCidade() {
		return cidade;
	}



	public void setCidade(String cidade) {
		this.cidade = cidade;
	}



	@Override
	public String toString() {
		return logradouro + " , " + numero + " - " + complemento + " , " + cidade + " - " + estado + ", cep: " + cep;
	}
}
