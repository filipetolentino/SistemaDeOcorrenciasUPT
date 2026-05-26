/**
 * 
 */
package sistemaOcorrencias;

/**
 * This class contains an array of every ocorrencia created
 * @author Esteban Silva
 * 
 * This class contains the methods, registarOcorrencia and definirPrioridade.
 * Também contém a geração de códigos vinculados a ocorrencia automatica e a definição de prazo por prioridade.
 * @author Filipe Tolentino
 */

import java.util.ArrayList;

import projeto.Ocorrencia;

public class Gestor {

	private ArrayList<Ocorrencia> ocorrencias;
	private int contadorCodigo;
	
	/**
	 * Constructor of the class Gestor
	 */
	public Gestor() {
		ocorrencias = new ArrayList<Ocorrencia>();
		contadorCodigo = 1;
	}

	/**
	 * @return the ocorrencias
	 */
	public ArrayList<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}
	
	/**
	 * @author Filipe Tolentino
	 * 
	 * Regista uma nova ocorrência no sistema.
	 * O código é gerado automaticamente, o estado inicial é sempre "Aberta"
	 * e o prazo é definido com base na prioridade indicada.
	 *
	 * @param titulo  o título da ocorrência (não pode ser nulo ou vazio)
	 * @param descricao  a descrição detalhada do problema (não pode ser nula ou vazia)
	 * @param prioridade  o nível de prioridade: "Alta", "Media" ou "Baixa"
	 * @param localizacao a localização onde o problema ocorre (não pode ser nula ou vazia)
	 * @param departamento o departamento responsável pelo tratamento
	 * @return a Ocorrencia criada e registada, ou null se os dados forem inválidos
	 */
	
	public Ocorrencia registarOcorrencia(String titulo, String descricao, String prioridade, String localizacao, String departamento) {
		
		if (titulo == null || titulo.trim().isEmpty()) {
			System.out.println("[Erro] Título não pode estar vazio! ");
			return null;
		}
		if (descricao == null || descricao.trim().isEmpty()) {
			System.out.println("[Erro] A descrição não pode estar vazia! ");
			return null;
		}
		if (localizacao == null || localizacao.trim().isEmpty()) {
			System.out.println("[Erro] A localização não pode estar vazia! ");
			return null;
		}
		if (departamento == null || departamento. trim().isEmpty()) {
			System.out.println("[Erro] O Departamento não pode estar vazio! ");
			return null;
		}
		
		/**
		 * Definir prioridade.
		 * @author Filipe Tolentino
		 */
		
		String prioridadeDefinida = definirPrioridade(prioridade);
		if (prioridadeDefinida == null) {
			System.out.println("[Erro] Prioridade definida é inválida" + prioridade + "Use Alta ou Baixa.");
			return null;
		}
		
		//Geração automática do código vinculado a ocorrencia.
		
		String codigo = String.format("OC-%03d", contadorCodigo);
		
		//Definir Prazo automaticamente.
		
		int prazo = definirPrazoPrio(prioridadeDefinida);
		
		//Estado inicial
		
		String estado = "Aberta";
		
		//Registro da nova ocorrencia
		
		Ocorrencia novaOcorrencia = new Ocorrencia(
				codigo,
				titulo.trim(),
				descricao.trim(),
				prioridadeDefinida,
				estado,
				localizacao.trim(),
				departamento.trim(),
				prazo);
		
		ocorrencias.add(novaOcorrencia);
		contadorCodigo++;
		
		System.out.println("Ocorrência registrada com sucesso! CÓDIGO: " + codigo + "PRAZO: " + prazo + "dias.");
				
		return novaOcorrencia;
				
	}
	
	/**
	 * Define a Prioridade da ocorrencia. Alta ou Baixa.
	 * @param prioridade o texto introduzido
	 * @return Prioridade ou null se inválido.
	 */
	
	private String definirPrioridade(String prioridade) {
		if (prioridade == null)
			return null;
		
		switch (prioridade.trim().toLowerCase()) {
		case "alta":
		return "Alta";
		
		case "baixa":
		return "Baixa";
		
		default:
		return null;
		}
	}
	
	/**
	 * Define o prazo da ocorrencia baseada na prioridade
	 * Alta = 3 dias
	 * Baixa = 5 dias
	 * @param prioridade a prioridade ja definida antes.
	 * @return numero de dias para resolver o problema.
	 */
	
	private int definirPrazoPrio(String prioridade) {
		switch (prioridade) {
		case "Alta":
		return 3;
		
		case "Baixa":
		return 5;
		
		default: return 0;
		}
	}
	
	public Ocorrencia procurarOcorrencia(String codigo) {
		for(Ocorrencia i : ocorrencias) {
			if(i.getCodigo().equals(codigo)) {
				return i;
			}
		}
		return null;
	}
	
	public void visualizarOcorrencias(String estado) {
		for(Ocorrencia o : ocorrencias) {
			if(o.getEstado().equalsIgnoreCase(estado)) {
				System.out.println(o);
			}
		}
	}
	
}
