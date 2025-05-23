package atendimentoHospital;

import java.util.ArrayList;
import java.util.List;

public class ListaHospital {
	
	    private List<Paciente> preferencial = new ArrayList<>();
	    private List<Paciente> normal = new ArrayList<>();

	    public void adicionarPaciente(Paciente paciente) {
	        if (paciente.getTipo() == TipoAtendimento.PREFERENCIAL) {
	            preferencial.add(paciente);
	        } else {
	            normal.add(paciente);
	        }
	    }

	    public Paciente chamarProximo() {
	        if (!preferencial.isEmpty()) {
	            return preferencial.remove(0);
	        } else if (!normal.isEmpty()) {
	            return normal.remove(0);
	        } else {
	            return null;
	        }
	    }

	    public void exibirListas() {
	    	 System.out.println("\n======= LISTA DE PACIENTES AGUARDANDO ATENDIMENTO =======");
	        System.out.println("\nTotal de pacientes PREFERENCIAIS cadastrados: " + preferencial.size());
	        for (Paciente p : preferencial) {
	            System.out.println("- " + p);
	        }

	        System.out.println("\nTotal de pacientes NORMAIS cadastrados: " + normal.size());
	        for (Paciente p : normal) {
	            System.out.println("- " + p);
	        }
	    }
	
	}

