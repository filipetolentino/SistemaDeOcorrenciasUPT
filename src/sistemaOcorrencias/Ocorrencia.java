package sistemaOcorrencias;
/**
 * This class a superclass that contains the information of every ocorrencia
 * @author Esteban Silva
 */

import java.time.LocalDate;

public class Ocorrencia {

	private String codigo;	
	private String titulo;
	private String descricao;
	private String prioridade;
	private String estado;
	private String localizacao;
	private String departamento;
	private LocalDate dataCriacao;
	private LocalDate dataLimite;
	/**
	 * @param codigo the specific code to identify the problem
	 * @param titulo the name that will represent the problem
	 * @param descricao the detailed description of the problem by the user
	 * @param prioridade the level of priority
	 * @param estado tells if the problem is open, fixed or late
	 * @param localizacao the specific location provided by the user
	 * @param departamento the department that will be handling the issue
	 * @param prazo is the estimated date for this problem to be solved	
	 */
	
	
	public Ocorrencia(String codigo, String titulo, String descricao, String prioridade, String estado,
			String localizacao, String departamento, int prazo) {
		
		this.codigo = codigo;
		this.titulo = titulo;
		this.descricao = descricao;
		this.prioridade = prioridade;
		this.estado = estado;
		this.localizacao = localizacao;
		this.departamento = departamento;
		dataCriacao = LocalDate.now();
		dataLimite = dataCriacao.plusDays(prazo);
		
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the prioridade
	 */
	public String getPrioridade() {
		return prioridade;
	}
	/**
	 * @param prioridade the prioridade to set
	 */
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}
	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 * @return the localizacao
	 */
	public String getLocalizacao() {
		return localizacao;
	}
	/**
	 * @return the dataCriacao
	 */
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	/**
	 * @return the dataLimite
	 */
	public LocalDate getDataLimite() {
		return dataLimite;
	}
	
	
	
	
	
}

