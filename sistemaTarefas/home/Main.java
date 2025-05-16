package home;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		TarefaManager manager = new TarefaManager();
		int opcao;
		do {
			System.out.println("\n========= Menu =========");
			System.out.println("\n1. Adicionar Tarefa");
			System.out.println("2. Listar Tarefas");
			System.out.println("3. Concluir Tarefa");
			System.out.println("4. Editar Tarefa");
			System.out.println("5. Remover Tarefa");
			System.out.println("6. Sair");
			System.out.println("\nEscolha uma opção:");
			opcao = scanner.nextInt();
			scanner.nextLine();
		
			switch (opcao) {
				case 1 -> {
					System.out.println("Digite o título: ");
					String titulo = scanner.nextLine();
		
					System.out.println("Digite descrição: ");
					String descricao = scanner.nextLine();
		
					System.out.println("Prioridade (BAIXA, MEDIA, ALTA):");
					TarefaPrioridade prioridade = 
							TarefaPrioridade.valueOf(scanner.nextLine().toUpperCase());
					
					Tarefa tarefa = new Tarefa(titulo, descricao, prioridade);
					manager.adicionarTarefa(tarefa);
					System.out.println("\nTarefa adicionada!");
					
				} 
		
				case 2 -> {
					System.out.println("\n------ Tarefas adicionadas ------");
					for(Tarefa t : manager.getTarefas()) {
						t.exibirDetalhes();

//						if(t.getStatus() == TarefaStatus.CONCLUIDA) {
//						t.exibirDetalhes();
//						}
					}
				}
				
				case 3 -> {
					System.out.println("\n --- Digite o ID da tarefa a ser concluída:");
					int id = scanner.nextInt();
					if(manager.concluirTarefa(id)) {
						System.out.println("\nTarefa concluída com sucesso!");						
					} else {
						System.err.println("\nTarefa não encontrada ou já concluída.");
					}
					
				}
					
				} 
							
			
		} while (opcao != 6);
		
		scanner.close();
		
		}
		
	}