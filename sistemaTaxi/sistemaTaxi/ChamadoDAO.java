package sistemaTaxi.sistemaTaxi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ChamadoDAO {
    

    public void inserir(Chamado chamado) throws SQLException {
        String sql = """
            INSERT INTO chamados (idcliente, idmotorista, idveiculo, origem, destino,
            data_chamado, tipo_chamado, km_inicial, km_final, hora_inicio, hora_fim, valor_total)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, chamado.getCliente().getIdCliente());
            stmt.setInt(2, chamado.getMotorista().getIdMotorista());
            stmt.setInt(3, chamado.getVeiculo().getIdVeiculo());
            stmt.setString(4, chamado.getOrigem());
            stmt.setString(5, chamado.getDestino());
            stmt.setDate(6, Date.valueOf(chamado.getData()));
            stmt.setString(7, chamado.getTipoChamado().toString());
            stmt.setDouble(8, chamado.getKmInicial());
            stmt.setDouble(9, chamado.getKmFinal());
            stmt.setTime(10, Time.valueOf(chamado.getHoraInicio()));
            stmt.setTime(11, Time.valueOf(chamado.getHoraFim()));
            stmt.setDouble(12, chamado.getValorTotal());

            stmt.executeUpdate();
        }
    }

    public List<Chamado> selecionarTodos() throws SQLException {
        List<Chamado> chamados = new ArrayList<>();
        String sql = "SELECT * FROM chamados";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = buscarClientePorId(rs.getInt("idcliente"));
                Motorista motorista = buscarMotoristaPorId(rs.getInt("idmotorista"));
                Veiculo veiculo = buscarVeiculoPorId(rs.getInt("idveiculo"));

                Chamado chamado = new Chamado(
                    cliente,
                    motorista,
                    veiculo,
                    rs.getString("origem"),
                    rs.getString("destino"),
                    rs.getDate("data_chamado").toLocalDate(),
                    TipoChamado.valueOf(rs.getString("tipo_chamado")),
                    rs.getDouble("km_inicial"),
                    rs.getDouble("km_final"),
                    rs.getTime("hora_inicio").toLocalTime(),
                    rs.getTime("hora_fim").toLocalTime(),
                    rs.getDouble("valor_total")
                );

                chamado.setIdChamado(rs.getInt("idchamado"));

                chamados.add(chamado);
            }
        }

        return chamados;
    }

    public List<Chamado> listarChamados() {
        try {
            return selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar chamados: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Chamado> listarChamadosPorCliente(String nomeCliente) {
        List<Chamado> resultado = new ArrayList<>();
        for (Chamado c : listarChamados()) {
            if (c.getCliente().getNome().equalsIgnoreCase(nomeCliente)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Chamado> listarChamadosPorMotorista(String nomeMotorista) {
        List<Chamado> resultado = new ArrayList<>();
        for (Chamado c : listarChamados()) {
            if (c.getMotorista().getNome().equalsIgnoreCase(nomeMotorista)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    private Cliente buscarClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE idcliente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

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
        return null;
    }

    private Motorista buscarMotoristaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM motoristas WHERE idmotorista = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

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
                motorista.setIdMotorista(rs.getInt("idmotorista"));
                return motorista;
            }
        }
        return null;
    }

    private Veiculo buscarVeiculoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM veiculos WHERE idveiculo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
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
        }
        return null;
    }

    public void atualizar(Chamado chamado) throws SQLException {
        String sql = """
            UPDATE chamados SET origem = ?, destino = ?, data_chamado = ?, tipo_chamado = ?, 
            km_inicial = ?, km_final = ?, hora_inicio = ?, hora_fim = ?, valor_total = ?
            WHERE idchamado = ?
        """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, chamado.getOrigem());
            stmt.setString(2, chamado.getDestino());
            stmt.setDate(3, Date.valueOf(chamado.getData()));
            stmt.setString(4, chamado.getTipoChamado().toString());
            stmt.setDouble(5, chamado.getKmInicial());
            stmt.setDouble(6, chamado.getKmFinal());
            stmt.setTime(7, Time.valueOf(chamado.getHoraInicio()));
            stmt.setTime(8, Time.valueOf(chamado.getHoraFim()));
            stmt.setDouble(9, chamado.getValorTotal());
            stmt.setInt(10, chamado.getIdChamado());

            stmt.executeUpdate();
        }
    }

    public void excluirChamado(Chamado chamado) throws SQLException {
        String sql = "DELETE FROM chamados WHERE idchamado = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, chamado.getIdChamado());
            stmt.executeUpdate();
        }
    }
}
