//Exercício de aula - 08/04/25
package notasMedia;

import java.util.Scanner;
import java.util.Arrays;

public class UsuarioDoWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] numeros = new double[3];
        boolean entradaValida;
 
        do {
            System.out.println("Digite três números separados por espaço:");
            String entrada = scanner.nextLine();
            String[] partes = entrada.split(" ");

            if (partes.length == 3) {
                try {
                    for (int i = 0; i < 3; i++) {
                        numeros[i] = Double.parseDouble(partes[i]);
                    }
                    entradaValida = true;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Certifique-se de digitar três números corretamente.");
                    entradaValida = false;
                }
            } else {
                System.out.println("Você deve inserir três números separados por espaço");
                entradaValida = false;
            }
        } while (!entradaValida);

        Arrays.sort(numeros);

        System.out.println("Números em ordem crescente:");
        for (double numero : numeros) {
            System.out.printf("%.0f%n", numero);
        }

        
        scanner.close();
    }
}
