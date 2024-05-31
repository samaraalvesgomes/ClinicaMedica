package query;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JOptionPane;

import Perfil.GetSet;

public class Usuario {
    public void create(GetSet p) {

        Connection con = ConnectionFactory.getConnection();
        System.out.println("conexão criada com sucesso");

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cadastro_usuario (usuario,senha,categoria) VALUES (?,?,?)");
            stmt.setString(1, p.getUsuario());
            stmt.setString(2, p.getSenha());
            stmt.setString(3, p.getCategoria());
            System.out.println("inserindo registro");

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            //con.close();
        }

    }  
}
