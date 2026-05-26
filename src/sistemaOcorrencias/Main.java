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
	
	public static void menu() {
		
		int opcao;
		
		do {
			System.out.println("\n ---Menu---");
			System.out.println("1 - Registrar ocorrência");
			System.out.println("2 - Procurar ocorrência");
			System.out.println("3 - Visualizar por estado");
			
			opcao=scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
			
			case 1:
			
				System.out.print("Código:");
				String codigo = scanner.nextLine();
				
				System.out.print("Titulo:");
				String titulo = scanner.nextLine();
				
				System.out.print("Descrição:");
				String descricao = scanner.nextLine();
				
				System.out.print("Prioridade:");
				String prioridade = scanner.nextLine();
				
				System.out.print("Estado:");
				String estado = scanner.nextLine();
				
				Ocorrencia o = new Ocorrencia(codigo, titulo, descricao, prioridade, estado);
				
				gestor.resgistrarOcorrencia(o);
				
				System.out.print("Ocorrencia registrada");
				break;
				
			case 2:
				
				System.out.print("Código a procurar:");
				String cod = scanner.nextLine();
				
				Ocorrencia encontrada = gestor.procurarOcorrencia(cod);
				
				if(encontrada != null) {
					System.out.print(encontrada);
					
				}else {
					System.out.print("Ocorrencia não encontrada.");
				}
				break;
				
			case 3:
				
				System.out.print("Estado:");
				String est = scanner.nextLine();
				
				gestor.visualizarOcorrencias(est);
				break;
				
				default:
					System.out.print("Opção inválida.");
					
					}
			
				}while(opcao !=0);
			}
		
			public static void main(String [] args) {
				interfaceSistema();
				menu();
		
	}
}
