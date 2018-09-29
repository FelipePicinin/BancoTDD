package br.felipe.AulaTeste02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;



public class ContaDao {
	private Connection conexao;

	public ContaDao() {
		try {
			this.conexao = DriverManager.getConnection("jdbc:mysql://192.168.4.159/mocks", "root", "123456");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Conta> getContas() {
		try {
			String sql = "SELECT * FROM CONTA;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Conta> contas = new ArrayList<Conta>();
			while (rs.next()) {
				Conta conta = new Conta(rs.getLong("id"), rs.getDouble("saldo"));
				contas.add(conta);
			}
			rs.close();
			ps.close();
			return contas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizaConta(Conta conta) throws RuntimeErrorException {
		if (conta.getSaldo() > 0.0) {
			try {
				String sql = "UPDATE CONTA SET SALDO=? WHERE ID = ?;";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setLong(1, conta.getId());
				ps.execute();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new RuntimeException();
		}
	}

	public void salvaConta(Conta conta) {
		try {
			String sql = "INSERT INTO CONTA (SALDO) VALUES (?);";
			PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, conta.getSaldo());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}