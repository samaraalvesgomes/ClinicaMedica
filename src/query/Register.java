package query;

//import java.sql.ResultSet;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;

import clinicamedica.*;
import perfil.Pessoa;
//import perfil.Cliente;

public class Register {
    Pessoa pessoa = new Pessoa();

    //Cliente cliente = new Cliente();

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

    public void listar(Pessoa pessoa) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = con.prepareStatement("SELECT * FROM agendamento_consultas where usuario = ?");
            stmt.setString(1, pessoa.getClienteAux());
            System.out.println(pessoa.getClienteAux());                                       
            rs = stmt.executeQuery();

            while (rs.next()) {

                pessoa.setProtocolo(rs.getString("protocolo"));
                pessoa.setUsuario(rs.getString("usuario"));
                pessoa.setEspecialidade(rs.getString("especialidade"));
                java.sql.Timestamp timestamp = rs.getTimestamp("data_agendada");

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                pessoa.setDataHora(localDateTime);
                System.out.println(pessoa.toString());

            }
            if (pessoa.getEspecialidade() == null){
                System.out.println("Você não possui nenhuma consulta agendada.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

    }  

    public void deletar(Pessoa protocolo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM agendamento_consultas WHERE protocolo= ?");
            stmt.setString(1, protocolo.getProtocolo());
            stmt.executeUpdate();
            System.out.println("Consulta cancelada");

        } catch (SQLException ex) {
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    public void criarConsulta(Pessoa consulta){

        Connection con = ConnectionFactory.getConnection();
        System.out.println("conexão criada com sucesso");

        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO agendamento_consultas (protocolo, usuario, especialidade, data_agendada) VALUES (?,?,?,?)");
            stmt.setString(1, consulta.getProtocolo());
            stmt.setString(2, consulta.getUsuario());
            stmt.setString(3, consulta.getEspecialidade());
            Timestamp timestamp = Timestamp.valueOf(consulta.getDataHora());
            stmt.setTimestamp(4, timestamp);
            System.out.println("consulta agendada!");

            stmt.executeUpdate();

        } catch (SQLException ex) {

            System.out.println(ex);

        } finally {

            //con.close();
        }
    }
}
