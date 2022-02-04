package conta;

import java.time.LocalDate;
import java.util.ArrayList;

import investimento.Investimento;
import transferencias.ExtratoTransferencia;
import validador.SaldoInsuficienteException;

public class ContaInvestimento extends Conta implements Investimento {

	private final String tipoConta = "Conta Investimento";
	private double totalInvestido;
	private ArrayList<String> operacoes = new ArrayList<String>();
	private LocalDate data = LocalDate.now();

	public double getTotalInvestido() {
		return totalInvestido;
	}

	public ArrayList<String> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(ArrayList<String> operacoes) {
		this.operacoes = operacoes;
	}

	public void setTotalInvestido(double totalInvestido) {
		this.totalInvestido = totalInvestido;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public ContaInvestimento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContaInvestimento(String nome, String cpf, float rendaMensal, Agencia agencia) {
		super(nome, cpf, rendaMensal, agencia);
		// TODO Auto-generated constructor stub
	}

	public void consultaInveste() {

		System.out.println("--------------------------------------------------------------");
		System.out.println("*************** Tipos de Investimento ************************");
		System.out.println("--------------------------------------------------------------");
		System.out.println("-- Nome ---------- Rentabilidade ------------ Periodo --------");
		System.out.println("-- Iniciante --------- 5,7 % --------------- 12 meses --------");
		System.out.println("-- Conservador ------- 9,3 % --------------- 12 meses --------");
		System.out.println("-- Agrassivo --------- 27,9 % -------------- 12 meses --------");
		System.out.println("--------------------------------------------------------------");
		System.out.println();

	}

	public double simulaInvestimento(double valor) {
		System.out.println("------------------ Iniciante Simulação ------------------------");
		iniciante(valor);
		conservador(valor);
		agressivo(valor);
		return valor;
	}

	@Override
	public double iniciante(double valor) {
		// TODO Auto-generated method stub
		double rendimento, juros;
		juros = 5.7f;
		rendimento = (valor * juros) / 100;
		System.out.println("Você investindo " + valor + " na opcção iniciante, vai te render " + rendimento);
		this.totalInvestido += valor;
		operacoes.add(this.conta + "   |   " + "Inves. Iniciante" + "   |   " + valor + "   |   " + data);
		return rendimento;
	}

	@Override
	public double conservador(double valor) {
		// TODO Auto-generated method stub
		double rendimento, juros;
		juros = 9.3f;
		rendimento = (valor * juros) / 100;
		System.out.println("Você investindo " + valor + " na opcção conservador, vai te render " + rendimento);
		this.totalInvestido += valor;
		operacoes.add(this.conta + "   |   " + "Inves. Conservador" + "   |   " + valor + "   |   " + data);
		return rendimento;

	}

	@Override
	public double agressivo(double valor) {
		// TODO Auto-generated method stub
		double rendimento, juros;
		juros = 27.9f;
		rendimento = (valor * juros) / 100;
		System.out.println("Você investindo " + valor + " na opcção agrassivo, vai te render " + rendimento);
		this.totalInvestido += valor;
		operacoes.add(this.conta + "   |   " + "Inves. Agrassivo" + "   |   " + valor + "   |   " + data);
		return rendimento;
	}

	@Override
	public void transferir(double valor, Conta contaRecebe, Conta contaOrigem) throws SaldoInsuficienteException {
		LocalDate dtAgora = LocalDate.now();
		String diaSemana = dtAgora.getDayOfWeek().toString();

		saldo = saldo();
		if (!contaOrigem.getConta().equalsIgnoreCase(contaRecebe.getConta())) {
			if (!diaSemana.equalsIgnoreCase("SATURDAY") && !diaSemana.equalsIgnoreCase("SUNDAY")) {

				if (valor <= 0 || valor > saldo) {
					System.out.println("O valor não pode ser menor que zero.");
				} else if (valor > saldo) {
					throw new SaldoInsuficienteException("Você não tem SALDO suficiente!.");
					// System.out.println()
				}
				try {
					contaRecebe.depositar(valor);
				} catch (IllegalArgumentException e) {
					System.out.println(e);
				}
				transferencia.addExtrato(contaOrigem, contaRecebe, valor);
				operacoes.add(
						transferencia.getContaOrigem().getConta() + "  |  " + transferencia.getContaDestino().getConta()
								+ "  |  " + transferencia.getValor() + "  |  " + transferencia.getData());
				this.saldo -= valor;
			} else {
				System.out.println("Não pode realizar transferencias no final de semana.");
			}
		} else {
			System.out.println("Não pode fazer transferencia para você mesmo.");
		}

	}

	@Override
	public void depositar(double deposito) {
		if (deposito <= 0) {
			throw new IllegalArgumentException("O valor não pode ser menor que zero.");
		}
		operacoes.add(agencia + "  |  " + conta + "  |  " + deposito + "  |  " + data);
		this.saldo += deposito;
	}

	@Override
	public void imprimiTransferencia() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------------------- Extrato ------------------------------------");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Conta Origem ------ Conta Destino  ------ Valor ----- Data-------");
		for (int i = 0; i < operacoes.size(); i++) {
			System.out.println(operacoes.get(i));
		}
	}

	@Override
	public double saldo() {
		this.saldo = saldo;
		return saldo;
	}

	@Override
	public void saque(double valor) throws SaldoInsuficienteException {
		double saldoTemp = saldo - valor;
		if (saldoTemp < 0) {
			throw new SaldoInsuficienteException("Você não possui saldo suficiente");
		}
		this.saldo -= valor;
		operacoes.add(conta + "  |  " + "  SAQUE  " + "   |   " + valor + "  |  " + data);
	}
}
