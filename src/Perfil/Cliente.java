package perfil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import connection.ConnectionFactory;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import clinicamedica.ClinicaMedica;
import clinicamedica.organizacao.LimparConsole;
import query.Register;

public class Cliente extends Pessoa {  
    Scanner scanner = new Scanner(System.in);
    Register register = new Register(); 
    Pessoa pessoa = new Pessoa();

    /**
     *  Este método coleta as informações de um usuário não cadastrado no sistema e aciona o método register.create(pessoa);
     */
    public void cadastroUsuario(){
        LimparConsole.clearConsole();
        System.out.println("------------------------------------");
        System.out.println("Sign up"); 
        System.out.println("------------------------------------");
    
        System.out.println("Informe seu usuário:");
        pessoa.usuario = scanner.nextLine();
        System.out.println("Informe sua senha:");
        pessoa.senha = scanner.nextLine();
        
        LimparConsole.clearConsole(); 
        register.create(pessoa);
        
    }

    /**
     * Este método coleta as informações de um usuário cadastrado no sistema e faz a verificação das credenciais inseridas
     * @return Retorna o "verdadeiro" em caso de sucesso no login ou "erro" em caso de falha no login
     */
    public String loginUsuario(){
        //Register register = new Register(); 
        Pessoa pessoa = new Pessoa();
        LimparConsole.clearConsole();
        System.out.println("------------------------------------");
        System.out.println("Sign in"); 
        System.out.println("------------------------------------");
        System.out.println("Digite seu nome de usuário: ");
        usuario = scanner.nextLine(); 
        pessoa.setClienteAux(usuario);   
        System.out.println("Digite sua senha: ");
        senha = scanner.nextLine();

        // Verificação de credenciais 
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
                clienteAux = rs.getString("usuario");
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        if (usuario.equals(pessoa.getUsuario()) && senha.equals(pessoa.getSenha())) {                   
            return "verdadeiro"; 
        } else {
            return "erro"; 
        }
    }
    
}
