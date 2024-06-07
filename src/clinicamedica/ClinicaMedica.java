package clinicamedica;

import java.sql.Connection;
import java.util.Scanner;
import clinicamedica.organizacao.*;
import Perfil.*;
import connection.ConnectionFactory;

public class ClinicaMedica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cliente perfil = new Cliente();
        Menus menus = new Menus();

        String logado = null;
        int opcao;

        System.out.println("Bem-vindo(a) a Mais Saúde!!!");

        while (true) {
            // Se o usuário não estiver logado, mostrar o menu de login
            if (logado == null) {
                menus.menuEntrar(); // Exibir menu de login
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt(); // Captura a entrada do usuário para a variável 'opcao'
                
                switch (opcao) {
                    case 1:
                        perfil.cadastroUsuario();
                        break; // Voltar para o menu de login após criar usuário

                    case 2:
                        logado = perfil.loginUsuario();
                        if ("verdadeiro".equals(logado)) { // Comparação correta de strings
                            System.out.println("Bem-vindo(a) " + logado); // Adicione um espaço após "Bem-vindo(a)"
                        } else {
                            logado = null; // Reset logado se login falhar
                            System.out.println("Falha no login. Tente novamente.");
                        }
                        break;

                    case 3:
                        System.out.println("Programa finalizado.");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else { 
                // Login feito, entrada para gerenciamento do cliente
                menus.menuPrincipal(); // Menu do site após o login
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt(); // Captura a entrada do usuário para a variável 'opcao'
                
                switch (opcao) {
                    case 1:
                        // Lógica para agendar consulta
                        System.out.println("Marcar consulta");
                        Connection con = ConnectionFactory.getConnection();
                        // Adicione lógica para marcar consulta aqui
                        break;

                    case 2:
                        // Lógica para ver histórico de consultas
                        System.out.println("Ver consultas agendadas");
                        // Adicione lógica para ver histórico de consultas aqui
                        break;

                    case 3:
                        System.out.println("Programa finalizado.");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                }
            }
        }
    }
}