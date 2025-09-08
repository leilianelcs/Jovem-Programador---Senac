package com.veiculo.util; // Define o pacote utilitário da aplicação

import java.util.regex.Pattern; // Importa a classe Pattern para uso de expressões regulares

// Classe utilitária para validação de placas de veículos brasileiras.
// Suporta formato antigo: AAA1234 (3 letras + 4 dígitos)
// Suporta formato Mercosul: AAA1A34 (3 letras + 1 dígito + 1 letras + 2 dígitos)
public final class ValidaVeiculo { // Classe final pois será somente utilizada contendo métodos utilitários

    private static final Pattern PADRAO_ANTIGO = Pattern.compile("^[A-Z]{3}[0-9]{4}$");
    private static final Pattern PADRAO_MERCOSUL = Pattern.compile("[A-Z]{3}[0-9][A-Z][0-9]{2}$");

    private ValidaVeiculo() { // Construtor privado para impedir instanciação
        // Classe utilitária, não deve ser instanciada
    }

    // Método para validar placas de veículos em dois formatos aceitos.
    public static boolean isPlacaValida(String placa) {
        if (placa == null) { // Rejeita caso seja nula
            return false; // Retorna falso se a placa for nula ou vazia
        }
        String normalizada = placa.trim().toUpperCase(); // Remove espaços e converte para maiúsculas
        if (normalizada.length() == 7) { // só continua se tiver exatamnte 7 caracteres
            // matches() verifica se a string inteira segue exatmante o padrao
            return PADRAO_ANTIGO.matcher(normalizada).matches() || // Verifica se corresponde ao padrão antigo
                    PADRAO_MERCOSUL.matcher(normalizada).matches(); // Verifica se corresponde ao padrão Mercosul
        }
        return false; // Verifica se a placa corresponde a algum dos formatos válidos
    }

}
