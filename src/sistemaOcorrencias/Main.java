/**
 * This class contains the menu for the first version of the program
 */

package sistemaOcorrencias;

import java.util.Scanner;

public class Main {

	public static void interfaceSistema() {
		
		System.out.println("======================================");
		System.out.println("         SISTEMA DE OCORRENCIAS       ");
		System.out.println("======================================");
		
		
	}
	
	static Scanner scanner = new Scanner(System.in);
	static Gestor gestor = new Gestor();
	
	public static void menu(Gestor ocorrencias) {
		
		int opcao;
		
		do {
			System.out.println("\n ---Menu---");
			System.out.println("1 - Registrar ocorrência");
			System.out.println("2 - Procurar ocorrência");
			System.out.println("3 - Listar pendentes (Abertas + Em Atraso)");
			
			opcao=scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
			
			case 1:
			
				System.out.println("Escolha o local da sua ocorrencia:");
				System.out.println("1-Sala");
				System.out.println("2-Cantina");
				System.out.println("3-Parque de estacionamento");
				
				System.out.print("Opção:");
				int opcaoLocal = scanner.nextInt();
				scanner.nextLine();
				
				Local localizacao = null;
				String localString;
				
				switch(opcaoLocal) {
				
				case 1:
					
					System.out.println("Número da sala:");
					String numeroSala = scanner.nextLine();
					
					localizacao = new Local("Sala", numeroSala);
					localString = localizacao.toString();
					
					break;
					
				case 2:
					
					localizacao = new Local("Cantina", "");
					localString = localizacao.toString();
					
					break;
					
				case 3:
					
					localizacao = new Local("Parque de estacionamento","");
					localString = localizacao.toString();
					
					break;
					
				default:
					
					System.out.println("Opção inválida.");
					continue;
				}
				
				System.out.print("Titulo:");
				String titulo = scanner.nextLine();
				
				System.out.print("Descrição:");
				String descricao = scanner.nextLine();
				
				System.out.print("Prioridade:");
				String prioridade = scanner.nextLine();
				
				
				System.out.print("Departamento:");
				String departamento = scanner.nextLine();
				
				
				if(prioridade.equalsIgnoreCase("Baixa")) {
				
				    ocorrencias.newOcorrencia(titulo,descricao,prioridade,localString,departamento);
				
				    System.out.print("Ocorrencia registrada");
				    break;
				}else if(prioridade.equalsIgnoreCase("Alta")) {
					
					System.out.print("Link:");
					String link = scanner.nextLine();
					
					System.out.print("Tamanho:");
					int tamanho = scanner.nextInt();
					scanner.nextLine();
					
					ocorrencias.newComplexa(titulo,descricao,prioridade,localString,departamento,link,tamanho);
					break;
				}
				
			case 2:
				
				/*System.out.print("Código a procurar:");
				String cod = scanner.nextLine();
				
				Ocorrencia encontrada = gestor.procurarOcorrencia(cod);
				
				if(encontrada != null) {
					System.out.print(encontrada);
					
				}else {
					System.out.print("Ocorrencia não encontrada.");
				}
				break;*/
				
				System.out.print("Código a procurar:");
				String cod = scanner.nextLine();
				Boolean encontrado = false;
				
				for (Ocorrencia o : ocorrencias.getOcorrencias()) {
					if(o.getCodigo().equalsIgnoreCase(cod)) {
						o.printOcorrencia();
						encontrado = true;
						break;
					}
				}
				if(encontrado == false) {
					System.out.println("A ocorrência com o código " + cod + " não existe!");
				}
				break;
				
			case 3:
				
				ocorrencias.listarPendentes();
				break;
				
				default:
					System.out.print("Opção inválida.");
					
					}
				
					
				}while(opcao !=0);
			}
		
			public static void main(String [] args) {
				
				Gestor ocorrencias = new Gestor();
				interfaceSistema();
				menu(ocorrencias);
		
	}
}