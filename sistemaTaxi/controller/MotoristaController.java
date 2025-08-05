package sistemaTaxi.controller;

import java.sql.SQLException;
import java.util.List;

public class MotoristaController {
     private MotoristaDAO motoristaDAO = new MotoristaDAO();    
      

    public void cadastrarMotorista(String nome, String cpf, String cnh, String telefone,
                                String logradouro, int numero, String cep,
                                String complemento, String estado, String cidade) {
        try {
            Endereco endereco = new Endereco(logradouro, numero, cep, complemento, estado, cidade);
            Motorista motorista = new Motorista(nome, cpf, cnh, telefone, endereco);
            motoristaDAO.inserir(motorista);
        } catch (SQLException e) {
            System.out.println("Erro ao criar motorista: " + e.getMessage());
        }
    }

    public List<Motorista> listarMotoristas() {
        try {        	
            return motoristaDAO.selecionarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao buscar motoristas: " + e.getMessage());
            return List.of();
        }
    }

    public void editarMotorista(Motorista motorista, String cpfOriginal) {
        try {
            motoristaDAO.atualizar(motorista, cpfOriginal);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar motorista: " + e.getMessage());
        }
    }


    public void excluirMotorista(String cpf) {
        try {
            motoristaDAO.excluir(cpf);
            System.out.println("Motorista excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir motorista: " + e.getMessage());
        }
    }

    public Motorista buscarMotoristaPorNome(String nome) {
        for (Motorista m : listarMotoristas()) {
            if (m.getNome().equalsIgnoreCase(nome)) {
                return m;
            }
        }
        return null;
    }

    public Motorista buscarMotoristaPorCnh(String cnh) {
        try {
            return motoristaDAO.buscarPorCnh(cnh);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar motorista por CNH: " + e.getMessage());
            return null;
        }
    }
}
