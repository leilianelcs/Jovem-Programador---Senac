//Exercício de aula - 17/04/25
package notasMedia;

import java.util.Scanner;

public class NotasMedia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		
		double soma = 0;
		int i = 1;
		
		do {
			System.out.println("Digite a " + i + "ª nota");
			double nota = scanner.nextDouble();
			//soma = soma + nota;(isso equivale o mesmo do exemplo a baixo)
			soma += nota;	
			i++; //encrementar o i (mesma coisa que i = i + 1;)
			
		} while (i <= 4 );
		double media = soma/4;
		System.out.println("Média Final: " + media);
		
		scanner.close();
			
		int mediaArredondada = (int) Math.round(media);
		
		switch (mediaArredondada) {
		case 9, 10 : {
			System.out.println("Classificação: Excelente!");
			break;
		}
		case 8, 7 : {
			System.out.println("Classificação: Aprovado!");
			break;
		}
		case 5, 6 : {
			System.out.println("Classificação: Recuperação!");
			break;			
		}
		default:
			System.out.println("Classificação: Reprovado!");
		
		}
	}

}

