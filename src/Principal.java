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
	static ContaInvestimento contaInves = new ContaInvestimento();
	static ValidaCPF validaCpf = new ValidaCPF();

	public static void main(String[] args) throws SaldoInsuficienteException {
		// TODO Auto-generated method stub

		menu();

	}

	public static void menu() throws SaldoInsuficienteException {
		String opcao = null;
		;
		do {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("----------------- Bem-vindo ao DevinMoney --------------------------");
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Digite 1 para cadastrar uma nova conta;\nDigite 2 para listar as contas já cadastradas;"
					+ "\nDigite 3 para ver os relatórios;\nDigite 4 para atualizar cadastro;\nDigite 5 para sair;");
			System.out.println("opção: ");
			opcao = scanner.next();
			switch (opcao) {
			case "1":

				opcao = null;

				do {

					System.out.println("Qual tipo de conta deseja cadastrar: ");
					System.out.println(
							"Digite - 1 para Conta Corrente; 2 - para Conta Poupança; 3 - para Conta de Investimento:");

					try {
						opcao = scanner.next();

					} catch (InputMismatchException e) {
						System.out.println("Informe um valor válido.");
					}

				} while (!(opcao.equalsIgnoreCase("1") || opcao.equalsIgnoreCase("2") || opcao.equalsIgnoreCase("3")));

				if (opcao.equalsIgnoreCase("1")) {
					System.out.println("--------------- Conta Corrente ----------------------");
					criaConta(conta);
				} else if (opcao.equalsIgnoreCase("2")) {
					System.out.println("--------------- Conta Poupança ----------------------");
					criaConta(contaPoupanca);
				} else if (opcao.equalsIgnoreCase("3")) {
					System.out.println("--------------- Conta de Investimento  --------------");
					criaConta(contaInves);
				}

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

						switch (opc) {
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
							} else {
								Agencia agencia = Agencia.SAO_JOSE;
							}
							int idDeposito = 0;
							do {
								try {	
									System.out.println("Informe o ID de qual conta deseja realizar o deposito: ");
									idDeposito = scanner.nextInt();
									break;
								} catch (Exception e) {
									System.out.println("Informe um valor válido.");
								}
							} while (true);
							double valorDeposito = 0;
							do {
								try {
									System.out.println("Informe o valor a ser depositado: ");
									valorDeposito = scanner.nextDouble();
									break;
								} catch (Exception e) {
									System.out.println("Informe um valor válido.");
								}
							} while (true);
							
							idDeposito -= 1;
							contaList.get(idDeposito).depositar(valorDeposito);
							break;

						case "3":
							System.out.println("Vamos te mostrar os tipos de investimento");
							contaInves.consultaInveste();
							System.out.println(
									"Deseja realizar algum investimento?\nDigite 1 para sim;\n Digite 2 para não;");
							opc = scanner.next();
							if (opc.equalsIgnoreCase("1")) {
								System.out.println("Informe qual o investimento:\nDigite 1 para iniciante;"
										+ "\nDigite 2 para conservador;\nDigite 3 para Agressivo;");
								opc = scanner.next();
								if (opc.equalsIgnoreCase("1")) {
									int idInves = 0;
									do {
										try {
											System.out.println("informe um ID de uma conta do tipo CONTA INVESTIMENTO!");
											idInves = scanner.nextInt();
											break;
										} catch (Exception e) {
											
										}
									
										idInves -= 1;
										if (contaList.get(idInves).getTipoConta()
												.equalsIgnoreCase("Conta Investimento")) {
											System.out.println("Informe o valor: ");
											double valorInves = scanner.nextDouble();
											((ContaInvestimento) contaList.get(idInves)).iniciante(valorInves);
											break;
										} else {
											System.out.println("Favor informe uma conta do Tipo CONTA INVESTIMENTO!");
										}
									} while (true);
								} else if (opc.equalsIgnoreCase("2")) {
									 int idInves = 0;
									do {
										System.out.println("informe um ID de uma conta do tipo CONTA INVESTIMENTO!");
										idInves = scanner.nextInt();
										idInves -= 1;
										if (contaList.get(idInves).getTipoConta()
												.equalsIgnoreCase("Conta Investimento")) {
											System.out.println("Informe o valor: ");
											double valorInves = scanner.nextDouble();
											((ContaInvestimento) contaList.get(idInves)).conservador(valorInves);
											break;
										} else {
											System.out.println("Favor informe uma conta do Tipo CONTA INVESTIMENTO!");
										}
									} while (true);
								} else if (opc.equalsIgnoreCase("3")) {
									int idInves = 0;
									do {
										System.out.println("informe um ID de uma conta do tipo CONTA INVESTIMENTO!");
										idInves = scanner.nextInt();
										idInves -= 1;
										if (contaList.get(idInves).getTipoConta()
												.equalsIgnoreCase("Conta Investimento")) {
											System.out.println("Informe o valor: ");
											double valorInves = scanner.nextDouble();
											((ContaInvestimento) contaList.get(idInves)).agressivo(valorInves);
											break;
										} else {
											System.out.println("Favor informe uma conta do Tipo CONTA INVESTIMENTO!");
										}
									} while (true);
								}
							}
							break;
						case "4":
							// digete 4 para verificar seu rendimento na poupança
							valor = 0;
							do {
								try {
									System.out.println("De quanto é o valor que deseja simular?");
									valor = scanner.nextDouble();
									break;
								} catch (Exception e) {

								}

							} while (true);

							double juros = 0;

							do {
								try {
									System.out.println("De quanto é o juros anual?");
									juros = scanner.nextFloat();
									break;
								} catch (Exception e) {

								}

							} while (true);
							int periodo = 0;

							do {
								try {
									System.out.println("Digite a quantidade de meses que deseja simular?");
									periodo = scanner.nextInt();
									break;
								} catch (Exception e) {

								}

							} while (true);
							System.out.println(contaPoupanca.rendimento(valor, juros, periodo));

							break;
						case "5":
							// ndigite 5 para ver seu saldo;
							listContas();
							int idSaldo = 0;
							do {
								try {
							
									System.out.println("Favor informe o ID da conta: ");
									idSaldo = scanner.nextInt();
									break;
								} catch(Exception e) {
									System.out.println("Favor informe um valor válido.");
								}
							} while (true);
							
							idSaldo -= 1;
							System.out.println("--------------------------------------------------------------------");
							System.out.println("-------------------------  Saldo  ----------------------------------");
							System.out.println("--------------------------------------------------------------------");
							System.out.println(
									contaList.get(idSaldo).getConta() + "   |   " + contaList.get(idSaldo).saldo());
							System.out.println("--------------------------------------------------------------------");
							break;
						case "6":
							listContas();
							int idSaque = 0;
							do {
								try {
									System.out.println("Digite o id de qual conta deseja realizar saque: ");
									idSaque = scanner.nextInt();
									break;
								} catch (Exception e) {
									System.out.println("Informe um valor válido");
								}
							} while (true);

							idSaque -= 1;
							double valorSaque = 0;
							do {
								try {
									System.out.println("Informe o valor: ");
									valorSaque = scanner.nextDouble();
									break;
								} catch (Exception e) {
									System.out.println("Informe um valor válido.");
								}
							} while (true);

							contaList.get(idSaque).saque(valorSaque);
							System.out.println("Saque realizado com sucesso, no valor de: " + valorSaque
									+ " Agora seu saldo é " + contaList.get(idSaque).saldo());

							break;
						}
					} while (!(opc.equalsIgnoreCase("7")));
					break;
				}
				System.out.println("--------------------------------------------------------------------");
				break;
			case "3":
				String op = null;
				do {
					try {
						System.out.println("--------------------------------------------------------------------");
						System.out.println("------------ Relatório das Operações de um cliente -----------------");
						System.out.println("--------------------------------------------------------------------");
						System.out.println(
								"Digite 1 para listar todas as CONTAS com saldo NEGATIVO;\nDigite 2 para ver o total investido;"
										+ "\nDigite 3 para ver TODAS as operacoes de um cliente;");
						op = scanner.next();
						break;
					} catch (Exception e) {
						System.out.println("Favor informe um valor válido");
					}
				} while (true);

				if (op.equalsIgnoreCase("1")) {
					contasNegativas();
					break;
				} else if (op.equalsIgnoreCase("2")) {
					totalInvestido();
					break;
				} else if (op.equalsIgnoreCase("3")) {
					listContas();
					int idOp = 0;
					do {
						try {
							System.out.println("informe um ID de uma conta.");
							idOp = scanner.nextInt();

							break;

						} catch (Exception e) {
							System.out.println("Informe um  valor válido.");
						}
					} while (true);
					idOp -= 1;
					contaList.get(idOp).imprimiTransferencia();
				}
				break;

			case "4":
				listContas();
				int idAltera = 0;
				do {
					try {
						System.out.println("Informe o ID de qual conta deseja alterar: ");
						idAltera = scanner.nextInt();
						break;
					} catch (Exception e) {
						System.out.println("Informe um valor válido.");
					}
				} while (true);
				idAltera -= 1;
				String nome;
				Agencia agencia = null;
				;
				double rendaMensal;

				do {
					try {

						System.out.println("Digite seu nome: ");
						String esy = scanner.nextLine();
						nome = scanner.nextLine();

						System.out.println("Digite sua renda mensal: ");
						rendaMensal = scanner.nextDouble();
						System.out.println("--------------------------------------------------------------");
						System.out.println("Agora, escolha de qual AGENCIA você vai quere abrir sua conta: ");
						opc = null;
						do {
							try {
								System.out.println("Digite: 1 - para Florianópolis; 2 - para São José");
								opc = scanner.next();
								break;
							} catch (Exception e) {
								System.out.println("Favor informe um valor válido!");
							}
						} while (!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));

						if (opc.equalsIgnoreCase("1")) {
							agencia = Agencia.FLORIANOPOLIS;
						} else if (opc.equalsIgnoreCase("2")) {
							agencia = Agencia.SAO_JOSE;
						}
						break;
					} catch (Exception e) {
						System.out.println("Favor digite os dados válidos.");
					}
				} while (true);

				System.out.println("--------------------------------------------------------------");
				System.out.println("Agora vamos gerar o número da sua CONTA.");
				conta.geraConta();
				System.out.println("O número da sua conta é: " + conta.getConta());
				System.out.println("--------------------------------------------------------------");
				contaList.get(idAltera).alteraCad(nome, rendaMensal, agencia);
				break;
			}
		} while (!(opcao.equalsIgnoreCase("5")));
		System.out.println("O Banco DevInMoney agradece sua preferencia.");
	}

	public static void criaConta(Conta conta) {

		System.out.println("--------------------------------------------------------------");
		System.out.println("               Vamos iniciar  o seu cadastro                  ");
		System.out.println("--------------------------------------------------------------");
		System.out.println();
		System.out.println("Digite seu nome: ");
		String nome = scanner.nextLine();
		conta.setNome(scanner.nextLine());
		System.out.println("Digite seu CPF: ");
		String cpf = scanner.nextLine();

		if (validaCpf.isCPF(cpf)) {
			conta.setCpf(cpf);
		} else {
			System.out.println("CPF invalido");
			criaConta(conta);
		}

		System.out.println("Digite sua renda mensal: ");
		conta.setRendaMensal(scanner.nextDouble());
		System.out.println("--------------------------------------------------------------");
		System.out.println("Agora, escolha de qual AGENCIA você vai quere abrir sua conta: ");
		String opc = null;
		do {
			System.out.println("Digite: 1 - para Florianópolis; 2 - para São José");
			opc = scanner.next();
		} while (!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));

		if (opc.equalsIgnoreCase("1")) {
			conta.setAgencia(Agencia.FLORIANOPOLIS);
		} else {
			conta.setAgencia(Agencia.SAO_JOSE);
		}
		System.out.println("--------------------------------------------------------------");
		System.out.println("Agora vamos gerar o número da sua CONTA CORRENTE.");
		conta.geraConta();
		System.out.println("O número da sua conta é: " + conta.getConta());
		System.out.println("--------------------------------------------------------------");
		opc = null;
		do {
			System.out.println("Deseja realizar algum deposito na sua conta? Se sim digete 1; não digite 2; ");
			opc = scanner.next();

		} while (!(opc.equalsIgnoreCase("1") || opc.equalsIgnoreCase("2")));
		double valor = 0;
		if (opc.equalsIgnoreCase("1")) {
			do {
				try {
					System.out.println("Quanto deseja depositar?");
					valor = scanner.nextDouble();
					conta.depositar(valor);
					break;
				} catch (Exception e) {

				}
				conta.setSaldo(valor);
				;
			} while (true);

		} else {
			conta.setSaldo(0);
		}
		System.out.println("Conta finalizada com sucesso!");

		contaList.add(conta);

	}

	public static void listContas() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------------------------");
		System.out.println("------------ Relatório de contas no sistema -------------------");
		System.out.println("---------------------------------------------------------------");
		System.out.println("ID       CONTA      NOME          TIPO DE CONTA           VALOR");
		for (int i = 0; i < contaList.size(); i++) {

			if (contaList.get(i).getTipoConta().equalsIgnoreCase("Conta Corrente")) {
				System.out.println(1 + i + "   |   " + contaList.get(i).getConta() + "   |   "
						+ contaList.get(i).getNome() + "   |   " + contaList.get(i).getTipoConta() + "   |   "
						+ ((ContaCorrente) contaList.get(i)).saldo());
			}
			System.out.println(1 + i + "   |   " + contaList.get(i).getConta() + "   |   " + contaList.get(i).getNome()
					+ "   |   " + contaList.get(i).getTipoConta() + "   |   " + contaList.get(i).saldo());
		}

	}

	public static void contasNegativas() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------------------------");
		System.out.println("------------ Relatório das contas negativas no sistema ---------");
		System.out.println("---------------------------------------------------------------");

		for (int i = 0; i < contaList.size(); i++) {

			if (contaList.get(i).saldo() < 0) {
				System.out.println(contaList.get(i).getConta() + "   |   " + contaList.get(i).getNome() + "   |   "
						+ contaList.get(i).getTipoConta() + "   |   " + contaList.get(i).saldo());
			}
		}

	}

	public static void totalInvestido() {
		// TODO Auto-generated method stub
		double totalInvestido = 0;
		System.out.println("---------------------------------------------------------------");
		System.out.println("------------ Relatório do total investido ---------------------");
		System.out.println("---------------------------------------------------------------");
		for (int i = 0; i < contaList.size(); i++) {

			if (contaList.get(i).getTipoConta().equalsIgnoreCase("Conta Investimento")) {
				totalInvestido += ((ContaInvestimento) contaList.get(i)).getTotalInvestido();
			}
		}
		System.out.println("O total investido é de R$:"+totalInvestido+" reais.");

	}

	public static void operacoesCliente() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------------------------");
		System.out.println("------------ Relatório do total investido ---------------------");
		System.out.println("---------------------------------------------------------------");
		for (int i = 0; i < contaList.size(); i++) {
			contaList.get(i).imprimiTransferencia();
		}
	}

}
