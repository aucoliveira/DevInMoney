package conta;

import java.time.LocalDate;
import java.util.ArrayList;

import transferencias.ExtratoTransferencia;
import validador.SaldoInsuficienteException;

public class ContaCorrente extends Conta{
	
	private double  chequeEspecial = rendaMensal;;
	private final String tipoConta = "Conta Corrente";
	private LocalDate data = LocalDate.now();
	private ArrayList<String> operacoes = new ArrayList<String>();
	
	
	
	public ArrayList<String> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(ArrayList<String> operacoes) {
		this.operacoes = operacoes;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public double getChequeEspecial() {
		return chequeEspecial;
	}
	

	public void setChequeEspecial(double rendaMensal) {
		this.chequeEspecial = rendaMensal;;
	}
	
	@Override
	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
		this.chequeEspecial = rendaMensal;
	}
	public ContaCorrente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContaCorrente(String nome, String cpf, float rendaMensal, Agencia agencia, double saldo) {
		super();
		// TODO Auto-generated constructor stub
		this.chequeEspecial += rendaMensal;
	}
	@Override
	public void  saque(double valor) throws SaldoInsuficienteException {
		double valorTemp = saldo + chequeEspecial;
		valorTemp = valorTemp - valor;
		double saldoTemp;
		
		if (valorTemp <= 0) {
			throw new SaldoInsuficienteException("Você não possui saldo suficiente");
		}
		if (valor > saldo) {
			saldoTemp = valor - saldo;
			saldo += chequeEspecial;
			//this.chequeEspecial = (chequeEspecial)*(-1);
			this.saldo -= valor;
			this.chequeEspecial -= chequeEspecial;
			
		}else {
			this.saldo -= valor;
		}
		
		
	}
	
	@Override
	public void depositar(double deposito) {
		if (deposito <= 0) {
			throw new IllegalArgumentException("O valor não pode ser menor que zero.");
		}
		operacoes.add(agencia+"  |  "+conta+
				"  |  "+deposito+"  |  "+data);
		this.saldo += deposito;
	}
	
	@Override
	public  double saldo() {
		this.saldo += chequeEspecial;
		return saldo;
	}
	@Override
	public void transferir(double valor, Conta contaRecebe, Conta contaOrigem) throws SaldoInsuficienteException {
		
		LocalDate dtAgora = LocalDate.now();
		String diaSemana = dtAgora.getDayOfWeek().toString();
		
		saldo = saldo();
		if(!contaOrigem.getConta().equalsIgnoreCase(contaRecebe.getConta())) {
			if(!diaSemana.equalsIgnoreCase("SATURDAY") && !diaSemana.equalsIgnoreCase("SUNDAY")) {
			
				if(valor <= 0 || valor > saldo) {
					System.out.println("O valor não pode ser menor que zero.");
				}
				else if (valor > saldo) {
					throw new SaldoInsuficienteException("Você não tem SALDO suficiente!.");
					//System.out.println()
				}
				try {
					contaRecebe.depositar(valor);
					}catch(IllegalArgumentException e) {
						System.out.println(e);
					}
				transferencia.addExtrato(contaOrigem, contaRecebe, valor);
				transferencias.add(transferencia);
				operacoes.add(transferencia.getContaOrigem().getConta()+"  |  "+transferencia.getContaDestino().getConta()+
							"  |  "+transferencia.getValor()+"  |  "+transferencia.getData());
				this.saldo -= valor;
			} else {
				System.out.println("Não pode realizar transferencias no final de semana.");
			}
		} else {
			System.out.println("Não pode fazer transferencia para você mesmo.");
		}
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
	
}
