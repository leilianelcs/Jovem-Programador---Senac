package sistemaTaxi.view;

import java.util.List;
import java.util.Scanner;

import sistemaTaxi.controller.VeiculoController;

public class VeiculoView {
      private VeiculoController controller = new VeiculoController();
    private Scanner scanner = new Scanner(System.in);

    private void cadastrarVeiculo() {
        System.out.println("\n======= CADASTRO DE VEÍCULO =======");

        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        if (!Validacao.validarPlaca(placa)) {
            System.out.println("Placa inválida. Cadastro cancelado.");
            return;
        }

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Ano: ");
        int ano;
        try {
            ano = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido. Cadastro cancelado.");
            return;
        }

        System.out.print("Cor: ");
        String cor = scanner.nextLine();

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        try {
            controller.cadastrarVeiculo(placa, modelo, ano, cor, marca);
            System.out.println("\nVeículo cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao cadastrar veículo: " + e.getMessage());
        }
    }

    private void listarVeiculos() {
        System.out.println("\n======= LISTA DE VEÍCULOS =======");
        List<Veiculo> veiculos = controller.listarVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            for (Veiculo v : veiculos) {
                System.out.println("\n--------------------------------");
                System.out.println(v);
            }
        }
    }

    private void buscarVeiculoPorPlaca() {
        System.out.print("\nDigite a placa do veículo: ");
        String placa = scanner.nextLine();
        Veiculo veiculo = controller.buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            System.out.println("\nVeículo encontrado:");
            System.out.println(veiculo);
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private void editarVeiculo() {
        List<Veiculo> veiculos = controller.listarVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("\nNenhum veículo cadastrado para editar.");
            return;
        }

        System.out.println("\n======= VEÍCULOS DISPONÍVEIS PARA EDIÇÃO =======");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println((i + 1) + ". " + veiculos.get(i).getPlaca() + " - " + veiculos.get(i).getModelo());
        }

        System.out.print("\nDigite o número do veículo que deseja editar: ");
        int indice;
        try {
            indice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }

        if (indice < 1 || indice > veiculos.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Veiculo veiculo = veiculos.get(indice - 1);
        String placaOriginal = veiculo.getPlaca();  

        System.out.print("Nova placa: ");
        String novaPlaca = scanner.nextLine();
        if (!Validacao.validarPlaca(novaPlaca)) {
            System.out.println("Placa inválida. Edição cancelada.");
            return;
        }
        veiculo.setPlaca(novaPlaca);

        System.out.print("Novo modelo: ");
        veiculo.setModelo(scanner.nextLine());

        System.out.print("Novo ano: ");
        try {
            veiculo.setAno(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido. Edição cancelada.");
            return;
        }

        System.out.print("Nova cor: ");
        veiculo.setCor(scanner.nextLine());

        System.out.print("Nova marca: ");
        veiculo.setMarca(scanner.nextLine());

        controller.editarVeiculo(veiculo, placaOriginal);  
        System.out.println("\nVeículo atualizado com sucesso!");
    }

    public void excluirVeiculo() {
        List<Veiculo> veiculos = controller.listarVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("\nNenhum veículo cadastrado para exclusão.");
            return;
        }

        System.out.println("\n======= VEÍCULOS DISPONÍVEIS PARA EXCLUSÃO =======");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println((i + 1) + ". " + veiculos.get(i).getPlaca() + " - " + veiculos.get(i).getModelo());
        }

        try {
            System.out.print("\nDigite o número do veículo que deseja excluir: ");
            int indice = Integer.parseInt(scanner.nextLine());

            if (indice < 1 || indice > veiculos.size()) {
                System.out.println("Número inválido.");
                return;
            }

            Veiculo veiculo = veiculos.get(indice - 1);
            System.out.print("Tem certeza que deseja excluir este veículo? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                controller.removerVeiculoPorPlaca(veiculo.getPlaca());
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
            System.out.println("\n=== MENU VEÍCULOS ===");
            System.out.println("\n1. Cadastrar Veículo");
            System.out.println("2. Listar Veículos");
            System.out.println("3. Buscar Veículo por Placa");
            System.out.println("4. Editar Veículo");
            System.out.println("5. Excluir Veículo");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrarVeiculo();
                case 2 -> listarVeiculos();
                case 3 -> buscarVeiculoPorPlaca();
                case 4 -> editarVeiculo();
                case 5 -> excluirVeiculo();
                case 0 -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
