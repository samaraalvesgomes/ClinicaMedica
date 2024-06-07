package clinicamedica.organizacao;

import java.util.Scanner;

public class Menus {
    Scanner scanner = new Scanner(System.in);
    private int opcao;

    public void menuEntrar(){

        System.out.println("MENU DE LOGIN");
        System.out.println("1 - Criar usuário");
        System.out.println("2 - Login");
        System.out.println("3 - Sair");

    }

    public void menuPrincipal(){

        System.out.println("1 - Marcar consulta");
        System.out.println("2 - Ver consultas agendadas");
        System.out.println("3 - Sair");
    }
}
