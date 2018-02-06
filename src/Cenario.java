import java.util.ArrayList;
// TODO: Change logic about how the bets endings impact on post calculations
public class Cenario {	

	protected boolean ocorreu;
	private boolean finalizado;
	private String descricao;
	protected ArrayList<Aposta> listApostas;
	protected int destinadoCaixa;
	protected int destinadoVencedores;

	/**
	 * Constrói um novo cenário a partir de uma descrição
	 * @param descricao descrição do cenário
	 */
	public Cenario(String descricao) {
		this.descricao = descricao;
		this.listApostas = new ArrayList<>();
		this.finalizado = false;
	}
	
	/**
	 * Cadastra uma nova aposta no cenário
	 * @param apostador nome do apostador
	 * @param valor valor da aposta
	 * @param previsao previsão da aposta
	 */
	public void cadastrarAposta(String apostador, int valor, String previsao) {
		Aposta nova = new Aposta(apostador, valor, previsao);
		this.listApostas.add(nova);
	}

	/**
	 * Fornece o valor total das apostas
	 * @return valor total das apostas
	 */
	public int valorTotal() {
		int total = 0;
		for (Aposta i : listApostas) {
			total += i.getValor();
		}
		return total;
	}
	
	/**
	 * Exibe as apostas de um cenário
	 * @return string com as apostas do cenário
	 */
	public String exibeApostas() {
		String ret = "";
		for (Aposta i : listApostas) {
			ret += i.toString();
		}
		return ret;
	}

	/**
	 * Fornece o total de apostas
	 * @return total de apostas
	 */
	public int getTotalApostas() {
		return this.listApostas.size();
	}

	/**
	 * Finaliza as apostas
	 * @param ocorreu boolean que representa a ocorrência
	 */
	public void finalizaApostas(boolean ocorreu) { 		 
		this.ocorreu = ocorreu
		for (Aposta i : listApostas) {
			if (i.getPrevisao() != this.ocorreu) {
				this.destinadoCaixa += i.getValor();
			} else {
				this.destinadoVencedores += i.getValor();
			}
		}
	}                  

	public int getDestinadoCaixa() {
		return this.destinadoCaixa;
	}

	public int getDestinadoVencedores() {
		return this.destinadoVencedores;
	}

	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	/**
	 * Gera uma string que representa o cenário
	 */
	@Override
	public String toString() {
		String ret = "";
		ret += this.descricao + " - ";
		
		if (!this.finalizado)
			ret += "Nao finalizado";
		else {
			if (this.ocorreu)
				ret += "Finalizado (ocorreu)";
			else
				ret += "Finalizado (n ocorreu)";
		}
		return ret;
	}

}
