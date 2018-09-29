package br.felipe.AulaTeste02;

import java.util.Calendar;
import java.util.List;


public class Banco {
	private ContaDao dao;
	private List<Conta> contas;
	private Notificador notificador;

	public Banco(ContaDao dao) {
		this.dao = dao;
		this.contas = dao.getContas();
	}

	public Banco(ContaDao dao, Notificador notificador) {
		this.dao = dao;
		this.notificador = notificador;
		this.contas = this.dao.getContas();
	}

	public void atualizaJuros(double indice) {
		for (Conta c : this.contas) {

			try {
				double dividendo = c.getSaldo() + (indice * c.getSaldo() / 100);
				c.deposito(dividendo);
				this.dao.atualizaConta(c);
			} catch (Exception e) {
				System.out.println("lançou uma excecao");
			}

		}
	}

	public double totalSaldo() {
		double saldoTotal = 0.0;
		for (Conta c : this.contas) {
			saldoTotal += c.getSaldo();
		}
		return saldoTotal;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void notificaDiretoria() {
		Notificacao notificacao = new Notificacao(Calendar.getInstance());
		for (Conta c : this.contas) {
			notificacao.addContas(c);
		}
		notificador.gera(notificacao);
	}
}