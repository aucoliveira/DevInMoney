package conta;

import java.util.ArrayList;
import java.util.Random;

import transferencias.ExtratoTransferencia;
import validador.SaldoInsuficienteException;

public class Conta {
	protected String nome;
	protected  String cpf;
	protected double rendaMensal;
	protected String tipoConta;
	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	protected Agencia agencia;
	protected String conta;
	protected  double saldo;
	protected ArrayList<ExtratoTransferencia> transferencias = new ArrayList<ExtratoTransferencia>();
	protected ExtratoTransferencia transferencia = new ExtratoTransferencia();
	protected ArrayList<String> operacoes = new ArrayList<String>();
	
	public ArrayList<String> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(ArrayList<String> operacoes) {
		this.operacoes = operacoes;
	}

	public ArrayList<ExtratoTransferencia> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(ArrayList<ExtratoTransferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public ExtratoTransferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(ExtratoTransferencia transferencia) {
		this.transferencia = transferencia;
	}
	Random gera = new Random();
	
	
	public void geraConta() {
		
		int cont = gera.nextInt(99999);
		conta= Integer.toString(cont);
		if (agencia ==agencia.FLORIANOPOLIS) {
			conta += "-001";
		}else {
			conta +="-002";
		}
		this.conta = conta;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getConta() {
		return conta;
	}

	public Conta() {
		
	}
	
	public Conta(String nome, String cpf, double rendaMensal, Agencia agencia, float saldo) {
		this.nome = nome;
		this.cpf = cpf;
		this.rendaMensal = rendaMensal;
		this.agencia = agencia;
		this.saldo = saldo;
	}
	
	public void  saque(double valorSaque) throws SaldoInsuficienteException {
		
		
	}
	
	public void depositar(double valorDeposito) {
		
	}
	
	public double saldo() {
		return saldo;
	}
	
	public static String extrato(String extrato) {
		return extrato;
	}
	
	//public Conta(String nome, String cpf, float rendaMensal, Agencia agencia, float saldo)
	public  void alteraCad (String nome, double rendaMensal, Agencia agencia) {
		this.nome = nome;
		this.rendaMensal = rendaMensal;
		this.agencia =agencia;
		
		
	}

	public void transferir(double valor, Conta contaRecebe, Conta contaOrigem) throws SaldoInsuficienteException {
		// TODO Auto-generated method stub
		
	}
	public void imprimiTransferencia() {
		
	}
	


}