package home;

import java.util.ArrayList;
import java.util.List;

public class TarefaManager {
	private List<Tarefa> tarefas = new ArrayList<>();
	
	public void adicionarTarefa(Tarefa t) {
		tarefas.add(t);
	}
	
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	
	public Tarefa buscarPorID(int idTarefaBuscada) {
		for(Tarefa t : tarefas) {
			if (t.getId() == idTarefaBuscada) {
				return t;
			} 
		}
		
		return null;
	}
	
	public boolean removerTarefa(int id) {
		Tarefa tarefa = buscarPorID(id);
		if (tarefa != null) {
			tarefas.remove(tarefa);
			return true;
			}
		return false;
	}
	
	public boolean concluirTarefa(int id) {
		Tarefa tarefa = buscarPorID(id);
		if(tarefa != null && tarefa.getStatus() == TarefaStatus.PENDENTE) {
			tarefa.setStatus(TarefaStatus.CONCLUIDA);			
			return true;
		}
		return false;
	}
	
	public String editarDescriao(int id, String decricao) {
		Tarefa tarefa = buscarPorID(id); 
						
		if(tarefa != null && tarefa.getStatus() == TarefaStatus.CONCLUIDA) {
			tarefa.setDescricao(decricao);			
						
			return "Descrição alterada com sucesso";
		}
		return "Ops! algo deu errado!";
	}
}