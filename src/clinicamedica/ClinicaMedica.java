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
                        System.out.println("Bem-vindo(a): " + cliente.getUsuario().toUpperCase()); //Login aceito 
                        break; // Pula para linha 58

                    case 3: // Finalizar programa

                        System.out.println("Obrigada por escolher a Mais Saúde! ");
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

                        consulta.marcarConsulta(); 
                        consulta.menuPosMarcarConsulta(); //marcar outra ou voltar menu principal?
                        System.out.print("Escolha uma opcao ");
                        opcao = scanner.nextInt(); // Escolha da opçao do menu

                        switch(opcao){
                            case 1:

                                //marcar outra consulta

                            case 2:

                                //voltar para o menu principal
                                break;

                            case 3:

                            System.out.println("Obrigada por escolher a Mais Saúde! ");
                            scanner.close();
                            System.exit(0);

                            default:

                            System.out.println("Opção inválida. Tente novamente.");

                        }  
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

                                System.out.println("Obrigada por escolher a Mais Saúde! ");
                                scanner.close();
                                System.exit(0);
                         
                            default: //erro

                                System.out.println("Opção inválida. Tente novamente.");

                         }
                        break;

                    case 3: //Sair do programa

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