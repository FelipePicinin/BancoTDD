package br.felipe.AulaTeste02;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Notificacao {
	private List<Conta> contasNotificadas = new ArrayList<Conta>();
	private Calendar data;

	public Notificacao(Calendar data) {
		this.data = data;
	}

	public void addContas(Conta conta) {
		this.contasNotificadas.add(conta);
	}

	public List<Conta> getContasNotificadas() {
		return this.contasNotificadas;
	}
}