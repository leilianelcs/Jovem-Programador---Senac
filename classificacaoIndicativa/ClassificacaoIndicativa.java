//Exercício de aula - 08/04/25

package exemploSwitch;

import java.util.Scanner;

public class ClassificacaoIndicativa {

	public static void main(String[] args) {
	// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite a classificação indicativa do filme (L, 10, 12, 14, 16 ou 18):");
        String classificacao = scanner.nextLine().toUpperCase();
        
        switch (classificacao) {
            case "L":
                System.out.println("O filme é Livre para todos os públicos.");
                break;
            case "10":
                System.out.println("Recomendado para maiores de 10 anos.");
                break;
            case "12":
                System.out.println("Recomendado para maiores de 12 anos.");
                break;
            case "14":
                System.out.println("Recomendado para maiores de 14 anos.");
                break;
            case "16":
                System.out.println("Recomendado para maiores de 16 anos.");
                break;
            case "18":
                System.out.println("Permitido apenas para maiores de 18 anos.");
                break;
            default:
                System.out.println("Classificação inválida! Digite apenas L, 10, 12, 14, 16 ou 18.");
        }
        
        scanner.close();

	}

}
