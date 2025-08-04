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
			System.out.println("5. Editar Descrição");
			System.out.println("6. Remover Tarefa");
			System.out.println("7. Sair");
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
				

				case 4 -> {
					System.out.println("\nDigite o ID da tarefa a ser editada: ");
					int id = scanner.nextInt();
					scanner.nextLine();

					System.out.println("Novo título: ");
					String novoTitulo = scanner.nextLine();

					System.out.println("Nova descrição: ");
					String novaDescricao = scanner.nextLine();

					System.out.println("Nova prioridade [BAIXA, MEDIA, ALTA]: ");
					TarefaPrioridade novaPrioridade = TarefaPrioridade.valueOf(scanner.nextLine().toUpperCase());

					String resultado = manager.editarTarefa(id, novoTitulo, novaDescricao, novaPrioridade);
					System.out.println(resultado);
}
				
//				case 4 -> {
//					System.out.println("\nDigite o ID da tarefa a ser editada: ");
//					int id = scanner.nextInt();
//					scanner.nextLine();
//					
//					Tarefa tarefa = manager.buscarPorID(id);
//					
//					if (tarefa != null && tarefa.getStatus() != TarefaStatus.CONCLUIDA) {
//								
//						System.out.println("Novo título: ");
//						tarefa.setTitulo(scanner.nextLine());
//					
//						System.out.println("Nova descrição: ");
//						tarefa.setDescricao(scanner.nextLine());
//						
//						System.out.println("Nova prioridade [BAIXA, MEDIA, ALTA]: ");
//						tarefa.setPrioridade(TarefaPrioridade.valueOf(scanner.nextLine().toUpperCase()));
//						System.out.println("\nTarefa atualizada!");
//						
//					} else {
//						System.err.println("Tarefa não encontrada.");
//					}
//									
//					}
								
				case 5 -> {
					System.out.println("Digite o ID da tarefa concluída para editar a descrição:");
					int id = scanner.nextInt();
					scanner.nextLine();

					System.out.println("Nova descrição:");
					String novaDescricao = scanner.nextLine();

					String resultado = manager.editarDescricao(id, novaDescricao);
					System.out.println(resultado);
					}

//				
//				System.out.println("digite o id da tarefa que a descrição vai ser editada");
//				int id = scanner.nextInt();
//				scanner.nextLine();
//				
//				Tarefa tarefa = manager.buscarPorId(id);
//				if(tarefa != null && tarefa.getStatus() != TarefaStatus.CONCLUIDA ) {
//					System.out.println("novo titulo: ");
//					 String descricao = scanner.nextLine();
//					manager.editarDescricao(id,descricao);
//				}
//				
//				else {System.err.println("Tarefa não encontrada!");}
//				
//				
				case 6 -> {
					System.out.println("Digite o ID da tarefa a ser removida: ");
					int id = scanner.nextInt();
					if (manager.removerTarefa(id)) {
						System.out.println("Tarefa removida com sucesso!");
					} else {
					System.out.println("Tarefa não removida!");
					}					
				}
							
				
				case 7 -> {
					System.out.println("Saindo...");
				}
				
				default -> {
					System.out.println("Opção inválida!");
				}
					
				} 
							
			
		} while (opcao != 7);
		
		scanner.close();
		
		}
		
	}
