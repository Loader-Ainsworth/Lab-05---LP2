
public class Aposta {

	private String apostador;
	private int valor;
	private boolean previsao;
	
	/**
	 * Constrói uma nova aposta
	 * @param apostador nome do apostador
	 * @param valor valor da aposta
	 * @param previsao previsão da aposta
	 */
	public Aposta(String apostador, int valor, String previsao) {
		
		this.apostador = apostador;
		this.valor = valor;
		
		if (previsao.equals("VAI ACONTECER")) this.previsao = true;
		else if (previsao.equals("N VAI ACONTECER")) this.previsao = false;

	}

	public int getValor() {
		return this.valor;
	}
	
	public boolean getPrevisao() {
		return this.previsao;
	}
	
}
