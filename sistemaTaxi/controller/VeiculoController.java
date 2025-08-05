package sistemaTaxi.controller;

import java.sql.SQLException;
import java.util.List;

public class VeiculoController {
    
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    public void cadastrarVeiculo(String placa, String modelo, int ano, String cor, String marca) {
        try {
            Veiculo veiculo = new Veiculo(placa, modelo, ano, cor, marca);
            veiculoDAO.adicionarVeiculo(veiculo);           
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar veículo: " + e.getMessage());
        }
    }

    public List<Veiculo> listarVeiculos() {
        try {
            return veiculoDAO.listarVeiculos();
        } catch (Exception e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
            return List.of();
        }
    }

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        try {
            return veiculoDAO.buscarPorPlaca(placa);
        } catch (Exception e) {
            System.out.println("Erro ao buscar veículo: " + e.getMessage());
            return null;
        }
    }

    public void editarVeiculo(Veiculo veiculo, String placaOriginal) {
        try {
            veiculoDAO.editarVeiculo(veiculo, placaOriginal);
        } catch (SQLException e) {
            System.out.println("Erro ao editar veículo: " + e.getMessage());
        }
    }

    
    public void removerVeiculoPorPlaca(String placa) {
        try {
            boolean removido = veiculoDAO.removerPorPlaca(placa);
            if (removido) {
                System.out.println("Veículo removido com sucesso!");
            } else {
                System.out.println("Veículo não encontrado ou não pôde ser removido.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover veículo: " + e.getMessage());
        }
    }
}
