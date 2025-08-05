package sistemaTaxi.controller;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
     private ClienteDAO clienteDAO = new ClienteDAO();					

    public void cadastrarCliente(String nome, String cpf, String rg, String telefone, String logradouro, int numero, String cep, String complemento, String estado, String cidade) {
        try {
            Endereco endereco = new Endereco(logradouro, numero, cep, complemento, estado, cidade);
            Cliente cliente = new Cliente(nome, cpf, rg, telefone, endereco);
            clienteDAO.inserir(cliente);
        } catch (SQLException e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarClientes() {
        try {
            return clienteDAO.selecionarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao buscar clientes: " + e.getMessage());
            return List.of(); 
        }
    }

    // ✅ Atualizado: recebe CPF original
    public void editarCliente(Cliente cliente, String cpfOriginal) {
        try {
            clienteDAO.atualizarCliente(cliente, cpfOriginal);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void excluirCliente(String cpf) {
        try {
            clienteDAO.excluir(cpf);
            System.out.println("Cliente excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }


    public Cliente buscarClientePorNome(String nome) {
        for (Cliente c : clienteDAO.listarClientes()) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    public Cliente buscarClientePorCpf(String cpf) {
        try {
            return clienteDAO.buscarPorCpf(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por CPF: " + e.getMessage());
            return null;
        }
    }

    public void removerClientePorCpf(String cpf) {
        try {
            Cliente cliente = clienteDAO.buscarPorCpf(cpf);
            if (cliente != null) {
                clienteDAO.excluir(cliente.getCpf());
                System.out.println("Cliente removido com sucesso.");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
        }
    }
}
