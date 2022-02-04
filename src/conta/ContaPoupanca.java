package conta;

import java.time.LocalDate;
import java.util.ArrayList;

import transferencias.ExtratoTransferencia;
import validador.SaldoInsuficienteException;

public class ContaPoupanca extends Conta {
	private float rendi;
	private final String tipoConta = "Conta Poupança";
	private ArrayList<String> operacoes = new ArrayList<String>();
	private LocalDate data = LocalDate.now();

	public ArrayList<String> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(ArrayList<String> operacoes) {
		this.operacoes = operacoes;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public ContaPoupanca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public float getRendi() {
		return rendi;
	}

	public void setRendi(float rendi) {
		this.rendi = rendi;
	}

	public ContaPoupanca(String nome, String cpf, float rendaMensal, Agencia agencia) {
		super(nome, cpf, rendaMensal, agencia);
		// TODO Auto-generated constructor stub
	}

	public double rendimento(double valor, double juros, int periodo) {
		juros = juros / 12;

		System.out.println("juros: " + juros + "% ao mês.");
		for (int i = 0; i < periodo; i++) {
			valor += (valor * juros) / 100;
		}
		return valor;
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
