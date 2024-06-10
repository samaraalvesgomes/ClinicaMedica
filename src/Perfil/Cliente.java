package perfil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException; 
import connection.ConnectionFactory;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import clinicamedica.ClinicaMedica;
import clinicamedica.organizacao.LimparConsole;
import connection.ConnectionFactory;
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
        Register register = new Register(); 
        Pessoa pessoa = new Pessoa();
        String clienteAux;

        // Aqui você pode implementar a lógica de autenticação do usuário
        System.out.println("Digite seu nome de usuário: ");
        usuario= scanner.nextLine(); // este novo atributo é necessário?                        <--
        System.out.println("Digite sua senha: ");
        senha = scanner.nextLine();
        // Verificação de credenciais (exemplo simplificado)
        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = con.prepareStatement("SELECT * FROM cadastro_usuario WHERE usuario = ? and senha = ?");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            while (rs.next()) {

                pessoa.setUsuario(rs.getString("usuario"));
                pessoa.setSenha(rs.getString("senha"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        if (usuario.equals(pessoa.getUsuario()) && senha.equals(pessoa.getSenha())) {                     ///<--
            return "verdadeiro"; // Retorna o nome do usuário em caso de sucesso
        } else {
            return "erro"; // Retorna null em caso de falha no login
        }
    }

    
}
