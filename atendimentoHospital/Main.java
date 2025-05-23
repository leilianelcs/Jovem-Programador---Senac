package atendimentoHospital;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaHospital lista = new ListaHospital();
        int opcao = -1;

        do {
            System.out.println("\n===== Menu Hospital =====");
            System.out.println("\n1. Adicionar Paciente");
            System.out.println("2. Chamar Próximo Paciente");
            System.out.println("3. Exibir Listas");
            System.out.println("4. Sair");
            System.out.print("\nEscolha uma opção: ");

            String entrada = scanner.nextLine();
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcao = -1; 
            }

            switch (opcao) {

                case 1 -> {
                    String nome;
                    do {
                        System.out.print("\nNome do paciente: ");
                        nome = scanner.nextLine().trim();
                        if (nome.isEmpty()) {
                            System.out.println("\nO nome não pode estar vazio. Tente novamente.");
                        } else if (!nome.matches("[a-zA-ZÀ-ÿ\\s]+")) {
                            System.out.println("\nO nome deve conter apenas letras e espaços. Tente novamente.");
                            nome = "";
                        }
                    } while (nome.isEmpty());

                    TipoAtendimento tipo = null;
                    boolean entradaValida = false;

                    while (!entradaValida) {
                        System.out.print("\nTipo de atendimento (NORMAL ou PREFERENCIAL): ");
                        String tipoEntrada = scanner.nextLine().toUpperCase();

                        try {
                            tipo = TipoAtendimento.valueOf(tipoEntrada);
                            entradaValida = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Tipo inválido! Digite apenas NORMAL ou PREFERENCIAL.");
                        }
                    }

                    lista.adicionarPaciente(new Paciente(nome, tipo));
                    System.out.println("\nPaciente adicionado com sucesso!");
                }

                case 2 -> {
                    Paciente proximo = lista.chamarProximo();
                    if (proximo != null) {
                        System.out.println("\n===== PRÓXIMO PACIENTE A SER ATENDIDO =====");
                        System.out.println("\nChamando paciente: " + proximo);
                    } else {
                        System.out.println("\nNão há pacientes a serem chamados!");
                    }
                }

                case 3 -> lista.exibirListas();

                case 4 -> System.out.println("\nSaindo do Sistema...");

                default -> System.out.println("\nOpção inválida!");
            }

        } while (opcao != 4);

        scanner.close();
    }
}
