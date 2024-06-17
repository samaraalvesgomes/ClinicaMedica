package clinicamedica;
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

        String logado = null;
        int opcao;

        System.out.println("Bem-vindo(a) a Mais Saúde!!!");
        
        while (true) {
            /**
             * Esta estrutura é responsável por fazer a autenticação do usuário, se a verificação for aprovada, será liberado o sistema para utilização (else) 
             */
            if (logado == null) {
                menus.menuEntrar(); 
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();

                /**
                 * Este switch é responsável por executar as funcionalidades correspondentes a opção escolhida no menu de entrada
                 */
                switch (opcao) {
                    case 1: // cadastrar usuário

                        cliente.cadastroUsuario();
                        break; 

                    case 2: // Fazer login

                        logado = cliente.loginUsuario();

                        while ("erro".equals(logado)) { 
                            System.out.println("Usuario invalido. Tente novamente.");
                            logado = cliente.loginUsuario();
                        }  
                        LimparConsole.clearConsole();
                        System.out.println("Bem-vindo(a): " + cliente.getUsuario().toUpperCase()); 
                        break; 

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
            }else { 

                // Login feito, entrada para gerenciamento do cliente              
                menus.menuPrincipal(); 
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt(); 
                
                /**
                 * Este switch é responsável por executar as funcionalidades correspondentes a opção escolhida no menu principal do sistema
                 */
                switch (opcao) {
                    case 1:// Agendar consulta
                        consulta.marcarConsulta(cliente.getUsuario()); 
                        break;

                    case 2: //Listar consultas agendadas

                        register.listar(cliente); 
                        menus.menuConsultasAgendadas(); 
                        System.out.print("Escolha uma opção: ");
                        opcao = scanner.nextInt(); 
                         
                        switch (opcao) {
                            case 1: //Reagendar
                                
                                consulta.reagendarConsulta();
                                break;

                            case 2: //cancelar

                                consulta.cancelarConsulta();
                                break;
                            case 3:// agendar consulta
                            
                                consulta.marcarConsulta(cliente.getUsuario()); 
                                break;

                            case 4://sair
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