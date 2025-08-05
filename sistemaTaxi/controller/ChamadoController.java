package sistemaTaxi;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ChamadoController {
     private final ChamadoDAO chamadoDAO = new ChamadoDAO();

    public void criarChamado(Cliente cliente, Motorista motorista, Veiculo veiculo,
                              String origem, String destino, LocalDate data, TipoChamado tipoChamado,
                              double kmInicial, double kmFinal, LocalTime horaInicio, LocalTime horaFim,
                              double valorTotal) {
        Chamado chamado = new Chamado(cliente, motorista, veiculo,
                origem, destino, data, tipoChamado,
                kmInicial, kmFinal, horaInicio, horaFim, valorTotal);
        try {
            chamadoDAO.inserir(chamado);            
        } catch (Exception e) {
            System.out.println("Erro ao criar chamado: " + e.getMessage());
        }
    }
    
    
    public List<Chamado> listarChamados() {
        return chamadoDAO.listarChamados();
    }

    public List<Chamado> listarChamadosPorCliente(String nomeCliente) {
        return chamadoDAO.listarChamadosPorCliente(nomeCliente);
    }

    public List<Chamado> listarChamadosPorMotorista(String nomeMotorista) {
        return chamadoDAO.listarChamadosPorMotorista(nomeMotorista);
    }
    
    public void editarChamado(Chamado chamado) {
        try {
            chamadoDAO.atualizar(chamado);
        } catch (Exception e) {
            System.out.println("Erro ao editar chamado: " + e.getMessage());
        }
    }
    
    public Chamado buscarChamadoPorId(int id) {
        try {
            List<Chamado> chamados = chamadoDAO.listarChamados();
            for (Chamado c : chamados) {
                if (c.getIdChamado() == id) {
                    return c;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar chamado por ID: " + e.getMessage());
        }
        return null;
    }


    public boolean excluirChamado(Chamado chamado) {
        try {
            chamadoDAO.excluirChamado(chamado);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir chamado: " + e.getMessage());
            return false;
        }
    }


}
