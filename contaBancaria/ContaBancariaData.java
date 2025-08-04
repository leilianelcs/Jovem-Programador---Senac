//Exercício de aula - 24/04/25

package contaBancariaData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ContaBancaria {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bem-vindo à sua conta bancária!");
        
        double saldo = 0.0;
        int servico;
        String[] extrato = new String[10]; 
        int index = 0; 
        
        DateTimeFormatter mascaraCompleta = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
        
        do {
            System.out.println("\nOpções de serviços disponíveis:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Realizar saque");
            System.out.println("4. Ver extrato");
            System.out.println("5. Sair do sistema");
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
                        LocalDateTime dataAtualHora = LocalDateTime.now(); 
                        extrato[index] = "Depósito de R$ " + deposito + " - " + dataAtualHora.format(mascaraCompleta); 
                        index++;
                        System.out.println("\nDepósito bem-sucedido!");
                    } else {
                        System.out.println("\nOperação não permitida! Valor inválido!");
                    }                    
                    break;

                case 3:
                    System.out.print("\nDigite o valor do saque: R$");
                    double saque = scanner.nextDouble();
                    if (saque > 0) { 
                        if (saque <= saldo) {
                            saldo -= saque;
                            LocalDateTime dataAtualHora = LocalDateTime.now(); 
                            extrato[index] = "Saque de R$ " + saque + " - " + dataAtualHora.format(mascaraCompleta);
                            index++;
                            System.out.println("\nSaque efetuado!");
                        } else {
                            System.out.println("\nVocê não possui saldo suficiente para esta operação! Consulte seu saldo.");
                        }
                    } else {
                        System.out.println("\nValor inválido!");
                    }
                    break;

                case 4:
                    System.out.println("\nExtrato Bancário");
                    if (index == 0) {
                        System.out.println("\nNenhuma movimentação registrada");
                    } else {
                        for (int i = 0; i < index; i++) {
                            System.out.println(extrato[i]);
                        }
                    }
                    break;

                case 5:
                    System.out.println("\nSaída realizada com sucesso! Volte sempre!");
                    break;

                default:
                    System.out.println("\nDigite uma opção válida. Verifique as opções abaixo:");
            }
            
        } while (servico != 5);

        scanner.close();
    }
}

