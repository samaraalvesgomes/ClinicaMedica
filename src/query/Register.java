package query;

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
            System.out.println("Registro criado!");
        } catch (SQLException ex) {
            System.out.println("Usuario ja cadastrado!");
        } finally {
            //con.close();
        }
    }  

    public void listar(Pessoa pessoa) {
        LimparConsole.clearConsole();
        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.println("CONSULTAS AGENDADAS");
        System.out.println("");
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
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

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
            LimparConsole.clearConsole();
            System.out.println("Consulta cancelada");

        } catch (SQLException ex) {
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    public void criarConsulta(Pessoa consulta){
        System.out.println(consulta.getClienteAux() + consulta.getUsuario());
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
            LimparConsole.clearConsole();
            System.out.println("consulta agendada!");

            stmt.executeUpdate();

        } catch (SQLException ex) {

            System.out.println(ex);

        } finally {

            //con.close();
        }
    }
}
