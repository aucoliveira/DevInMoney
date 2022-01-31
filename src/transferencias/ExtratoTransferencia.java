package transferencias;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import conta.Conta;

public class ExtratoTransferencia {
	
	private Conta contaOrigem;
	private Conta contaDestino;
	private double valor;
	private LocalDate data;
	
	public ExtratoTransferencia() {
		
	}
	
	public ExtratoTransferencia(Conta contaOrigem, Conta contaDestino, double valor) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
	}
	
	public void addExtrato(Conta contaOrigem, Conta contaDestino, double valor) {
		this.contaOrigem = contaOrigem;
		this.contaDestino =  contaDestino;
		this.valor = valor;
		this.data = LocalDate.now();
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}
