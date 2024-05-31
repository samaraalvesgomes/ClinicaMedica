package Perfil;

/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException; */
import java.util.Scanner;
//import connection.ConnectionFactory;
import query.Usuario;

public class GerenciarPerfil {
    Scanner scanner = new Scanner(System.in);

    public void cadastrousuario(){

        //instanciando as classes
        Usuario cadastro = new Usuario(); 
        GetSet getset = new GetSet();
    
        System.out.println("Sign up"); // Coletar as informações de um usuário cadastrado
    
        System.out.println("Informe seu usuário:");
        getset.usuario = scanner.nextLine();
        System.out.println("Informe sua senha:");
        getset.senha = scanner.nextLine();
        System.out.println("Cliente ou Funcionario:");
        getset.categoria = scanner.nextLine();
        
        cadastro.create(getset);

    }
}
