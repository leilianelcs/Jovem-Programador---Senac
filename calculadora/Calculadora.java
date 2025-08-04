//Exercício de aula - 03/04/25

package main;

import java.util.Scanner;

public class Main {
	 public static void main(String[] args) {
	
	Scanner scanner = new Scanner(System.in);

    System.out.print("Digite o primeiro número: ");
    Float num1 = scanner.nextFloat();
    
    System.out.print("Digite o segundo número: ");
    Float num2 = scanner.nextFloat();
    
    System.out.println("Digite o valor correspondente para a : ");
    System.out.println("Digite 1 para soma ");
    System.out.println("Digite 2 para subtração ");
    System.out.println("Digite 3 para multiplicação ");
    System.out.println("Digite 4 para divisão ");
    
    int operador = scanner.nextInt();
    
    scanner.close();
    
   if(operador >=1 && operador <=4) {
    if(operador == 1) {
    	System.out.println("Resultado da soma dos valores é: " + (num1 + num2));
    }else if(operador == 2) {
    	System.out.println("Resultado da subtação dos valores é: " + (num1 - num2));
    }else if(operador == 3) {
    	System.out.println("Resultado da multiplicação dos valores é: " + (num1 * num2));
    }else {
    	System.out.println("Resultado da divisão dos valores é: " + (num1 / num2));
    }
   }else {
		System.err.println("Ops! Algo deu errado com o operador digitado.");
   }
   }   
}
	 
