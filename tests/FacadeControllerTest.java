import static org.junit.Assert.*;

import org.junit.*;

public class FacadeControllerTest {

	FacadeController padrao = new FacadeController();
	
	@Before
	public void testInicializaPadrao() {
		padrao.inicializa(10000, 0.01);
	}
	@Before
	public void testCadastrarCenarioPadrao() {
		padrao.cadastrarCenario("Joaozinho é um cara legal");
	}
	/**
	 * Inicializa um sistema com caixa de valor negativo
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testInicializaCaixaNegativo() {
		padrao.inicializa(-1, 0.01);
	}
	/**
	 * Inicializa um sistema com taxa de valor negativo
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testInicializaTaxaNegativa() {
		padrao.inicializa(10000, -0.01);
	}
	/**
	 * Cria um cenário com descrição vazia
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarCenarioDescricaoVazia() {
		padrao.cadastrarCenario("");
	}
	
	/**
	 * Cria um cenário com descrição nula
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastrarCenarioDescricaoNula() {
		padrao.cadastrarCenario(null);
	}
	
	/**
	 * Verifica a exibição de um cenário padrão
	 */
	@Test
	public void testExibirCenarioPadrao() {
		String msg = "Esperando a saída adequada";
		String expected = "1 - Joaozinho é um cara legal - Nao finalizado";
		assertEquals(msg, expected, padrao.exibirCenario(1));
	}
	/**
	 * Testa a exibição de um cenário com id diferente do inicial
	 */
	@Test
	public void testExibirCenarioSecundario() {
		padrao.cadastrarCenario("Eu não vou passar em cálculo");
		padrao.cadastrarCenario("É sério.");
		String msg = "Esperando a saída adequada";
		String expected = "3 - É sério. - Nao finalizado"; 
		assertEquals(msg, expected, padrao.exibirCenario(3));
	}
	
	/**
	 * Tenta exibir um cenário com id negativo
	 */
	@Test(expected=NullPointerException.class)
	public void testExibirCenarioIdNegativo() {
		padrao.exibirCenario(-1);
	}
	/**
	 * Tenta exibir um cenário com id ainda não cadastrado
	 */
	@Test(expected=NullPointerException.class)
	public void testExibirCenarioIdInvalido() {
		padrao.exibirCenario(9);
	}
}
	