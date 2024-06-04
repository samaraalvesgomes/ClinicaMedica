package Perfil;

/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException; */

import java.util.Scanner;

import clinicamedica.LimparConsole;
//import connection.ConnectionFactory;
import query.Usuario;

public class GerenciarPerfil extends GetSet { // Tentativa de criar uma herança 
    Scanner scanner = new Scanner(System.in);

    public void cadastroUsuario(){

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

        LimparConsole.clearConsole(); // Limpar console

        System.out.println("Cadastro realizado");
    }

    public String loginUsuario(){

        // Aqui você pode implementar a lógica de autenticação do usuário
        System.out.println("Digite seu nome de usuário: ");
        String nome = scanner.nextLine(); // este novo atributo é necessário?                        <--
        System.out.println("Digite sua senha: ");
        String senha = scanner.nextLine();

        // Verificação de credenciais (exemplo simplificado)
        if (nome.equals("samara") && senha.equals("1234")) {                     ///<--
            return "verdadeiro"; // Retorna o nome do usuário em caso de sucesso
        } else {
            return "erro"; // Retorna null em caso de falha no login
        }
    }
}
