package br.felipe.AulaTeste02;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;


import static org.mockito.Mockito.*;

public class BancoTest {

	private Conta c1;
	private Conta c2;
	private Conta c3;
	private Usuario joao;
	private Usuario manoel;
	private Usuario marina;

	@Before
	public void criarAmbiente() {
		c1 = new Conta(100.0, "Basica");
		c2 = new Conta(200.0, "Prime");
		c3 = new Conta(400.0, "Ultra");
		joao = new Usuario("Joao da Silva", "111111111", "joaosilva@mailinator.com");
		manoel = new Usuario("Manoel da Silva", "22222222", "manoelsilva@mailinator.com");
		marina = new Usuario("Marina", "111111111", "marinasouza@marina.com");
	}

	@Test
	public void deveSomarTodasAsContasTrazendoOSaldoDoBanco() {
		List<Conta> contas = new ContaBuilder().addConta(c1, joao).addConta(c2, manoel).addConta(c3, marina).constroi();

		ContaDao dao = mock(ContaDao.class);
		when(dao.getContas()).thenReturn(contas);

		dao.salvaConta(contas.get(0));
		dao.salvaConta(contas.get(1));
		dao.salvaConta(contas.get(2));

		Banco banco = new Banco(dao);

		assertEquals(3, banco.getContas().size(), 0.00001);
		assertEquals(700, banco.totalSaldo(), 0.00001);
	}

	@Test
	public void deveAtualizarAsContasComJurosAplicados() {
		List<Conta> contas = new ContaBuilder().addConta(c1, joao).addConta(c2, manoel).addConta(c3, marina).constroi();

		ContaDao dao = mock(ContaDao.class);
		when(dao.getContas()).thenReturn(contas);

		Banco banco = new Banco(dao);
		banco.atualizaJuros(5);

		// verificando se o metodo atualizaConta foi realmente invocado!
		verify(dao, times(1)).atualizaConta(contas.get(0));
		//assertEquals(735.0, banco.totalSaldo(), 0.00001);
	}

	@Test
	public void deveContinuarAExecucaoMesmoQuandoDaoFalha() {
		List<Conta> contas = new ContaBuilder().addConta(c1, joao).addConta(c2, manoel).addConta(c3, marina).constroi();

		ContaDao dao = mock(ContaDao.class);
		when(dao.getContas()).thenReturn(contas);

		doThrow(new RuntimeException()).when(dao).atualizaConta(c1);

		Banco banco = new Banco(dao);
		banco.atualizaJuros(5);

		verify(dao, times(1)).atualizaConta(c1);
		verify(dao, times(1)).atualizaConta(c2);
		verify(dao, times(1)).atualizaConta(c3);

	}

	@Test
	public void deveChecarAsNotificacoesGeradasPeloBancoParaADiretoria() {
		List<Conta> contas = new ContaBuilder()
				.addConta(c1, joao)
				.addConta(c2, manoel)
				.addConta(c3, marina)
				.constroi();
		
		ContaDao dao = mock(ContaDao.class);
		when(dao.getContas()).thenReturn(contas);
		
		Notificador notificadorFalso = mock(Notificador.class);
		
		Banco banco = new Banco(dao, notificadorFalso);
		banco.atualizaJuros(5);
		banco.notificaDiretoria();
		
		ArgumentCaptor<Notificacao> argumento = ArgumentCaptor.forClass(Notificacao.class);
		verify(notificadorFalso).gera(argumento.capture());
		Notificacao notificacao = argumento.getValue();
		assertEquals(3, notificacao.getContasNotificadas().size(), 0.00001);
		
	}
}