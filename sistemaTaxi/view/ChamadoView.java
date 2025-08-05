package sistemaTaxi.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import sistemaTaxi.controller.ChamadoController;
import sistemaTaxi.controller.ClienteController;
import sistemaTaxi.controller.MotoristaController;
import sistemaTaxi.controller.VeiculoController;

public class ChamadoView {
    private ChamadoController chamadoController = new ChamadoController();
    private ClienteController clienteController;
    private MotoristaController motoristaController;
    private VeiculoController veiculoController;
    private Scanner scanner = new Scanner(System.in);

    public ChamadoView(ClienteController clienteController, MotoristaController motoristaController, VeiculoController veiculoController) {
        this.clienteController = clienteController;
        this.motoristaController = motoristaController;
        this.veiculoController = veiculoController;
    }
    
    private void criarChamado() {
        System.out.print("Nome do Cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = clienteController.buscarClientePorNome(nomeCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Nome do Motorista: ");
        String nomeMotorista = scanner.nextLine();
        Motorista motorista = motoristaController.buscarMotoristaPorNome(nomeMotorista);
        if (motorista == null) {
            System.out.println("Motorista não encontrado.");
            return;
        }

        System.out.print("Placa do Veículo: ");
        String placa = scanner.nextLine();
        Veiculo veiculo = veiculoController.buscarVeiculoPorPlaca(placa);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.print("Origem: ");
        String origem = scanner.nextLine();

        System.out.print("Destino: ");
        String destino = scanner.nextLine();

        // 1. DATA AUTOMÁTICA
        LocalDate hoje = LocalDate.now();
        System.out.print("Data do Chamado (pressione Enter para hoje: " + hoje + "): ");
        String dataStr = scanner.nextLine();
        LocalDate data = dataStr.isBlank() ? hoje : LocalDate.parse(dataStr);

        // 3. VALIDAÇÃO COM DEFAULT
        TipoChamado tipoChamado = TipoChamado.COMUM;
        System.out.print("Tipo de Chamado (COMUM, EXECUTIVO, VIAGEM, OUTROS) - ou pressione Enter para: COMUM: ");
        String tipo = scanner.nextLine();
        if (!tipo.isBlank()) {
            try {
                tipoChamado = TipoChamado.valueOf(tipo.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido. Usando COMUM.");
            }
        }

        System.out.print("KM Inicial: ");
        double kmInicial = Double.parseDouble(scanner.nextLine());

        System.out.print("KM Final: ");
        double kmFinal = Double.parseDouble(scanner.nextLine());

        // 2. HORA AUTOMÁTICA
        LocalTime horaInicio = LocalTime.now();
        System.out.print("Hora Início (HH:mm) - pressione Enter para: " + horaInicio + ": ");
        String hInicio = scanner.nextLine();
        if (!hInicio.isBlank()) horaInicio = LocalTime.parse(hInicio);

        LocalTime horaFim = LocalTime.now();
        System.out.print("Hora Fim (HH:mm) - pressione Enter para: " + horaFim + ": ");
        String hFim = scanner.nextLine();
        if (!hFim.isBlank()) horaFim = LocalTime.parse(hFim);

        // 2. VALOR AUTOMÁTICO COM BASE NO KM RODADO
        double valorPorKm = 2.50;
        if (tipoChamado == TipoChamado.EXECUTIVO) valorPorKm = 3.50;
        if (tipoChamado == TipoChamado.VIAGEM) valorPorKm = 4.00;
        double kmRodado = kmFinal - kmInicial;
        double valorTotal = kmRodado * valorPorKm;

        // 4. RESUMO FINAL
        System.out.println("\n--- RESUMO DO CHAMADO ---");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Motorista: " + motorista.getNome());
        System.out.println("Veículo: " + veiculo.getPlaca());
        System.out.println("Origem: " + origem);
        System.out.println("Destino: " + destino);
        System.out.println("Data: " + data);
        System.out.println("Tipo: " + tipoChamado);
        System.out.println("KM Rodado: " + kmRodado);
        System.out.printf("Valor Total Calculado: R$ %.2f%n", valorTotal);
        System.out.print("Confirmar chamado? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("s")) {
            System.out.println("Cadastro de chamado cancelado.");
            return;
        }

        // Registro final
        chamadoController.criarChamado(
            cliente, motorista, veiculo, origem, destino, data, tipoChamado,
            kmInicial, kmFinal, horaInicio, horaFim, valorTotal
        );
        System.out.println("Chamado registrado com sucesso!");
    }


    private void listarChamados() {
    	 System.out.println("\n======= Lista de Chamados =======");
        List<Chamado> chamados = chamadoController.listarChamados();
        if (chamados.isEmpty()) {
            System.out.println("Nenhum chamado registrado.");
        } else {
            for (Chamado c : chamados) {
                System.out.println("\n--- CHAMADO ID " + c.getIdChamado() + " ---");
                System.out.println(c);
            }
        }
    }

    private void editarChamado() {
       
        List<Chamado> chamados = chamadoController.listarChamados();

        if (chamados.isEmpty()) {
            System.out.println("Nenhum chamado registrado.");
            return;
        }
        System.out.println("\n======= CHAMADOS DISPONÍVEIS PARA EDIÇÃO =======");
        for (Chamado c : chamados) {
            System.out.println("ID " + c.getIdChamado() + ": " + c.getCliente().getNome() + " -> " + c.getDestino() + " (" + c.getData() + ")");
        }

        try {
            System.out.print("\nDigite o ID do chamado que deseja editar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Chamado chamado = chamadoController.buscarChamadoPorId(id);
            if (chamado == null) {
                System.out.println("Chamado não encontrado.");
                return;
            }

            System.out.print("Nova origem (Enter para manter: " + chamado.getOrigem() + "): ");
            String origem = scanner.nextLine();
            if (!origem.isBlank()) chamado.setOrigem(origem);

            System.out.print("Novo destino (Enter para manter: " + chamado.getDestino() + "): ");
            String destino = scanner.nextLine();
            if (!destino.isBlank()) chamado.setDestino(destino);

            System.out.print("Nova data (AAAA-MM-DD, Enter para manter: " + chamado.getData() + "): ");
            String dataStr = scanner.nextLine();
            if (!dataStr.isBlank()) chamado.setData(LocalDate.parse(dataStr));

            System.out.print("Novo tipo (COMUM, EXECUTIVO, VIAGEM, OUTROS) [Atual: " + chamado.getTipoChamado() + "]: ");
            String tipoStr = scanner.nextLine();
            if (!tipoStr.isBlank()) {
                try {
                    chamado.setTipoChamado(TipoChamado.valueOf(tipoStr.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipo inválido. Mantido o atual.");
                }
            }

            System.out.print("KM Inicial (Enter para manter: " + chamado.getKmInicial() + "): ");
            String kmInicial = scanner.nextLine();
            if (!kmInicial.isBlank()) chamado.setKmInicial(Double.parseDouble(kmInicial));

            System.out.print("KM Final (Enter para manter: " + chamado.getKmFinal() + "): ");
            String kmFinal = scanner.nextLine();
            if (!kmFinal.isBlank()) chamado.setKmFinal(Double.parseDouble(kmFinal));

            System.out.print("Hora início (HH:mm, Enter para manter: " + chamado.getHoraInicio() + "): ");
            String hInicio = scanner.nextLine();
            if (!hInicio.isBlank()) chamado.setHoraInicio(LocalTime.parse(hInicio));

            System.out.print("Hora fim (HH:mm, Enter para manter: " + chamado.getHoraFim() + "): ");
            String hFim = scanner.nextLine();
            if (!hFim.isBlank()) chamado.setHoraFim(LocalTime.parse(hFim));

            // Atualiza o valor total com base no novo km
            double kmRodado = chamado.getKmFinal() - chamado.getKmInicial();
            chamado.setValorTotal(kmRodado * 2.5); 

            chamadoController.editarChamado(chamado);
            System.out.println("\nChamado atualizado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        } catch (Exception e) {
            System.out.println("Erro ao editar chamado: " + e.getMessage());
        }
    }


    private void excluirChamado() {
        System.out.println("\n======= CHAMADOS DISPONÍVEIS PARA EXCLUSÃO =======");
        listarChamados();

        System.out.print("\nDigite o ID do chamado que deseja excluir: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Chamado chamado = chamadoController.buscarChamadoPorId(id);
        if (chamado == null) {
            System.out.println("Chamado não encontrado.");
            return;
        }

        System.out.print("Tem certeza que deseja excluir este chamado? (s/n): ");
        String confirmacao = scanner.nextLine();
        if (!confirmacao.equalsIgnoreCase("s")) {
            System.out.println("Exclusão cancelada.");
            return;
        }

        boolean excluido = chamadoController.excluirChamado(chamado);
        if (excluido) {
            System.out.println("Chamado excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir chamado.");
        }
    }



    private void listarPorCliente() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        List<Chamado> lista = chamadoController.listarChamadosPorCliente(nome);
        if (lista.isEmpty()) {
            System.out.println("Nenhum chamado encontrado para esse cliente.");
        } else {
            for (Chamado c : lista) {
                System.out.println("\n--- CHAMADO ID " + c.getIdChamado() + " ---");
                System.out.println(c);
            }
        }
    }

    private void listarPorMotorista() {
        System.out.print("Nome do motorista: ");
        String nome = scanner.nextLine();
        List<Chamado> lista = chamadoController.listarChamadosPorMotorista(nome);
        if (lista.isEmpty()) {
            System.out.println("Nenhum chamado encontrado para esse motorista.");
        } else {
            for (Chamado c : lista) {
                System.out.println("\n--- CHAMADO ID " + c.getIdChamado() + " ---");
                System.out.println(c);
            }
        }
    }

    public void exibirMenu() {
        int opcao = -1;
        do {
            System.out.println("\n=== MENU CHAMADOS ===");
            System.out.println("\n1. Criar Chamado");
            System.out.println("2. Listar Chamados");
            System.out.println("3. Listar Chamados por Cliente");
            System.out.println("4. Listar Chamados por Motorista");
            System.out.println("5. Editar Chamado");
            System.out.println("6. Excluir Chamado");           
            System.out.println("0. Voltar");
            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> criarChamado();
                    case 2 -> listarChamados();
                    case 3 -> listarPorCliente();
                    case 4 -> listarPorMotorista();
                    case 5 -> editarChamado();
                    case 6 -> excluirChamado();                   
                    case 0 -> System.out.println("\nVoltando ao menu principal...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        } while (opcao != 0);
    }
}
