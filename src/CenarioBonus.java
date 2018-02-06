/**
 * 
 */

/**
 * @author thulioicc
 *
 */
public class CenarioBonus extends Cenario{
	
	private int bonus;

	public CenarioBonus(String descricao, int bonus) {
		super(descricao);
		this.bonus = bonus;
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

}
