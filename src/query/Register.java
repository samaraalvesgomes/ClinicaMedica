package query;

//import java.sql.ResultSet;
import connection.ConnectionFactory;
import perfil.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Timestamp;

import clinicamedica.*;
import clinicamedica.organizacao.LimparConsole;

public class Register {
    Pessoa pessoa = new Pessoa();

    //Cliente cliente = new Cliente();

    public void create(Pessoa p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO cadastro_usuario (usuario,senha) VALUES (?,?)");
            stmt.setString(1, p.getUsuario());
            stmt.setString(2, p.getSenha());
            
            stmt.executeUpdate();
            System.out.println("Usuário criado!");
        } catch (SQLException ex) {
            System.out.println("Usuario ja cadastrado!");
        } finally {
            //con.close();
        }
    }  

    public void listar(Pessoa pessoa) {
        System.out.println("");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("CONSULTAS AGENDADAS");
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = con.prepareStatement("SELECT protocolo, usuario, especialidades.especialidade, data_agendada FROM agendamento_consultas \r\n" + //
                                "INNER JOIN especialidades ON\r\n" + //
                                "especialidades.id_especialidade = agendamento_consultas.especialidade \r\n" + //
                                "where usuario = ?\r\n" + //
                                "");
            stmt.setString(1, pessoa.getClienteAux());                                      
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
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }  
    

    public void deletar(Pessoa p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            // Verifica se o protocolo existe
            stmt = con.prepareStatement("SELECT protocolo FROM agendamento_consultas WHERE protocolo = ?");
            stmt.setString(1, p.getProtocolo());
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                // Protocolo existe, proceder com a deleção
                stmt = con.prepareStatement("DELETE FROM agendamento_consultas WHERE protocolo = ?");
                stmt.setString(1, p.getProtocolo());
                stmt.executeUpdate();
                LimparConsole.clearConsole();
                System.out.println("Consulta cancelada");
            } else {
                // Protocolo não existe
                LimparConsole.clearConsole();
                System.out.println("Protocolo não existe, por favor tente novamente.");
            }
    
        } catch (SQLException ex) {
            System.out.println("Falha ao cancelar consulta, tente novamente");
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fecha os recursos no bloco finally
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void criarConsulta(Pessoa consulta){
        Connection con = ConnectionFactory.getConnection();

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
    public void reagendarConsulta(Pessoa p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
    
            stmt = con.prepareStatement("SELECT protocolo FROM agendamento_consultas WHERE protocolo = ?");
            stmt.setString(1, p.getProtocolo());
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                // Protocolo existe, proceder com o update
                stmt = con.prepareStatement("UPDATE agendamento_consultas SET data_agendada = ? WHERE protocolo = ?");
                Timestamp timestamp = Timestamp.valueOf(p.getDataHora());
                stmt.setTimestamp(1, timestamp); 
                stmt.setString(2, p.getProtocolo());
                stmt.executeUpdate();
                LimparConsole.clearConsole();
                System.out.println("Consulta reagendada!");
            } else {
                // Protocolo não existe
                LimparConsole.clearConsole();
                System.out.println("Protocolo não existe, por favor tente novamente.");
            }
    
        } catch (SQLException ex) {
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the resources in finally block to ensure they are closed even if an exception is thrown
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
