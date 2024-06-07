package Perfil;

/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException; */
//import connection.ConnectionFactory;
import java.util.Scanner;

import clinicamedica.organizacao.LimparConsole;
import query.Register;


public class Cliente extends Pessoa { // Tentativa de criar uma herança 
    Scanner scanner = new Scanner(System.in);
    Register cadastro = new Register(); 
    Pessoa pessoa = new Pessoa();
    

    public void cadastroUsuario(){

    
        System.out.println("Sign up"); // Coletar as informações de um usuário cadastrado
    
        System.out.println("Informe seu usuário:");
        pessoa.usuario = scanner.nextLine();
        System.out.println("Informe sua senha:");
        pessoa.senha = scanner.nextLine();
        
        cadastro.create(pessoa);

        LimparConsole.clearConsole(); // Limpar console

        System.out.println("Cadastro realizado");
    }

    public String loginUsuario(){

        // Aqui você pode implementar a lógica de autenticação do usuário
        System.out.println("Digite seu nome de usuário: ");
        String usuario = scanner.nextLine(); // este novo atributo é necessário?                        <--
        System.out.println("Digite sua senha: ");
        String senha = scanner.nextLine();

        // Verificação de credenciais (exemplo simplificado)
        if (usuario.equals("samara") && senha.equals("1234")) {                     ///<--
            return "verdadeiro"; // Retorna o nome do usuário em caso de sucesso
        } else {
            return "erro"; // Retorna null em caso de falha no login
        }
    }
}
