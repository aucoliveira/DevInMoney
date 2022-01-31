import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.Conta;
import conta.ContaCorrente;
import conta.ContaInvestimento;
import conta.ContaPoupanca;
import validador.ValidaCPF;

public class PrincipalTeste {
	public static Scanner scanner = new Scanner(System.in);
	public static ArrayList<Conta> contaList = new ArrayList<Conta>();
	static ContaCorrente conta = new ContaCorrente();
	static ContaPoupanca contaPoupanca = new ContaPoupanca();
	static ContaInvestimento contaInves =  new ContaInvestimento();
	static ValidaCPF  validaCpf = new ValidaCPF();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double valor = 0;
	 	
		
			
			try {
				System.out.println("Quanto deseja investir?");
				valor = scanner.nextDouble();
				//break;
			}catch(InputMismatchException e) {
				System.out.println("Deu erro");
				
			}
			
		
		//contaInves.simulaInvestimento(valor);

	}
	
	

}
