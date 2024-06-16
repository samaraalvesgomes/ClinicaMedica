package clinicamedica.organizacao;

import java.util.Scanner;

public class Menus {
    Scanner scanner = new Scanner(System.in);

    public void menuEntrar(){

        System.out.println("------------------------------------");
        System.out.println("MENU DE LOGIN");
        System.out.println("------------------------------------");
        System.out.println("1 - Criar usuário");
        System.out.println("2 - Login");
        System.out.println("3 - Sair");

    }

    public void menuPrincipal(){
        
        System.out.println("------------------------------------");
        System.out.println("MENU");
        System.out.println("------------------------------------");
        System.out.println("1 - Marcar consulta");
        System.out.println("2 - Ver consultas agendadas");
        System.out.println("3 - Sair");

    }

    public void menuConsultasAgendadas(){
        
        System.out.println("");
        System.out.println("Você quer:");
        System.out.println("1 - Reagendar consulta");
        System.out.println("2 - Cancelar consulta");
        System.out.println("3 - Sair");
        
    }
    
}
