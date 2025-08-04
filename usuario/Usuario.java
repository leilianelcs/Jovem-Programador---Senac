//Exercício de aula - 15/04/25

package notasMedia;

import java.util.Scanner;
import java.util.Arrays;

public class Usuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite três números separados por espaço:");
        String entrada = scanner.nextLine();
        String[] partes = entrada.split(" ");

        double[] numeros = new double[3];
        for (int i = 0; i < 3; i++) {
            numeros[i] = Double.parseDouble(partes[i]);
        }

        Arrays.sort(numeros);

        System.out.println("Números em ordem crescente:");
        for (double numero : numeros) {
            System.out.printf("%.0f%n", numero);
        }

        scanner.close();
    }
}
