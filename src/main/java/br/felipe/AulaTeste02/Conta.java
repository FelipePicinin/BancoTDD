package br.felipe.AulaTeste02;

public class Conta {
	private Long id;
	private double saldo;
	private double dividendo = 0.0;
	private String tipoConta;
	private Usuario usuario;

	public Conta() { }

	public Conta(double saldo, String tipoConta) {
		this.saldo = saldo;
		this.tipoConta = tipoConta;
	}

	public Conta(Long id, double saldo) {
		this.id = id;
		this.saldo = saldo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void deposito(double valor) {
		saldo = saldo + valor;
	}

	public void saque(double valor) {
		saldo = saldo - valor;
	}

	public double getSaldo() {
		return saldo;
	}

	public double getSaldoTotal() {
		return this.saldo + this.dividendo;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getDividendo() {
		return dividendo;
	}

	public void setDividendo(double dividendo) {
		this.dividendo += dividendo;
	}
}