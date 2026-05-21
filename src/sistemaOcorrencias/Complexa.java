/**
 * 
 */
package sistemaOcorrencias;

/**
 * This class contain the subclass that extends creating a ocorrencia with high priority
 * @author Esteban Silva 
 */
public class Complexa extends Ocorrencia {
	
	private String link;
	private int tamanho;
	/**
	 * @param codigo
	 * @param titulo
	 * @param descricao
	 * @param prioridade
	 * @param estado
	 * @param localizacao
	 * @param departamento
	 * @param prazo
	 * @param link that leads to the image to be uploaded
	 * @param tamanho its the size of the file
	 */
	
	//constructor complexa!
	public Complexa(String codigo, String titulo, String descricao, String prioridade, String estado,
			String localizacao, String departamento, int prazo, String link, int tamanho) {
		
		super(codigo, titulo, descricao, prioridade, estado, localizacao, departamento, prazo);
		this.link = link;
		this.tamanho = tamanho;
		
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the tamanho
	 */
	public int getTamanho() {
		return tamanho;
	}
	/**
	 * @param tamanho the tamanho to set
	 */
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	
	
	

}
