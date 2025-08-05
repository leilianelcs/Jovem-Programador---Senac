package sistemaTaxi.sistemaTaxi;

public class Validacao {
    
    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;
        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false;

        int soma = 0, peso = 10;
        for (int i = 0; i < 9; i++) soma += (cpf.charAt(i) - '0') * peso--;

        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) soma += (cpf.charAt(i) - '0') * peso--;

        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        return cpf.charAt(9) - '0' == primeiroDigito && cpf.charAt(10) - '0' == segundoDigito;
    }

    public static boolean validarTelefone(String telefone) {
        if (telefone == null) return false;
        telefone = telefone.replaceAll("[^\\d]", "");
        return telefone.matches("\\d{10,11}");
    }

    public static boolean validarCEP(String cep) {
        if (cep == null) return false;
        cep = cep.replaceAll("[^\\d]", "");
        return cep.matches("\\d{8}");
    }

    public static boolean validarRG(String rg) {
        return rg != null && rg.matches("\\d{5,10}");
    }

    public static boolean validarCNH(String cnh) {
        return cnh != null && cnh.matches("\\d{11}");
    }

    public static boolean validarPlaca(String placa) {
        return placa != null && placa.toUpperCase().matches("^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$");
    }
}
