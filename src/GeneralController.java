import java.util.ArrayList;

public class GeneralController {
	
	private int caixa;
	private double taxa;
	private ArrayList <Cenario> listCenarios; 
	
	/** 1: Inicializar Sistema **/
	public GeneralController(int caixa, double taxa) {
		this.caixa = caixa;
		this.taxa = taxa;
		this.listCenarios = new ArrayList<>();
	}
	
	public int getCaixa() {
		return caixa;
	}
	
	
	/** 2: Cadastrar e Listar Cenários **/
	public int cadastrarCenario(String descricao) {
		Cenario novo = new Cenario(descricao);
		this.listCenarios.add(novo);
		return this.listCenarios.size();
	}
	
	public String exibirCenario(int id) {		// TODO: Throw exceptions
		return this.listCenarios.get(id-1).toString();
	}
	
	public String exibirCenarios() {
		String ret = "";
		for (Cenario i : listCenarios) {
			ret += i.toString();
		}
		return ret;
	}
	
	
	/** 3: Cadastrar e Listar Apostas **/
	public void cadastrarAposta(int id, String apostador, int valor, String previsao) { // TODO: Build method
		listCenarios.get(id-1).cadastrarAposta(apostador, valor, previsao);
	}
	
	public int valorTotalDeApostas(int id) {
		return listCenarios.get(id-1).valorTotal();
	}
	
	public int totalDeApostas(int id) {
		return listCenarios.get(id-1).getTotalApostas();
	}
	
	public String exibeApostas(int id) {
		return listCenarios.get(id-1).exibeApostas();
	}
	
	
	/** 4: Fechar Apostas (Concretizar Cenário) **/
	public void	fecharAposta(int id, boolean ocorreu) {
		listCenarios.get(id-1).finalizaApostas(ocorreu);
	}
	
	public int getCaixaCenario(int id) {
		return listCenarios.get(id-1).getDestinadoCaixa();
	}
	
	public int getTotalRateioCenario(int id) {
		return listCenarios.get(id-1).getDestinadoVencedores();
	}
}
