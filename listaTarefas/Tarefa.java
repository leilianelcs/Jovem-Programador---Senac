package home;

public class Tarefa {
	
	private static int contadorId = 1;
	
	private int id;
	private String titulo;
	private String descricao;
	private TarefaPrioridade prioridade;
	private TarefaStatus status;
		
	public Tarefa(String titulo, String descricao, TarefaPrioridade prioridade) {
		this.id = contadorId++;
		this.titulo = titulo;
		this.descricao = descricao;
		this.prioridade = prioridade;
		this.status = TarefaStatus.PENDENTE;
	}
	
	public void exibirDetalhes() {
		System.out.println("\nID: " + id);
		System.out.println("Título: " + titulo);
		System.out.println("Descrição: " + descricao);
		System.out.println("Prioridade: " + prioridade);
		System.out.println("Status: " + status);
		System.out.println("------------------------------------");
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TarefaPrioridade getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(TarefaPrioridade prioridade) {
		this.prioridade = prioridade;
	}
	public TarefaStatus getStatus() {
		return status;
	}
	public void setStatus(TarefaStatus status) {
		this.status = status;
	}
		
	
}
