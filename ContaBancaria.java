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
            System.out.println("Opções de serviços disponíveis:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Realizar saque");
            System.out.println("4. Sair do sistema");
            System.out.print("Digite a opção desejada: ");
            servico = scanner.nextInt();

            switch (servico) {
                case 1:
                    System.out.println("O saldo atual é de R$" + saldo);
                    break;
                case 2:
                    System.out.print("Qual o valor do depósito? R$");
                    double deposito = scanner.nextDouble();
                    saldo += deposito;
                    System.out.println("Depósito bem sucedido!");
                    break;
                case 3:
                    System.out.print("Digite o valor do saque: R$");
                    double saque = scanner.nextDouble();
                    if (saque <= saldo) {
                        saldo -= saque;
                        System.out.println("Saque efetuado!");
                    } else {
                        System.out.println("Você não possui saldo suficiente para esta operação! Consulte seu saldo.");
                    }
                    break;
                case 4:
                    System.out.println("Saída realizada com sucesso! Volte sempre!");
                    break;
                default:
                    System.out.println("Digite uma opção válida. Verifique as opções abaixo:");
            }
        } while (servico != 4);

        scanner.close();
		
	}

}

