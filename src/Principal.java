import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import conta.Agencia;
import conta.Conta;
import conta.ContaCorrente;
import conta.ContaInvestimento;
import conta.ContaPoupanca;
import validador.SaldoInsuficienteException;
import validador.ValidaCPF;

public class Principal {
	public static Scanner scanner = new Scanner(System.in);
	public static ArrayList<Conta> contaList = new ArrayList<Conta>();
	static ContaCorrente conta = new ContaCorrente();
	static ContaPoupanca contaPoupanca = new ContaPoupanca();
	static ContaInvestimento contaInves =  new ContaInvestimento();
	static ValidaCPF  validaCpf = new ValidaCPF();
	
	public static void main(String[] args) throws SaldoInsuficienteException {
		// TODO Auto-generated method stub
		
		menu();	
		/*conta.setRendaMensal(1000);
		conta.depositar(350);
		System.out.println(conta.saldo());
		System.out.println(conta.getChequeEspecial());
		*/
		/*
		conta.setRendaMensal(1000);
		conta.depositar(350);
		conta.saque(500);
		System.out.println(conta.saldo());
		*/
	}
	
	public static void menu() throws SaldoInsuficienteException {
		String opcao= null;;
		do {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("----------------- Bem-vindo ao DevinMoney --------------------------");
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Digite 1 para cadastrar uma nova conta;\nDigite 2 para listar as contas já cadastradas;"
					+ "\nDigite 3 para ver os relatórios;\nDigite 4 para atualizar cadastro;\nDigite 5 para sair;");
			System.out.println("opção: ");
			opcao = scanner.next();
			switch(opcao) {
				case "1":
					System.out.println("Vamos a criação da conta");
					criaConta();
					System.out.println("--------------------------------------------------------------------");
					break;
				case "2":
					System.out.println("Você escolheu listar todas as contas");
					listContas();
					System.out.println("Deseja realizar alguma operação?\nDigite 1 para sim e 2 para não;");
					String opc = scanner.next();
					
					if (opc.equalsIgnoreCase("1")) {
						opc = null;
						do {
							System.out.println("digite 1 para Transferencia;\ndigite 2 para deposito;\n"
									+ "digite 3 para investimento;\ndigite 4 para verificar seu rendimento na poupança"
									+ "\ndigite 5 para ver seu saldo;\ndigite 6 para sacar;\ndigite 7 para sair;");
							opc = scanner.next();
							
							switch(opc) {
								case "1":
									System.out.println("Informe o ID da conta de origem: ");
									int id = scanner.nextInt();
									id -= 1;
									System.out.println("Informe o ID da conta de destino: ");
									int idDestino = scanner.nextInt();
									idDestino -= 1;
									System.out.println("Informe o valor: ");
									double valor = scanner.nextDouble();
									contaList.get(id).transferir(valor, contaList.get(idDestino), contaList.get(id));
									break;
								case "2":
									System.out.println("Qual agencia vc deseja depositar?\nDigite 1 para Florianópolis;"
											+ "\nDigite 2 para São José;");
									String op = scanner.next();
									if (op.equalsIgnoreCase("1")) {
										Agencia agencia = Agencia.FLORIANOPOLIS;
									}else {
										Agencia agencia = Agencia.SAO_JOSE;
									}
									System.out.println("Informe o ID de qual conta deseja realizar o deposito: ");
									int idDeposito =  scanner.nextInt();
									System.out.println("Informe o valor a ser depositado: ");
									double valorDeposito = scanner.nextDouble();
									contaList.get(idDeposito).depositar(valorDeposito);
									break;
									
								case "3":
									System.out.println("Vamos te mostrar os tipos de investimento");
									contaInves.consultaInveste();
									System.out.println("Deseja realizar algum investimento?\nDigite 1 para sim;\n Digite 2 para não;");
									opc =  scanner.next();
									if( opc.equalsIgnoreCase("1")) {
										System.out.println("Informe qual o investimento:\nDigite 1 para iniciante;"
												+ "\nDigite 2 para conservador;\nDigite 3 para Agressivo;");
										opc = scanner.next();
										if(opc.equalsIgnoreCase("1")) {
											do {
												System.out.println("informe um ID de uma conta do tipo CONTA INVESTIMENTO!");
												int idInves = scanner.nextInt();
												idInves -= 1;
												if (contaList.get(idInves).getTipoConta().equalsIgnoreCase("Conta Investimento")) {
													System.out.println("Informe o valor: ");
													double valorInves = scanner.nextDouble();
													((ContaInvestimento) contaList.get(idInves)).iniciante(valorInves);
													break;
												}else {
													System.out.println("Favor informe uma conta do Tipo CONTA INVESTIMENTO!");
												}
											}while(true);
										}else if(opc.equalsIgnoreCase("2")) {
											do {
												System.out.println("informe um ID de uma conta do tipo CONTA INVESTIMENTO!");
												int idInves = scanner.nextInt();
												idInves -= 1;
												if (contaList.get(idInves).getTipoConta().equalsIgnoreCase("Conta Investimento")) {
													System.out.println("Informe o valor: ");
													double valorInves = scanner.nextDouble();
													((ContaInvestimento) contaList.get(idInves)).conservador(valorInves);
													break;
												}else {
													System.out.println("Favor informe uma conta do Tipo CONTA INVESTIMENTO!");
												}
											}while(true);
									}else if(opc.equalsIgnoreCase("3")) {
										do {
											System.out.println("informe um ID de uma conta do tipo CONTA INVESTIMENTO!");
											int idInves = scanner.nextInt();
											idInves -= 1;
											if (contaList.get(idInves).getTipoConta().equalsIgnoreCase("Conta Investimento")) {
												System.out.println("Informe o valor: ");
												double valorInves = scanner.nextDouble();
												((ContaInvestimento) contaList.get(idInves)).agressivo(valorInves);
												break;
											}else {
												System.out.println("Favor informe uma conta do Tipo CONTA INVESTIMENTO!");
											}
										}while(true);
									}
								}
							case "4":
								//digete 4 para verificar seu rendimento na poupança
								valor = 0;
								do {
									try {
										System.out.println("De quanto é o valor que deseja simular?");
										valor = scanner.nextDouble();
										break;
									}catch(Exception e) {
										
									}
									
								}while(true);
								
								double juros = 0;
								
								do {
									try {
										System.out.println("De quanto é o juros anual?");
										juros = scanner.nextFloat();
										break;
									}catch(Exception e) {
										
									}
									
								}while(true);
								int periodo = 0;
								 
								do {
									try {
										System.out.println("Digite a quantidade de meses que deseja simular?");
										periodo = scanner.nextInt();
										break;
									}catch(Exception e) {
										
									}
									
								}while(true);
								System.out.println(contaPoupanca.rendimento(valor, juros, periodo));
							
								break;
							case "5":
								//ndigite 5 para ver seu saldo;
								listContas();
								System.out.println("Favor informe o ID da conta: ");
								int idSaldo = scanner.nextInt();
								contaList.get(idSaldo).saldo();
								break;
							case "6":
								listContas();
								System.out.println("Digite o id de qual conta deseja realizar saque: ");
								int idSaque = scanner.nextInt();
								System.out.println("Informe o valor: ");
								double valorSaque = scanner.nextDouble();
								idSaque -= 1;
								contaList.get(idSaque).saque(valorSaque);
								
								break;
							}
						}while(!(opc.equalsIgnoreCase("7")));
					}
					System.out.println("--------------------------------------------------------------------");
			case "3":
				do {
					System.out.println("Relatórios do Sistema:");
					System.out.println("Digite 1 para listar todas as CONTAS com saldo NEGATIVO;\nDigite 2 para ver o total investido;"
							+ "\nDigite 3 para ver TODAS as operacoes de um cliente;");
					String op = scanner.next();
					if (op.equalsIgnoreCase("1")) {
						contasNegativas();
						break;
					}else if(op.equalsIgnoreCase("2")) {
						totalInvestido();
						break;
					}else if (op.equalsIgnoreCase("3")) {
						listContas();
						do {
							System.out.println("informe um ID de uma conta do tipo CONTA POUPANÇA!");
							int idPoup = scanner.nextInt();
							System.out.println("contaList.get(idPoup).getOperacoes()");
						}while(true);
						
						
					}
				}while(true);
				break;
				
			case "4":
				listContas();
				System.out.println("Informe o ID de qual conta deseja alterar: ");
				int idAltera = scanner.nextInt();
				idAltera -= 1;
				System.out.println("Digite seu nome: ");
				String esy = scanner.nextLine();
				String nome = scanner.nextLine();
								
				System.out.println("Digite sua renda mensal: ");
				double rendaMensal = scanner.nextDouble();
				System.out.println("--------------------------------------------------------------");
				System.out.println("Agora, escolha de qual AGENCIA você vai quere abrir sua conta: ");
				opc =  null;
				do {
					System.out.println("Digite: 1 - para Florianópolis; 2 - para São José");
					opc = scanner.next();
				}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));
				Agencia agencia;
				if ( opc.equalsIgnoreCase("1")) {
					agencia = Agencia.FLORIANOPOLIS;
				}else {
					agencia = Agencia.SAO_JOSE;
				}
				System.out.println("--------------------------------------------------------------");
				System.out.println("Agora vamos gerar o número da sua CONTA.");
				conta.geraConta();
				System.out.println("O número da sua conta é: "+conta.getConta());
				System.out.println("--------------------------------------------------------------");
				contaList.get(idAltera).alteraCad(nome, rendaMensal, agencia);
				break;
			}
		}while(!(opcao.equalsIgnoreCase("5")));	
		System.out.println("O Banco DevInMoney agradece sua preferencia.");
	}
	public static void criaConta() {
		
		
		String opcao = null;
		
			
			do {
			
			System.out.println("Qual tipo de conta deseja cadastrar: ");
			System.out.println("Digite - 1 para Conta Corrente; 2 - para Conta Poupança; 3 - para Conta de Investimento:");
			
			try {
				opcao = scanner.next();
				
			}catch(InputMismatchException e) {
				
			
			}
			
			}while(!(opcao.equalsIgnoreCase("1") || opcao.equalsIgnoreCase("2") || opcao.equalsIgnoreCase("3")));
			
			if(opcao.equalsIgnoreCase("1")) {
				System.out.println("--------------------------------------------------------------");
				System.out.println("Vamos iniciar seu cadastro na CONTA CORRENTE. ");
				System.out.println("--------------------------------------------------------------");
				System.out.println();
				System.out.println("Digite seu nome: ");
				String nome = scanner.nextLine();
				conta.setNome(scanner.nextLine());
				System.out.println("Digite seu CPF: ");
				String cpf = scanner.nextLine();
				if (validaCpf.isCPF(cpf)) {
					conta.setCpf(cpf);
				}else {
					System.out.println("CPF invalido");
					criaConta();
				}
				
				System.out.println("Digite sua renda mensal: ");
				conta.setRendaMensal(scanner.nextDouble());
				System.out.println("--------------------------------------------------------------");
				System.out.println("Agora, escolha de qual AGENCIA você vai quere abrir sua conta: ");
				String opc =  null;
				do {
					System.out.println("Digite: 1 - para Florianópolis; 2 - para São José");
					opc = scanner.next();
				}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));
				
				if ( opc.equalsIgnoreCase("1")) {
					conta.setAgencia(Agencia.FLORIANOPOLIS);
				}else {
					conta.setAgencia(Agencia.SAO_JOSE);
				}
				System.out.println("--------------------------------------------------------------");
				System.out.println("Agora vamos gerar o número da sua CONTA CORRENTE.");
				conta.geraConta();
				System.out.println("O número da sua conta é: "+conta.getConta());
				System.out.println("--------------------------------------------------------------");
				opc = null;
				do {
				System.out.println("Deseja realizar algum deposito na sua conta? Se sim digete 1; não digite 2; ");
				opc = scanner.next();
				
				}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));
				double valor = 0;
				if ( opc.equalsIgnoreCase("1")) {
					do {
						try {
							System.out.println("Quanto deseja depositar?");
							valor = scanner.nextDouble();
							conta.depositar(valor);
							break;
						}catch(Exception e) {
							
						}
						conta.setSaldo(valor);;
					}while(true);
						
				}else {
					conta.setSaldo(0);
				}
				System.out.println("Conta finalizada com sucesso!");
				
				contaList.add(conta);
				
				
			}else if(opcao.equalsIgnoreCase("2")) {
				System.out.println("--------------------------------------------------------------");
				System.out.println("Vamos iniciar seu cadastro na CONTA POUPANÇA. ");
				System.out.println("--------------------------------------------------------------");
				System.out.println("Digite seu nome: ");
				String nome = scanner.nextLine();
				contaPoupanca.setNome(scanner.nextLine());
				System.out.println("Digite seu CPF: ");
				String cpf = scanner.nextLine();
				if (validaCpf.isCPF(cpf)) {
					contaPoupanca.setCpf(cpf);
				}else {
					System.out.println("CPF invalido");
					criaConta();
				}
				
				System.out.println("Digite sua renda mensal: ");
				contaPoupanca.setRendaMensal(scanner.nextDouble());
				System.out.println("--------------------------------------------------------------");
				System.out.println("Agora, escolha de qual AGENCIA você vai quere abrir sua conta: ");
				String opc =  null;
				do {
					System.out.println("Digite: 1 - para Florianópolis; 2 - para São José");
					opc = scanner.next();
				}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));
				
				if ( opc.equalsIgnoreCase("1")) {
					contaPoupanca.setAgencia(Agencia.FLORIANOPOLIS);
				}else {
					contaPoupanca.setAgencia(Agencia.SAO_JOSE);
				}
				System.out.println("--------------------------------------------------------------");
				System.out.println("Agora vamos gerar o número da sua CONTA POUPANÇA.");
				contaPoupanca.geraConta();
				System.out.println("O número da sua conta é: "+contaPoupanca.getConta());
				System.out.println("--------------------------------------------------------------");
				opc = null;
				do {
				System.out.println("Deseja realizar algum deposito na sua conta? Se sim digete 1; não digite 2; ");
				opc = scanner.next();
				
				}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));
				double valor = 0;
				if ( opc.equalsIgnoreCase("1")) {
					do {
						try {
							System.out.println("Quanto deseja depositar?");
							valor = scanner.nextDouble();
							contaPoupanca.depositar(valor);
							break;
						}catch(Exception e) {
							
						}
						contaPoupanca.setSaldo(valor);;
					}while(true);
						
				}else {
					contaPoupanca.setSaldo(0);
				}
				opc = null;
				do {
					System.out.println("Deseja verificar quanto será seu rendimento? Se sim digete 1; não digite 2; ");
					opc = scanner.next();
				
				}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));
				
				if ( opc.equalsIgnoreCase("1")) {
					valor = 0;
					do {
						try {
							System.out.println("De quanto é o valor que deseja simular?");
							valor = scanner.nextDouble();
							break;
						}catch(Exception e) {
							
						}
						
					}while(true);
					
					double juros = 0;
					
					do {
						try {
							System.out.println("De quanto é o juros anual?");
							juros = scanner.nextFloat();
							break;
						}catch(Exception e) {
							
						}
						
					}while(true);
					int periodo = 0;
					 
					do {
						try {
							System.out.println("Digite a quantidade de meses que deseja simular?");
							periodo = scanner.nextInt();
							break;
						}catch(Exception e) {
							
						}
						
					}while(true);
					System.out.println(contaPoupanca.rendimento(valor, juros, periodo));
				}
				System.out.println("Conta finalizada com Sucesso!");
				contaList.add(contaPoupanca);				
		
		
			} else if(opcao.equalsIgnoreCase("3")) {
				System.out.println("--------------------------------------------------------------");
				System.out.println("Vamos iniciar seu cadastro na CONTA DE INVESTIMENTO. ");
				System.out.println("--------------------------------------------------------------");
				System.out.println("Digite seu nome: ");
				String nome = scanner.nextLine();
				contaInves.setNome(scanner.nextLine());
				System.out.println("Digite seu CPF: ");
				String cpf = scanner.nextLine();
				if (validaCpf.isCPF(cpf)) {
					contaInves.setCpf(cpf);
				}else {
					System.out.println("CPF invalido");
					criaConta();
				}
				System.out.println("Digite sua renda mensal: ");
				contaInves.setRendaMensal(scanner.nextDouble());
				System.out.println("--------------------------------------------------------------");
				System.out.println("Agora, escolha de qual AGENCIA você vai quere abrir sua conta: ");
				String opc =  null;
				do {
					System.out.println("Digite: 1 - para Florianópolis; 2 - para São José");
					opc = scanner.next();
				}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));
				
				if ( opc.equalsIgnoreCase("1")) {
					contaInves.setAgencia(Agencia.FLORIANOPOLIS);
				}else {
					contaInves.setAgencia(Agencia.SAO_JOSE);
				}
				System.out.println("--------------------------------------------------------------");
				System.out.println("Agora vamos gerar o número da sua CONTA INVESTIMENTO.");
				contaInves.geraConta();
				System.out.println("O número da sua conta é: "+contaInves.getConta());
				System.out.println("--------------------------------------------------------------");
				opc = null;
				do {
				System.out.println("Deseja realizar algum deposito na sua conta? Se sim digete 1; não digite 2; ");
				opc = scanner.next();
				
				}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));
				double valor = 0;
				if ( opc.equalsIgnoreCase("1")) {
					do {
						try {
							System.out.println("Quanto deseja depositar?");
							valor = scanner.nextDouble();
							contaInves.depositar(valor);
							break;
						}catch(Exception e) {
							
						}
						contaInves.setSaldo(valor);;
					}while(true);
						
				}else {
					contaInves.setSaldo(0);
				}
				opc = null;
				do {
				System.out.println("Deseja consultar os tipos de investimento? Se sim digete 1; não digite 2; ");
				opc = scanner.next();
				
				}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));
				
				if ( opc.equalsIgnoreCase("1")) {
					contaInves.consultaInveste();
						
				}
				
				opc = null;
				do {
				System.out.println("Deseja realizar algum investimento ou simular? Para investir digite 1; "
						+ "para simular digite 2; para sair digite 3;; ");
				opc = scanner.next();
				
				}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2") || opc.equalsIgnoreCase("3")));
				
				if ( opc.equalsIgnoreCase("1")) {
					opc = null;
					do {
						System.out.println("Escolha:Digiete  1 - iniciante; 2 - para conservador; 3 - Agressivo; ");
						opc = scanner.next();
						
					}while(!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2") || opc.equalsIgnoreCase("3")));
					
					if (opc.equalsIgnoreCase("1")) {
						 valor = 0;
						 	
							do {
								try {
									System.out.println("Quanto deseja investir?");
									valor = scanner.nextDouble();
									break;
								}catch(Exception e) {
									
								}
								
							}while(true);
							contaInves.iniciante(valor);
							
					}else if (opc.equalsIgnoreCase("2")) {
						 valor = 0;
						 	
							do {
								try {
									System.out.println("Quanto deseja investir?");
									valor = scanner.nextDouble();
									break;
								}catch(Exception e) {
									
								}
								
							}while(true);
							contaInves.conservador(valor);
							
					}if (opc.equalsIgnoreCase("3")) {
						 valor = 0;
						 	
							do {
								try {
									System.out.println("Quanto deseja investir?");
									valor = scanner.nextDouble();
									break;
								}catch(Exception e) {
									
								}
								
							}while(true);
							contaInves.agressivo(valor);
				} else if (opc.equalsIgnoreCase("2")) {
					 valor = 0;
					 	
						do {
							try {
								System.out.println("Quanto deseja investir?");
								valor = scanner.nextDouble();
								break;
							}catch(Exception e) {
							
							}
							
						}while(true);
						contaInves.simulaInvestimento(valor);
				}else {
				}
					System.out.println("Você pode escolher um investimento quando quiser.");
				}
				contaList.add(contaInves);
				System.out.println("Conta cadastrada com sucesso.!");
			}	
	}
	
	public static void listContas() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------------------------");
		System.out.println("------------ Relatório de contas no sistema -------------------");
		System.out.println("---------------------------------------------------------------");
		System.out.println("ID       CONTA      NOME          TIPO DE CONTA           VALOR");
		for ( int i =0; i < contaList.size(); i ++) {
			
			if (contaList.get(i).getTipoConta().equalsIgnoreCase("Conta Corrente")) {
				System.out.println(1+i+"   |   "+contaList.get(i).getConta()+"   |   "+contaList.get(i).getNome()+"   |   "
			+contaList.get(i).getTipoConta()+"   |   "+((ContaCorrente)contaList.get(i)).saldo());
			}
			System.out.println(1+i+"   |   "+contaList.get(i).getConta()+"   |   "+contaList.get(i).getNome()+"   |   "
					+contaList.get(i).getTipoConta()+"   |   "+contaList.get(i).saldo());
		}
		
	}

	
	public static void contasNegativas() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------------------------");
		System.out.println("------------ Relatório das contas negativas no sistema ---------");
		System.out.println("---------------------------------------------------------------");
		
		for ( int i =0; i < contaList.size(); i ++) {
			
			if(contaList.get(i).getSaldo() < 0) {
				System.out.println(contaList.get(i).getConta()+"   |   "+contaList.get(i).getNome()+"   |   "
			+contaList.get(i).getTipoConta()+"   |   "+contaList.get(i).getSaldo());
			}
		}
		
	}

	
	public static void totalInvestido() {
		// TODO Auto-generated method stub
		double totalInvestido = 0;
		System.out.println("---------------------------------------------------------------");
		System.out.println("------------ Relatório do total investido ---------------------");
		System.out.println("---------------------------------------------------------------");
		for ( int i =0; i < contaList.size(); i ++) {
			
			if(contaList.get(i).getTipoConta().equalsIgnoreCase("Conta Investimento")) {
				totalInvestido += ((ContaInvestimento) contaList.get(i)).getTotalInvestido();
			}
		}
		
	}

	
	public static void operacoesCliente() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------------------------");
		System.out.println("------------ Relatório do total investido ---------------------");
		System.out.println("---------------------------------------------------------------");
		for ( int i =0; i < contaList.size(); i ++) {
			contaList.get(i).imprimiTransferencia();
		}
	}
	
}

