package sistemaTaxi.sistemaTaxi;

import java.util.Scanner;

import sistemaTaxi.controller.ClienteController;
import sistemaTaxi.controller.MotoristaController;
import sistemaTaxi.controller.VeiculoController;
import sistemaTaxi.view.ChamadoView;
import sistemaTaxi.view.ClienteView;
import sistemaTaxi.view.MotoristaView;
import sistemaTaxi.view.VeiculoView;

public class Main {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ClienteController clienteController = new ClienteController();
        MotoristaController motoristaController = new MotoristaController();
        VeiculoController veiculoController = new VeiculoController();

        ClienteView clienteView = new ClienteView();
        MotoristaView motoristaView = new MotoristaView();
        VeiculoView veiculoView = new VeiculoView();

        ChamadoView chamadoView = new ChamadoView(clienteController, motoristaController, veiculoController);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nSistema finalizado. Obrigado por utilizar o Sistema Táxi!");
        }));

        int opcao = -1;

        do {
        	 System.out.println("========= BEM-VINDO! =========");
            System.out.println("\n=== SISTEMA DE CHAMADOS DE TÁXI ===");
            System.out.println("\n1. Menu de Clientes");
            System.out.println("2. Menu de Motoristas");
            System.out.println("3. Menu de Veículos");
            System.out.println("4. Menu de Chamados");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> clienteView.exibirMenu();
                    case 2 -> motoristaView.exibirMenu();
                    case 3 -> veiculoView.exibirMenu();
                    case 4 -> chamadoView.exibirMenu();
                    case 0 -> System.out.println("\nEncerrando o sistema...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
