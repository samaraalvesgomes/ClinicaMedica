
package clinicamedica;

    import java.util.Scanner;
    public class ClinicaMedica {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Bem-vindo(a) a Mais Saúde!!!");

            GerenciarPerfis perfil = new GerenciarPerfis(); //instanciando a classe GerenciarPerfil
            Agenda  calendario = new Agenda(); //instanciando a classe Agenda
            Consulta atendimento = new Consulta(); //instanciando a classe Consulta

            System.out.println("Sign in"); // Coletar as informações de um usuário cadastrado

            System.out.println("Informe seu usuário:");
            perfil.usuario = scanner.nextLine();
            System.out.println("Informe sua senha:");
            perfil.senha = scanner.nextLine();
             
            perfil.signin(); //executar o método singin

            if(){ // se for cliente
                System.out.println("MENU:");
                System.out.println("");
            } else { // se for funcionário
                System.out.println("MENU:");
                System.out.println("");
            }










            System.out.println("Sign up"); // Coletar as informações de um usuário não cadastrado

            






            scanner.close();
        }
        
    }
