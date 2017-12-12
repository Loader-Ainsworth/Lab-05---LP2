import java.util.ArrayList;

// TODO: toString; ver relação REAL - CENTAVO

public class Cenario {
	
	private boolean ocorreu;
	private String descricao;
	private ArrayList <Aposta> listApostas;
	private int destinadoCaixa;
	private int destinadoVencedores;
	
	public Cenario(String descricao) {
		this.descricao = descricao;
		this.listApostas = new ArrayList<>();
	}
	
	public void cadastrarAposta(String apostador, int valor, String previsao) {
		Aposta nova = new Aposta(apostador, valor, previsao);
		this.listApostas.add(nova);
	}
	
	public int valorTotal() {
		int total = 0;
		for (Aposta i : listApostas) { 
			total += i.getValor();
		}
		return total;
	}
		
	public String exibeApostas() {
		String ret = "";
		for (Aposta i : listApostas) {
			ret += i.toString();
		}
		return ret;
	}
	
	public int getTotalApostas() {
		return this.listApostas.size();
	}
	
	public void finalizaApostas(boolean ocorreu) { 
		this.ocorreu = ocorreu;
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
}
