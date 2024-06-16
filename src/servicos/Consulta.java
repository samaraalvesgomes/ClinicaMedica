package servicos;

import clinicamedica.ClinicaMedica;
import clinicamedica.organizacao.LimparConsole;
import connection.ConnectionFactory;
import perfil.Cliente;
import perfil.Pessoa;
import query.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consulta {
    Scanner scanner = new Scanner(System.in);
    Pessoa pessoa = new Pessoa();
    Cliente cliente = new Cliente();
    Register register = new Register();
    int especialidade;
    String cancelar;
    String confirm;

    public int menuEspecialidades() {

        LimparConsole.clearConsole();
        System.out.println("------------------------------------");
        System.out.println("Marcar consulta");
        System.out.println("------------------------------------");
        System.out.println("Escolha a especialidade que deseja:");
        System.out.println("1 - Clínica médica");
        System.out.println("2 - Cardiologia");
        System.out.println("3 - Ginecologia");
        System.out.println("4 - Oftalmologia");
        System.out.println("5 - Neurologia");
        System.out.println("6 - Pediatria");
        System.out.println("7 - Sair");

        System.out.print("Escolha uma especialidade: ");
        int especialidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        if (especialidade >= 1 && especialidade <= 6) {
            return especialidade;
        } else if (especialidade == 7) {
            System.out.println("Programa finalizado.");
            scanner.close();
            System.exit(0);
        }
        System.out.println("Opção inválida. Tente novamente.");
        return menuEspecialidades(); // Chama recursivamente para pedir nova escolha

    }

    public LocalDateTime menuDataHora() {
        String auxDataHora;
        System.out.println("Digite a data e hora que deseja marcar a consulta: dd/MM/yyyy HH:mm");
        auxDataHora = scanner.nextLine();
        System.out.println(auxDataHora);
        return parseDateTime(auxDataHora);
    }

    public void marcarConsulta(String usuario) {

        pessoa.setUsuario(usuario);
        var especialidade = menuEspecialidades();
        System.out.println("");  
        var dataHora = menuDataHora();

        pessoa.setEspecialidade(String.valueOf(especialidade));
        pessoa.setDataHora(dataHora);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalTime localTime = LocalTime.now();
        int finalprot = localTime.getMinute() + localTime.getSecond();
        String auxProtocolo = dataHora.toLocalDate().format(outputFormatter) + String.valueOf(finalprot) ; 
        System.out.println("O n° do protocolo da consulta é: " + auxProtocolo);
        pessoa.setProtocolo(auxProtocolo);

        register.criarConsulta(pessoa);
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dateTime, inputFormatter);
    }


    public void cancelarConsulta() {
        System.out.println("");
        auxCancelarConsulta();

        while (!cancelar.equals(pessoa.getProtocolo())) {
            System.out.println("");
            auxCancelarConsulta();
        }
    }

    private void auxCancelarConsulta() {
        System.out.println("Digite o protocolo da consulta que deseja cancelar:");
        cancelar = scanner.nextLine();
        pessoa.setProtocolo(cancelar);

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT protocolo FROM agendamento_consultas WHERE protocolo = ?");
            stmt.setString(1, cancelar);
            rs = stmt.executeQuery();

            while (rs.next()) {
                pessoa.setProtocolo(rs.getString("protocolo"));
            }
            register.deletar(pessoa);

        } catch (SQLException ex) {
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fechar recursos (ResultSet, PreparedStatement e Connection)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void reagendarConsulta() {
        // Método reservado para implementação futura
    }
}
