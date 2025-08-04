//Exercício de aula - 22/04/25

package contaBancaria;
import java.util.Scanner;

public class ContaBancaria {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Bem-vindo à sua conta bancária!");
		
        double saldo = 0.0;
        int servico;
        
        do {
            System.out.println("\nOpções de serviços disponíveis:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Realizar saque");
            System.out.println("4. Sair do sistema");
            System.out.print("\nDigite a opção desejada: ");
            servico = scanner.nextInt();

            switch (servico) {
                case 1:
                    System.out.println("\nO saldo atual é de R$" + saldo);
                    break;
                case 2:
                    System.out.print("\nQual o valor do depósito? R$");
                    double deposito = scanner.nextDouble();                   
                    if (deposito > 0) {
                    	 saldo += deposito;
                         System.out.println("\nDepósito bem sucedido!");
					} else {
						System.out.print("\nOperação não permitida! Valor inválido!");
					}                    
                   
                    break;
                case 3:
                    System.out.print("\nDigite o valor do saque: R$");
                    double saque = scanner.nextDouble();
                    if (saque > 0) { 
                        if (saque <= saldo) {
                            saldo -= saque;
                            System.out.println("\nSaque efetuado!");
                        } else {
                            System.out.println("\nVocê não possui saldo suficiente para esta operação! Consulte seu saldo.");
                        }
                    } else {
                        System.out.println("\nValor inválido!");
                    }
                    break;
                case 4:
                    System.out.println("\nSaída realizada com sucesso! Volte sempre!");
                    break;
                default:
                    System.out.println("\nDigite uma opção válida. Verifique as opções abaixo:");
            }
            
        } while (servico != 4);

        scanner.close();
		
	}

}