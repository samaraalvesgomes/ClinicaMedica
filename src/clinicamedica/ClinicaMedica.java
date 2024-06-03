package clinicamedica;

import java.util.Scanner;
import Perfil.GerenciarPerfil;

public class ClinicaMedica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciarPerfil perfil = new GerenciarPerfil();
        String logado = null ;

        System.out.println("Bem-vindo(a) a Mais Saúde!!!");

        while (true) {
            // Se o usuário não estiver logado, mostrar o menu de login
            if (logado == null) {
                // Exibir menu de login
                System.out.println("MENU DE LOGIN");
                System.out.println("A - Criar usuário");
                System.out.println("B - Login");
                System.out.println("C - Sair");
                String opcao = scanner.nextLine();

                switch (opcao.toUpperCase()) {
                    case "A":
                        perfil.cadastroUsuario();
                        break; // Voltar para o menu de login após criar usuário
  /*conferencia */  case "B":
                        logado = perfil.loginUsuario();

                        if (logado == "verdadeiro") {
                            System.out.println("Bem-vindo(a)" + logado); //retornar o nome da pessoa logada
                            break; 
                        } else {
                            System.out.println("Falha no login. Tente novamente.");
                        }
                        
   /*OK*/           case "C":
                        System.out.println("Programa finalizado.");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else { // refazer para 2 telas




                // Menu do site após o login
                System.out.println("Bem-vindo(a) ao Site da Clínica Médica");
                System.out.println("D - Agendar consulta");
                System.out.println("E - Ver histórico de consultas");
                System.out.println("F - Sair");

                String opcao = scanner.nextLine();

                switch (opcao.toUpperCase()) {
                    case "D":
                        // Lógica para agendar consulta
                        System.out.println("Funcionalidade de agendar consulta em desenvolvimento...");
                        break;
                    case "E":
                        // Lógica para ver histórico de consultas
                        System.out.println("Funcionalidade de ver histórico de consultas em desenvolvimento...");
                        break;
                    case "F":
                        System.out.println("Saindo...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }
}