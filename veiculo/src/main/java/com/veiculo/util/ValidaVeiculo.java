package com.veiculo.util; // Define o pacote utilitário da aplicação

import java.util.regex.Pattern; // Importa a classe Pattern para uso de expressões regulares

import com.veiculo.dto.VeiculoDTO;

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
    public static boolean isPlacaValida(VeiculoDTO dto) {
        dto.setPlaca(dto.getPlaca().replaceAll(" ", "").trim().toUpperCase());
        if (dto.getPlaca().length() == 7) {
            return PADRAO_ANTIGO.matcher(dto.getPlaca()).matches() || // Verifica se corresponde ao padrão antigo
                    PADRAO_MERCOSUL.matcher(dto.getPlaca()).matches(); // Verifica se corresponde ao padrão Mercosul
        }
        return false; // Verifica se a placa corresponde a algum dos formatos válidos
    }

}
