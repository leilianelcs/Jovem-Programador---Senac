package sistemaTaxi.view;

import java.util.List;
import java.util.Scanner;

import sistemaTaxi.controller.MotoristaController;

public class MotoristaView {
  private MotoristaController controller = new MotoristaController();	
	private Scanner scanner = new Scanner(System.in);
	
	public void cadastrarMotorista( ) {
		System.out.println("\n======= CADASTRO DE MOTORISTAS =======");
		
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		
		System.out.print("CPF: ");
		String cpf = scanner.nextLine();
		
		System.out.print("CNH: ");
		String cnh = scanner.nextLine();
		
		System.out.print("Telefone: ");
		String telefone = scanner.nextLine();
		
		System.out.print("Logradouro: ");
		String logradouro = scanner.nextLine();
		
		System.out.print("Número: ");
		int numero = scanner.nextInt();
	    scanner.nextLine(); 
	        
		System.out.println("CEP: ");
		String cep = scanner.nextLine();
		
		System.out.println("Complemento: ");
		String complemento = scanner.nextLine();		
				
		System.out.println("Estado: ");
		String estado = scanner.nextLine();
		
		System.out.println("Cidade: ");
		String cidade = scanner.nextLine();		
				
						
        try {
        	controller.cadastrarMotorista(nome, cpf, cnh, telefone, logradouro, numero, cep, complemento, estado, cidade);
            System.out.println("\nMotorista cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao cadastrar motorista: " + e.getMessage());
        }
    }

    public void listarMotoristas() {
    	 System.out.println("\n======= Lista de Motoristas =======");
        List<Motorista> motoristas = controller.listarMotoristas();
        if (motoristas.isEmpty()) {
            System.out.println("\nNenhum motorista cadastrado.");
        } else {
        	for (Motorista motorista : motoristas) {
                System.out.println("\n--------------------------------");
                motorista.exibirDados();
            }
        }
    }

    public void editarMotorista() {
        List<Motorista> motoristas = controller.listarMotoristas();
        if (motoristas.isEmpty()) {
            System.out.println("\nNenhum motorista cadastrado para editar.");
            return;
        }

        System.out.println("\n======= MOTORISTAS DISPONÍVEIS PARA EDIÇÃO =======");
        for (int i = 0; i < motoristas.size(); i++) {
            System.out.println((i + 1) + ". " + motoristas.get(i).getNome());
        }

        try {
            System.out.print("\nDigite o número do motorista que deseja editar: ");
            int indice = Integer.parseInt(scanner.nextLine());

            if (indice < 1 || indice > motoristas.size()) {
                System.out.println("Número inválido.");
                return;
            }

            Motorista motorista = motoristas.get(indice - 1);
            String cpfOriginal = motorista.getCpf(); // <- necessário para WHERE correto

            System.out.print("Novo nome: ");
            motorista.setNome(scanner.nextLine());

            System.out.print("Novo CPF: ");
            String cpf = scanner.nextLine();
            if (!Validacao.validarCPF(cpf)) throw new IllegalArgumentException("CPF inválido");
            motorista.setCpf(cpf);

            System.out.print("Nova CNH: ");
            String cnh = scanner.nextLine();
            if (!Validacao.validarCNH(cnh)) throw new IllegalArgumentException("CNH inválida");
            motorista.setCnh(cnh);

            System.out.print("Novo telefone: ");
            String telefone = scanner.nextLine();
            if (!Validacao.validarTelefone(telefone)) throw new IllegalArgumentException("Telefone inválido");
            motorista.setTelefone(telefone);

            System.out.print("Novo logradouro: ");
            motorista.getEndereco().setLogradouro(scanner.nextLine());

            System.out.print("Novo número: ");
            motorista.getEndereco().setNumero(Integer.parseInt(scanner.nextLine()));

            System.out.print("Novo CEP: ");
            String cep = scanner.nextLine();
            if (!Validacao.validarCEP(cep)) throw new IllegalArgumentException("CEP inválido");
            motorista.getEndereco().setCep(cep);

            System.out.print("Novo complemento: ");
            motorista.getEndereco().setComplemento(scanner.nextLine());

            System.out.print("Novo estado: ");
            motorista.getEndereco().setEstado(scanner.nextLine());

            System.out.print("Nova cidade: ");
            motorista.getEndereco().setCidade(scanner.nextLine());

            controller.editarMotorista(motorista, cpfOriginal);
            System.out.println("\nMotorista atualizado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("\nErro: Número inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao editar motorista: " + e.getMessage());
        }
    }


    public void excluirMotorista() {
        List<Motorista> motoristas = controller.listarMotoristas();
        if (motoristas.isEmpty()) {
            System.out.println("\nNenhum motorista cadastrado para excluir.");
            return;
        }

        System.out.println("\n======= MOTORISTAS DISPONÍVEIS PARA EXCLUSÃO =======");
        for (int i = 0; i < motoristas.size(); i++) {
            System.out.println((i + 1) + ". " + motoristas.get(i).getNome());
        }

        try {
            System.out.print("\nDigite o número do motorista que deseja excluir: ");
            int indice = Integer.parseInt(scanner.nextLine());

            if (indice < 1 || indice > motoristas.size()) {
                System.out.println("Número inválido.");
                return;
            }

            Motorista motorista = motoristas.get(indice - 1);
            System.out.print("Tem certeza que deseja excluir este motorista? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                controller.excluirMotorista(motorista.getCpf());
            } else {
                System.out.println("\nExclusão cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nEntrada inválida.");
        }
    }

			

    public void exibirMenu() {
        int opcao = -1;

        do {
            System.out.println("\n=== MENU MOTORISTAS ===");
            System.out.println("\n1. Cadastrar Motorista");
            System.out.println("2. Listar Motoristas");
            System.out.println("3. Editar Motorista");
            System.out.println("4. Excluir Motorista");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrarMotorista();
                case 2 -> listarMotoristas();
                case 3 -> editarMotorista();
                case 4 -> excluirMotorista();
                case 0 -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
  
}
