package clinicamedica;

import java.sql.Connection;
import connection.ConnectionFactory;
import perfil.*;

import java.util.Scanner;
import clinicamedica.organizacao.*;
import servicos.*;
import query.*;

public class ClinicaMedica {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();
        Menus menus = new Menus();
        Register register = new Register();

        String logado = null;
        int opcao, especialidade;
        

        System.out.println("Bem-vindo(a) a Mais Saúde!!!");

        while (true) {
            // Se o usuário não estiver logado, mostrar o menu de login
            if (logado == null) {
                menus.menuEntrar(); // Exibir menu de login
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt(); // Captura a entrada do usuário para a variável 'opcao'
                
                switch (opcao) {
                    case 1:
                        cliente.cadastroUsuario();
                        break; // Voltar para o menu de login após criar usuário

                    case 2:
                        logado = cliente.loginUsuario();

                        while ("erro".equals(logado)) {
                            System.out.println("Usuario invalido. Tente novamente.");
                            logado = cliente.loginUsuario();
                        }  
                            System.out.println("Bem-vindo(a): " + cliente.getUsuario().toUpperCase()); //retornar o nome da pessoa logada
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
                Consulta consulta=new Consulta();
                Agenda agenda=new Agenda();

                menus.menuPrincipal(); // Menu do site após o login
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt(); // Captura a entrada do usuário para a variável 'opcao'
                
                switch (opcao) {
                    case 1:
                        // Lógica para agendar consulta
                        System.out.println("Marcar consulta");
                        Connection con = ConnectionFactory.getConnection(); //??
                        
                        consulta.menuEspecialidades();
                        System.out.print("Escolha uma opção: ");
                        especialidade = scanner.nextInt();

                        if(especialidade >= 1 && especialidade <= 6){

                            agenda.datasDisponiveis();
                            agenda.horariosDisponiveis();

                        }else if(especialidade == 7){

                            System.out.println("Programa finalizado.");
                            scanner.close();
                            System.exit(0);

                        }else{
                            System.out.println("Opção inválida. Tente novamente.");
                        }  
                        // Adicione lógica para marcar consulta aqui

                        break;

                    case 2:
                        // Lógica para ver histórico de consultas
                        register.listar();
                        // Chamar uma lista de todas as consultas marcadas deste paciente

                        menus.menuConsultasAgendadas();
                        System.out.print("Escolha uma opção: ");
                        opcao = scanner.nextInt(); // Captura a entrada do usuário para a variável 'opcao'
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