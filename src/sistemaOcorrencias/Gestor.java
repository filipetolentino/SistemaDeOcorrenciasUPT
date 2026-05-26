
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
	 *  "Alta"  → requer link e tamanho → cria uma Complexa
	 *  "Baixa" → cria uma Ocorrencia normal
	 *
	 */
	
	public Ocorrencia registarOcorrencia(String titulo, String descricao, String prioridade,
			String localizacao, String departamento, String link, int tamanho) {
 
		
		if (titulo == null || titulo.trim().isEmpty()) {
			System.out.println("[ERRO] O título não pode estar vazio.");
			return null;
		}
		if (descricao == null || descricao.trim().isEmpty()) {
			System.out.println("[ERRO] A descrição não pode estar vazia.");
			return null;
		}
		if (localizacao == null || localizacao.trim().isEmpty()) {
			System.out.println("[ERRO] A localização não pode estar vazia.");
			return null;
		}
		if (departamento == null || departamento.trim().isEmpty()) {
			System.out.println("[ERRO] O departamento não pode estar vazio.");
			return null;
		}
		
		/**
		 * Set priority.
		 * @author Filipe Tolentino
		 */
		
		String prioridadeDefinida = definirPrioridade(prioridade);
		if (prioridadeDefinida == null) {
			System.out.println("[Erro] Prioridade definida é inválida" + prioridade + "Use Alta ou Baixa.");
			return null;
		}
		
		//Prioridade Alta obriga a ser Complexa
				if (prioridadeDefinida.equals("Alta")) {
					if (link == null || link.trim().isEmpty()) {
						System.out.println("[ERRO] Ocorrências de prioridade Alta requerem um link de anexo.");
						return null;
					}
					if (tamanho <= 0) {
						System.out.println("[ERRO] Ocorrências de prioridade Alta requerem um tamanho de ficheiro válido.");
						return null;
					}
					return newComplexa(titulo, descricao, prioridadeDefinida, localizacao, departamento, link, tamanho);
				}
		 
				// Baixa cria uma Ocorrencia normal
				return newOcorrencia(titulo, descricao, prioridadeDefinida, localizacao, departamento);
			}
		
	/**
	 * Método de fábrica privado que constrói e regista uma Ocorrencia normal.
	 * Usado internamente por registarOcorrencia() para prioridade Baixa.
	 *
	 * @return a Ocorrencia criada
	 */
	private Ocorrencia newOcorrencia(String titulo, String descricao, String prioridade,
			String localizacao, String departamento) {
 
		String codigo = gerarCodigo();
		int prazo     = definirPrazoPrio(prioridade);
 
		Ocorrencia oc = new Ocorrencia(
				codigo,
				titulo.trim(),
				descricao.trim(),
				prioridade,
				"Aberta",
				localizacao.trim(),
				departamento.trim(),
				prazo);
 
		ocorrencias.add(oc);
		contadorCodigo++;
 
		System.out.println("[OK] Ocorrência registada! Código: " + codigo + " | Tipo: Normal | Prazo: " + prazo + " dia(s).");
		return oc;
	}
 
	/**
	 * Método de fábrica privado que constrói e regista uma Ocorrencia Complexa.
	 * Usado internamente por registarOcorrencia() quando a prioridade é Alta.
	 *
	 * @return a Complexa criada
	 */
	private Complexa newComplexa(String titulo, String descricao, String prioridade,
			String localizacao, String departamento, String link, int tamanho) {
 
		String codigo = gerarCodigo();
		int prazo     = definirPrazoPrio(prioridade);
 
		Complexa oc = new Complexa(
				codigo,
				titulo.trim(),
				descricao.trim(),
				prioridade,
				"Aberta",
				localizacao.trim(),
				departamento.trim(),
				prazo,
				link.trim(),
				tamanho);
 
		ocorrencias.add(oc);
		contadorCodigo++;
 
		System.out.println("[OK] Ocorrência registada! Código: " + codigo + " | Tipo: Complexa | Prazo: " + prazo + " dia(s).");
		return oc;
	}
 
	/**
	 * Gera o próximo código sequencial no formato OC-001, OC-002, ...
	 *
	 * @return o código gerado
	 */
	private String gerarCodigo() {
		return String.format("OC-%03d", contadorCodigo);
	}
 
	/**
	 * Padroniza o texto da prioridade para o formato padrão do sistema.
	 * Aceita variações de maiúsculas/minúsculas.
	 *
	 * @param prioridade o texto introduzido pelo utilizador
	 * @return "Alta" ou "Baixa", ou null se inválido
	 */
	private String definirPrioridade(String prioridade) {
		if (prioridade == null) return null;
		switch (prioridade.trim().toLowerCase()) {
			case "alta":  return "Alta";
			case "baixa": return "Baixa";
			default:      return null;
		}
	}
 
	/**
	 * Define o prazo em dias com base na prioridade da ocorrência.
	 * Alta  → 3 dias
	 * Baixa → 5 dias
	 *
	 * @param prioridade a prioridade já normalizada
	 * @return número de dias para resolver a ocorrência
	 */
	private int definirPrazoPrio(String prioridade) {
		switch (prioridade) {
			case "Alta": return 3;
			default:     return 5; // Baixa
		}
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
