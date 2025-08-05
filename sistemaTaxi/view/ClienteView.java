package sistemaTaxi.view;

import java.util.List;
import java.util.Scanner;

import sistemaTaxi.controller.ClienteController;

public class ClienteView {
  
    private ClienteController controller = new ClienteController();
    private Scanner scanner = new Scanner(System.in);

    private void cadastrarCliente() {
        System.out.println("\n======= CADASTRO DE CLIENTES =======");

        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            if (!Validacao.validarCPF(cpf)) throw new IllegalArgumentException("CPF inválido");

            System.out.print("RG: ");
            String rg = scanner.nextLine();
            if (!Validacao.validarRG(rg)) throw new IllegalArgumentException("RG inválido");

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            if (!Validacao.validarTelefone(telefone)) throw new IllegalArgumentException("Telefone inválido");

            System.out.print("Logradouro: ");
            String logradouro = scanner.nextLine();

            System.out.print("Número: ");
            int numero = Integer.parseInt(scanner.nextLine());

            System.out.print("CEP: ");
            String cep = scanner.nextLine();
            if (!Validacao.validarCEP(cep)) throw new IllegalArgumentException("CEP inválido");

            System.out.print("Complemento: ");
            String complemento = scanner.nextLine();

            System.out.print("Estado: ");
            String estado = scanner.nextLine();

            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();

            controller.cadastrarCliente(nome, cpf, rg, telefone, logradouro,
                    numero, cep, complemento, estado, cidade);
            System.out.println("\nCliente cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Número inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private void listarClientes() {
        System.out.println("\n======= Lista de Clientes =======");
        List<Cliente> lista = controller.listarClientes();
        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : lista) {
                System.out.println("\n--------------------------------");
                System.out.println(c);
            }
        }
    }

    public void editarCliente() {
        List<Cliente> clientes = controller.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado para editar.");
            return;
        }

        System.out.println("\n======= CLIENTES DISPONÍVEIS PARA EDIÇÃO =======");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
        }

        try {
            System.out.print("\nDigite o número do cliente que deseja editar: ");
            int indice = Integer.parseInt(scanner.nextLine());

            if (indice < 1 || indice > clientes.size()) {
                System.out.println("Número inválido.");
                return;
            }

            Cliente cliente = clientes.get(indice - 1);
            String cpfAntigo = cliente.getCpf();  // <== ARMAZENA O CPF ORIGINAL

            System.out.print("Novo nome: ");
            cliente.setNome(scanner.nextLine());

            System.out.print("Novo CPF: ");
            String cpf = scanner.nextLine();
            if (!Validacao.validarCPF(cpf)) throw new IllegalArgumentException("CPF inválido");
            cliente.setCpf(cpf);

            System.out.print("Novo RG: ");
            String rg = scanner.nextLine();
            if (!Validacao.validarRG(rg)) throw new IllegalArgumentException("RG inválido");
            cliente.setRg(rg);

            System.out.print("Novo telefone: ");
            String telefone = scanner.nextLine();
            if (!Validacao.validarTelefone(telefone)) throw new IllegalArgumentException("Telefone inválido");
            cliente.setTelefone(telefone);

            System.out.print("Novo logradouro: ");
            cliente.getEndereco().setLogradouro(scanner.nextLine());

            System.out.print("Novo número: ");
            cliente.getEndereco().setNumero(Integer.parseInt(scanner.nextLine()));

            System.out.print("Novo CEP: ");
            String cep = scanner.nextLine();
            if (!Validacao.validarCEP(cep)) throw new IllegalArgumentException("CEP inválido");
            cliente.getEndereco().setCep(cep);

            System.out.print("Novo complemento: ");
            cliente.getEndereco().setComplemento(scanner.nextLine());

            System.out.print("Novo estado: ");
            cliente.getEndereco().setEstado(scanner.nextLine());

            System.out.print("Nova cidade: ");
            cliente.getEndereco().setCidade(scanner.nextLine());

            controller.editarCliente(cliente, cpfAntigo); // <== PASSA O CPF ORIGINAL
            System.out.println("\nCliente atualizado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("\nErro: Número inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao editar cliente: " + e.getMessage());
        }
    }


    private void buscarPorCpf() {
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        Cliente cliente = controller.buscarClientePorCpf(cpf);
        if (cliente != null) {
            System.out.println("\nCliente encontrado:\n" + cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
    public void excluirCliente() {
        List<Cliente> clientes = controller.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado para excluir.");
            return;
        }

        System.out.println("\n======= CLIENTES DISPONÍVEIS PARA EXCLUSÃO =======");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
        }

        try {
            System.out.print("\nDigite o número do cliente que deseja excluir: ");
            int indice = Integer.parseInt(scanner.nextLine());

            if (indice < 1 || indice > clientes.size()) {
                System.out.println("Número inválido.");
                return;
            }

            Cliente cliente = clientes.get(indice - 1);
            System.out.print("Tem certeza que deseja excluir este cliente? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                controller.excluirCliente(cliente.getCpf()); // agora o tratamento está dentro do controller
            } else {
                System.out.println("\nExclusão cancelada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("\nEntrada inválida.");
        }
    }


    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU CLIENTES ===");
            System.out.println("\n1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar por CPF");
            System.out.println("4. Editar Cliente");
            System.out.println("5. Excluir Cliente");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> buscarPorCpf();
                case 4 -> editarCliente();
                case 5 -> excluirCliente();
                case 0 -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }  
}
