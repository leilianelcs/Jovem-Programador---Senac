package sistemaTaxi.sistemaTaxi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, cpf, rg, telefone, logradouro, numero, cep, complemento, estado, cidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco().getLogradouro());
            stmt.setInt(6, cliente.getEndereco().getNumero());
            stmt.setString(7, cliente.getEndereco().getCep());
            stmt.setString(8, cliente.getEndereco().getComplemento());
            stmt.setString(9, cliente.getEndereco().getEstado());
            stmt.setString(10, cliente.getEndereco().getCidade());

            stmt.executeUpdate();
        }
    }

    public List<Cliente> selecionarTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Endereco endereco = new Endereco(
                    rs.getString("logradouro"),
                    rs.getInt("numero"),
                    rs.getString("cep"),
                    rs.getString("complemento"),
                    rs.getString("estado"),
                    rs.getString("cidade")
                );
                Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getString("telefone"),
                    endereco
                );
                cliente.setIdCliente(rs.getInt("idcliente")); 
                clientes.add(cliente);
            }
        }
        return clientes;
    }
    public void atualizarCliente(Cliente cliente, String cpfOriginal) throws SQLException {
        String sql = "UPDATE clientes SET nome=?, cpf=?, rg=?, telefone=?, logradouro=?, numero=?, cep=?, complemento=?, estado=?, cidade=? WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco().getLogradouro());
            stmt.setInt(6, cliente.getEndereco().getNumero());
            stmt.setString(7, cliente.getEndereco().getCep());
            stmt.setString(8, cliente.getEndereco().getComplemento());
            stmt.setString(9, cliente.getEndereco().getEstado());
            stmt.setString(10, cliente.getEndereco().getCidade());
            stmt.setString(11, cpfOriginal);

            stmt.executeUpdate();
        }
    }


    public void excluir(String cpf) throws SQLException {
        String sql = "DELETE FROM clientes WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    public List<Cliente> listarClientes() {
        try {
            return selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Cliente buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = new Endereco(
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        rs.getString("cep"),
                        rs.getString("complemento"),
                        rs.getString("estado"),
                        rs.getString("cidade")
                    );
                    Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("rg"),
                        rs.getString("telefone"),
                        endereco
                    );
                    cliente.setIdCliente(rs.getInt("idcliente")); 
                    return cliente;
                }
            }
        }
        return null;
    }

    public Cliente buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE nome = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = new Endereco(
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        rs.getString("cep"),
                        rs.getString("complemento"),
                        rs.getString("estado"),
                        rs.getString("cidade")
                    );
                    Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("rg"),
                        rs.getString("telefone"),
                        endereco
                    );
                    cliente.setIdCliente(rs.getInt("idcliente"));
                    return cliente;
                }
            }
        }
        return null;
    }
}
