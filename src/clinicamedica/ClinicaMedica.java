package clinicamedica;

/*import java.sql.Connection;
import connection.ConnectionFactory;*/
import java.util.Scanner;

import clinicamedica.organizacao.*;
import servicos.*;
import query.*;
import perfil.*;
public class ClinicaMedica {
    
    public static void main(String[] args) {
        LimparConsole.clearConsole();
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();
        Menus menus = new Menus();
        Register register = new Register();
        Consulta consulta=new Consulta();

        //Pessoa pessoa = new Pessoa();
        //Agenda agenda=new Agenda();

        String logado = null;
        int opcao;

        

        System.out.println("Bem-vindo(a) a Mais Saúde!!!");

        while (true) {
            if (logado == null) {
                menus.menuEntrar(); // Exibir menu de login
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt(); // Escolher opção do menu
                
                switch (opcao) {
                    case 1: // cadastrar usuário

                        cliente.cadastroUsuario();
                        break; // Voltar para o menu de login após criar usuário

                    case 2: // Fazer login

                        logado = cliente.loginUsuario();

                        while ("erro".equals(logado)) { //Login recusado
                            System.out.println("Usuario invalido. Tente novamente.");
                            logado = cliente.loginUsuario();
                        }  
                        LimparConsole.clearConsole();
                        System.out.println("Bem-vindo(a): " + cliente.getUsuario().toUpperCase()); //Login aceito 
                        break; // Pula para linha 64

                    case 3: // Finalizar programa

                        System.out.println("");
                        System.out.println("--------------------------------------");
                        System.out.println("Obrigada por escolher a Mais Saúde! ");
                        System.out.println("--------------------------------------");
                        System.out.println("");
                        scanner.close();
                        System.exit(0);

                    default: 

                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else { 
                // Login feito, entrada para gerenciamento do cliente              
                menus.menuPrincipal(); // Menu pós login
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt(); // Escolha da opçao do menu
                
                switch (opcao) {
                    case 1:// Agendar consulta
                        consulta.marcarConsulta(cliente.getUsuario()); 
                        break;

                    case 2: //Listar consultas agendadas

                        register.listar(cliente); // Chamar uma lista de todas as consultas marcadas deste paciente
                        menus.menuConsultasAgendadas(); //Reagendar ou Cancelar?
                        System.out.print("Escolha uma opção: ");
                        opcao = scanner.nextInt(); // Escolha da opçao do menu
                         
                        switch (opcao) {
                            case 1: //Reagendar
                                
                                consulta.reagendarConsulta();
                                break;

                            case 2: //cancelar

                                consulta.cancelarConsulta();
                                break;

                            case 3://sair
                                System.out.println("");
                                System.out.println("--------------------------------------");
                                System.out.println("Obrigada por escolher a Mais Saúde! ");
                                System.out.println("--------------------------------------");
                                System.out.println("");
                                scanner.close();
                                System.exit(0);
                         
                            default: //erro

                                System.out.println("Opção inválida. Tente novamente.");

                         }
                        break;

                    case 3: //Sair do programa

                        System.out.println("");
                        System.out.println("--------------------------------------");
                        System.out.println("Obrigada por escolher a Mais Saúde! ");
                        System.out.println("--------------------------------------");
                        System.out.println("");
                        scanner.close();
                        System.exit(0);

                    default:

                        System.out.println("Opção inválida. Tente novamente!");
                }
            }
        }
    }
}