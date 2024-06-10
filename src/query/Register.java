package query;

import connection.ConnectionFactory;
import perfil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import clinicamedica.*;

public class Register {
    public void create(Pessoa p) {

        Connection con = ConnectionFactory.getConnection();
        System.out.println("conexão criada com sucesso");

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cadastro_usuario (usuario,senha) VALUES (?,?)");
            stmt.setString(1, p.getUsuario());
            stmt.setString(2, p.getSenha());
            System.out.println("inserindo registro");

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            //con.close();
        }

    }  
    public void listar() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
         Pessoa pessoa = new Pessoa();
        try {

            stmt = con.prepareStatement("SELECT * FROM agendamento_consultas where usuario = ?");
            stmt.setString(1,pessoa.getUsuario());
            System.out.println(pessoa.getUsuario());
            rs = stmt.executeQuery();

            while (rs.next()) {

                pessoa.setProtocolo(rs.getInt("protocolo"));
                pessoa.setUsuario(rs.getString("usuario"));
                pessoa.setEspecialidade(rs.getString("especialidade"));
                pessoa.setDataHora(rs.getDate("data_agendada"));
                System.out.println(pessoa.toString());
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

    }  

}
