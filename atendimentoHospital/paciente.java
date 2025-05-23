package atendimentoHospital;

public class Paciente {

	    private String nome;
	    private TipoAtendimento tipo;

	    public Paciente(String nome, TipoAtendimento tipo) {
	        this.nome = nome;
	        this.tipo = tipo;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public TipoAtendimento getTipo() {
	        return tipo;
	    }

	    @Override
	    public String toString() {
	        return nome + " (" + tipo + ")";
	    }
	}
	

