
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

	public Ocorrencia newOcorrencia(String titulo, String descricao, String prioridade,
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

	public Complexa newComplexa(String titulo, String descricao, String prioridade,
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
>>>>>>> branch 'master' of git@github.com:alejandrosilva-ec/SistemaDeOcorrenciasUPT.git
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
	
	/**
	 * Lista todas as ocorrências pendentes, ou seja, com estado "Aberta" ou "Em Atraso".
	 *
	 * Antes de listar, percorre todas as ocorrências "Abertas" e verifica se a
	 * dataLimite já foi ultrapassada — se sim, atualiza o estado para "Em Atraso"
	 * automaticamente, evitando que incidentes fiquem esquecidos.
	 *
	 * A listagem é apresentada em dois grupos separados:
	 *   1. Em Atraso  — mostradas primeiro, por serem mais urgentes
	 *   2. Abertas    — dentro do prazo, mas ainda por resolver
	 *
	 * Se não existirem ocorrências pendentes, informa o utilizador.
	 *
	 * @author Filipe Tolentino
	 */
	
	//definir ocorrencias atrasadas
	public void listarPendentes() {
		java.time.LocalDate hoje = java.time.LocalDate.now();
		for (Ocorrencia o : ocorrencias) {
			if (o.getEstado().equalsIgnoreCase("Aberta") && o.getDataLimite().isBefore(hoje)) {
			o.setEstado("Em atraso.");
			System.out.println("[AVISO] Ocorrência " + o.getCodigo() + " passou para \"Em Atraso\" (prazo: " + o.getDataLimite() + ").");
			}
		}
		
		java.util.List<Ocorrencia> emAtraso = new java.util.ArrayList<>();
		java.util.List<Ocorrencia> abertas = new java.util.ArrayList<>();
		
		//adicionar e separar na lista de abertas e fechadas
		for (Ocorrencia o : ocorrencias) {
			if (o.getEstado().equalsIgnoreCase("Em Atraso")) {
				emAtraso.add(o);
			} else if (o.getEstado().equalsIgnoreCase("Aberta")) {
				abertas.add(o);
			}
		}

		//verificar se há ocorrencias pendentes
		if (emAtraso.isEmpty() && abertas.isEmpty()) {
			System.out.println("\n[INFO] Não existem ocorrências pendentes. Tudo resolvido!");
			return;
		}
		
		System.out.println("\n==========================================");
		System.out.println("       OCORRÊNCIAS PENDENTES              ");
		System.out.println("==========================================");
		
		//Mostrar Em Atraso primeiro (mais urgentes)
		if (!emAtraso.isEmpty()) {
			System.out.println("\n⚠  EM ATRASO (" + emAtraso.size() + ")");
			System.out.println("------------------------------------------");
			for (Ocorrencia o : emAtraso) {
					o.printOcorrencia();
			System.out.println("------------------------------------------");
			}
		   }

		//Mostrar Abertas
		if (!abertas.isEmpty()) {
			System.out.println("\n✔  ABERTAS (" + abertas.size() + ")");
			System.out.println("------------------------------------------");
			for (Ocorrencia o : abertas) {
				o.printOcorrencia();
			System.out.println("------------------------------------------");
			}
		 }
		
		//numero total de pendencias
		System.out.println("\nTotal pendentes: " + (emAtraso.size() + abertas.size())
				+ "  |  Em Atraso: " + emAtraso.size()
				+ "  |  Abertas: " + abertas.size());
		System.out.println("==========================================\n");
	  }

	}
