import java.util.ArrayList;

public class GeneralController {
	
	private int caixa;
	private double taxa;
	private ArrayList <Cenario> listCenarios; 
	
	/** 1: Inicializar Sistema **/
	/**
	 * Inicializa as informações do GeneralController
	 * @param caixa caixa do sistema
	 * @param taxa taxa do sistema
	 */
	public void inicializa(int caixa, double taxa) {
		if (caixa < 0) throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		if (taxa < 0) throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		this.caixa = caixa;
		this.taxa = taxa;
	}
	/**
	 * Constroi um novo GeneralController
	 */
	public GeneralController() {
		this.listCenarios = new ArrayList<>();
	}
	/**
	 * Fornece o valor atual no caixa
	 * @return valor do caixa
	 */
	public int getCaixa() {
		int total = this.caixa;
		for (int i = 0; i < listCenarios.size(); i++) {
			if (listCenarios.get(i).isFinalizado()) {
				total += this.getCaixaCenario(i+1);
			}
		}
		return total;
	}
	
	
	/** 2: Cadastrar e Listar Cenários **/
	/**
	 * Cadastra um novo cenário
	 * @param descricao descrição do cenário
	 * @return
	 */
	public int cadastrarCenario(String descricao) {
		descricaoValida(descricao);
		Cenario novo = new Cenario(descricao);
		this.listCenarios.add(novo);
		return this.listCenarios.size();
	}
	
	/**
	 * Exibe um cenário
	 * @param id id do cenário
	 * @return retorna uma string que representa o cenário
	 */
	public String exibirCenario(int id) {		
		idIsValidConsultaCenario(id); 
		return id + " - " + this.listCenarios.get(id-1).toString();
	}
	
	/**
	 * Exibe todos os cenários do sistema
	 * @return String com a representação de todos os cenários
	 */
	public String exibirCenarios() {
		String ret = "";
		for (int i = 0; i < listCenarios.size(); i++) {
			int id = i + 1;
			ret +=  id + " - " + listCenarios.get(i).toString() + System.lineSeparator();
		}
		return ret;
	}
	
	
	/** 3: Cadastrar e Listar Apostas **/
	/**
	 * Cadastra uma nova aposta
	 * @param id id da aposta
	 * @param apostador nome do apostador
	 * @param valor valor da aposta
	 * @param previsao previsao da aposta
	 */
	public void cadastrarAposta(int id, String apostador, int valor, String previsao) { // TODO: Build method
		idIsValidCadastroAposta(id);
		apostadorValido(apostador);
		validaPrevisao(previsao);
		if (valor <= 0) throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		if (listCenarios.get(id-1).isFinalizado()) throw new IllegalArgumentException();
		listCenarios.get(id-1).cadastrarAposta(apostador, valor, previsao);
	}
	/**
	 * Fornece o valor total das apostas
	 * @param id id do cenário 
	 * @return valor total das apostas
	 */
	public int valorTotalDeApostas(int id) {
		idIsValidValorTotalAposta(id); 
		return listCenarios.get(id-1).valorTotal();
	}
	
	/**
	 * Fornece o total de apostas
	 * @param id id do cenário
	 * @return total de apostas
	 */
	public int totalDeApostas(int id) {
		idIsValidTotalAposta(id); 
		return listCenarios.get(id-1).getTotalApostas();
	}
	
	/**
	 * Exibe as apostas de um cenário
	 * @param id id do cenário 
	 * @return String que representa as apostas de um cenário
	 */
	public String exibeApostas(int id) {
		return listCenarios.get(id-1).exibeApostas();
	}
	
	
	/** 4: Fechar Apostas (Concretizar Cenário) **/
	/**
	 * Concretiza uma aposta específica
	 * @param id id do cenário
	 * @param ocorreu determina se a aposta ocorreu
	 */
	public void	fecharAposta(int id, boolean ocorreu) {
		idIsValidFecharAposta(id); 
		if (listCenarios.get(id-1).isFinalizado()) {
			throw new IllegalArgumentException("Erro ao fechar aposta: Cenario ja esta fechado");
		}
		listCenarios.get(id-1).finalizaApostas(ocorreu);
		listCenarios.get(id-1).setFinalizado(true);
	}
	
	/**
	 * Determina o total destinado ao caixa do cenário
	 * @param id id do cenário
	 * @return total destinado ao caixa
	 */
	public int getCaixaCenario(int id) {
		idIsValidConsultaCaixaCenario(id);
		if(!listCenarios.get(id-1).isFinalizado()) {
			throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}
		return (int) (listCenarios.get(id-1).getDestinadoCaixa() * this.taxa);
	}
	/**
	 * Determina o total de rateio do cenário
	 * @param id id do cenário
	 * @return rateio do cenário
	 */
	public int getTotalRateioCenario(int id) {
		idIsValidRateio(id); 
		if(!listCenarios.get(id-1).isFinalizado()) {
			throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		}
		return listCenarios.get(id-1).getDestinadoCaixa() - this.getCaixaCenario(id);
	}
	
	/**
	 * Verifica se o ID passado é válido, lançando exceção
	 * @param id ID do elemento passado
	 */
	private void idIsValidConsultaCenario(int id) {
		if (listCenarios.size() < id) throw new NullPointerException("Erro na consulta de cenario: Cenario nao cadastrado");
		if (id < 1) throw new NullPointerException("Erro na consulta de cenario: Cenario invalido"); 
	}
	private void idIsValidCadastroAposta(int id) {
		if (listCenarios.size() < id) throw new NullPointerException("Erro no cadastro de aposta: Cenario nao cadastrado");
		if (id < 1) throw new NullPointerException("Erro no cadastro de aposta: Cenario invalido"); 
	}
	private void idIsValidValorTotalAposta(int id) {
		if (listCenarios.size() < id) throw new NullPointerException("Erro na consulta do valor total de apostas: Cenario nao cadastrado");
		if (id < 1) throw new NullPointerException("Erro na consulta do valor total de apostas: Cenario invalido"); 
	}
	private void idIsValidTotalAposta(int id) {
		if (listCenarios.size() < id) throw new NullPointerException("Erro na consulta do total de apostas: Cenario nao cadastrado");
		if (id < 1) throw new NullPointerException("Erro na consulta do total de apostas: Cenario invalido"); 
	}
	private void idIsValidFecharAposta(int id) {
		if (listCenarios.size() < id) throw new NullPointerException("Erro ao fechar aposta: Cenario nao cadastrado");
		if (id < 1) throw new NullPointerException("Erro ao fechar aposta: Cenario invalido"); 
	}
	private void idIsValidConsultaCaixaCenario(int id) {
		if (listCenarios.size() < id) throw new NullPointerException("Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		if (id < 1) throw new NullPointerException("Erro na consulta do caixa do cenario: Cenario invalido"); 
	}
	private void idIsValidRateio(int id) {
		if (listCenarios.size() < id) throw new NullPointerException("Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		if (id < 1) throw new NullPointerException("Erro na consulta do total de rateio do cenario: Cenario invalido"); 
	}
	
	/**
	 * Verifica se a string passada é válida, lançando exceção
	 * @param str string 
	 */
	private void descricaoValida(String str) {
		if (str == null) throw new NullPointerException();
		if (str.trim().equals("")) throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
	}
	private void apostadorValido(String str) {
		if (str == null) throw new NullPointerException();
		if (str.trim().equals("")) throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
	}
	private void validaPrevisao(String str) {
		if (str == null) throw new NullPointerException();
		if (str.trim().equals("")) throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		if (!str.equals("VAI ACONTECER") && !str.equals("N VAI ACONTECER")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		}
	}
}
