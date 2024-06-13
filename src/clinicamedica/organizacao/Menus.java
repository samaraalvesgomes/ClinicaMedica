package clinicamedica.organizacao;

/*import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;

import clinicamedica.ClinicaMedica;
import java.sql.Connection;
import connection.ConnectionFactory;
import perfil.*;

import clinicamedica.organizacao.*;
import servicos.*; */

import java.util.Scanner;

public class Menus {
    Scanner scanner = new Scanner(System.in);

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

    public void menuConsultasAgendadas(){
        
        System.out.println("Você quer:");
        System.out.println("1 - Reagendar consulta");
        System.out.println("2 - Cancelar consulta");
        System.out.println("3 - Sair");
        
    }

    
}
