package sistemaTaxi.sistemaTaxi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistemaTaxi.conexao.ConnectionFactory;

public class VeiculoDAO {
    

       public void adicionarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculos (placa, modelo, ano, cor, marca) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setString(4, veiculo.getCor());
            stmt.setString(5, veiculo.getMarca());

            stmt.executeUpdate();
        }
    }
       
       public void editarVeiculo(Veiculo veiculo, String placaOriginal) throws SQLException {
    	    String sql = "UPDATE veiculos SET placa = ?, modelo = ?, ano = ?, cor = ?, marca = ? WHERE placa = ?";
    	    try (Connection conn = ConnectionFactory.getConnection();
    	         PreparedStatement stmt = conn.prepareStatement(sql)) {

    	        stmt.setString(1, veiculo.getPlaca());
    	        stmt.setString(2, veiculo.getModelo());
    	        stmt.setInt(3, veiculo.getAno());
    	        stmt.setString(4, veiculo.getCor());
    	        stmt.setString(5, veiculo.getMarca());
    	        stmt.setString(6, placaOriginal);

    	        stmt.executeUpdate();
    	    }
    	}


    public void excluirVeiculo(String placa) throws SQLException {
        String sql = "DELETE FROM veiculos WHERE placa = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, placa);
            stmt.executeUpdate();
        }
    }
   
    public List<Veiculo> selecionarTodos() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculos";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getInt("ano"),
                    rs.getString("cor"),
                    rs.getString("marca")
                );
                veiculo.setIdVeiculo(rs.getInt("idveiculo"));
                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }

    // Listagem segura com fallback
    public List<Veiculo> listarVeiculos() {
        try {
            return selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Buscar veículo por placa
    public Veiculo buscarPorPlaca(String placa) {
        String sql = "SELECT * FROM veiculos WHERE placa = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Veiculo veiculo = new Veiculo(
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getInt("ano"),
                    rs.getString("cor"),
                    rs.getString("marca")
                );
                veiculo.setIdVeiculo(rs.getInt("idveiculo"));
                return veiculo;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar veículo por placa: " + e.getMessage());
        }
        return null;
    }

    // Remover veículo por placa
    public boolean removerPorPlaca(String placa) {
        try {
            Veiculo veiculo = buscarPorPlaca(placa);
            if (veiculo != null) {
                excluirVeiculo(placa);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover veículo: " + e.getMessage());
        }
        return false;
    }
}
