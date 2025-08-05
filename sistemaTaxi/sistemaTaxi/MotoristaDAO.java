package sistemaTaxi.sistemaTaxi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistemaTaxi.conexao.ConnectionFactory;

public class MotoristaDAO {
    
    public void inserir(Motorista motorista) throws SQLException {
        String sql = "INSERT INTO motoristas (nome, cpf, cnh, telefone, logradouro, numero, cep, complemento, estado, cidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCpf());
            stmt.setString(3, motorista.getCnh());
            stmt.setString(4, motorista.getTelefone());
            stmt.setString(5, motorista.getEndereco().getLogradouro());
            stmt.setInt(6, motorista.getEndereco().getNumero());
            stmt.setString(7, motorista.getEndereco().getCep());
            stmt.setString(8, motorista.getEndereco().getComplemento());
            stmt.setString(9, motorista.getEndereco().getEstado());
            stmt.setString(10, motorista.getEndereco().getCidade());

            stmt.executeUpdate();
        }
    }

    public List<Motorista> selecionarTodos() throws SQLException {
        List<Motorista> motoristas = new ArrayList<>();
        String sql = "SELECT * FROM motoristas";
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
                Motorista motorista = new Motorista(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("cnh"),
                    rs.getString("telefone"),
                    endereco
                );
                motorista.setIdMotorista(rs.getInt("idmotorista")); // Correção
                motoristas.add(motorista);
            }
        }
        return motoristas;
    }

    public void atualizar(Motorista motorista, String cpfOriginal) throws SQLException {
        String sql = "UPDATE motoristas SET nome=?, cpf=?, cnh=?, telefone=?, logradouro=?, numero=?, cep=?, complemento=?, estado=?, cidade=? WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCpf());
            stmt.setString(3, motorista.getCnh());
            stmt.setString(4, motorista.getTelefone());
            stmt.setString(5, motorista.getEndereco().getLogradouro());
            stmt.setInt(6, motorista.getEndereco().getNumero());
            stmt.setString(7, motorista.getEndereco().getCep());
            stmt.setString(8, motorista.getEndereco().getComplemento());
            stmt.setString(9, motorista.getEndereco().getEstado());
            stmt.setString(10, motorista.getEndereco().getCidade());
            stmt.setString(11, cpfOriginal); // <- chave original

            stmt.executeUpdate();
        }
    }

    public void excluir(String cpf) throws SQLException {
        String sql = "DELETE FROM motoristas WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    public List<Motorista> listarMotoristas() {
        try {
            return selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar motoristas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Motorista buscarPorCnh(String cnh) throws SQLException {
        String sql = "SELECT * FROM motoristas WHERE cnh = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnh);
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
                    Motorista motorista = new Motorista(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("cnh"),
                        rs.getString("telefone"),
                        endereco
                    );
                    motorista.setIdMotorista(rs.getInt("idmotorista")); // Correção
                    return motorista;
                }
            }
        }
        return null;
    }

    public Motorista buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM motoristas WHERE nome = ?";
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
                    Motorista motorista = new Motorista(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("cnh"),
                        rs.getString("telefone"),
                        endereco
                    );
                    motorista.setIdMotorista(rs.getInt("idmotorista")); // Correção
                    return motorista;
                }
            }
        }
        return null;
    }
}
