package servicos;

import clinicamedica.ClinicaMedica;
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
import java.time.format.DateTimeParseException;
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
        System.out.println("Marcar consulta");
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

    public String menuProtocolo() {
        String auxProtocolo;
        System.out.println("Digite o protocolo da consulta que deseja remarcar: ");
        auxProtocolo = scanner.nextLine();
        return auxProtocolo;
    }

    public LocalDateTime menuDataHora() {
        String auxDataHora;
        LocalDateTime dateTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (dateTime == null) {
            System.out.println("Digite a data e hora que deseja marcar a consulta: dd/MM/yyyy HH:mm");
            auxDataHora = scanner.nextLine();

            dateTime = parseDateTime(auxDataHora, formatter);
            if (dateTime == null) {
                System.out.println("Formato inválido. Por favor, digite novamente no formato dd/MM/yyyy HH:mm.");
            }
        }

        return dateTime;
    }

    public void marcarConsulta(String usuario) {
        pessoa.setUsuario(usuario);
        var especialidade = menuEspecialidades();
        var dataHora = menuDataHora();

        pessoa.setEspecialidade(String.valueOf(especialidade));
        pessoa.setDataHora(dataHora);

            
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalTime localTime = LocalTime.now();
        int finalprot = localTime.getMinute() + localTime.getSecond();
        String auxProtocolo = dataHora.toLocalDate().format(outputFormatter) + String.valueOf(finalprot); 
        System.out.println("O n° do protocolo da consulta é: " + auxProtocolo);
        pessoa.setProtocolo(auxProtocolo);

        register.criarConsulta(pessoa);
    }

        private LocalDateTime parseDateTime(String dateTimeString, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public void menuPosMarcarConsulta() {
        System.out.println("1 - Marcar outra consulta");
        System.out.println("2 - Voltar ao menu principal");
        System.out.println("3 - Sair");
    }

    public void cancelarConsulta() {
        auxCancelarConsulta();

        while (!cancelar.equals(pessoa.getProtocolo())) {
            auxCancelarConsulta();
        }
    }

    public void reagendarConsulta() {
        System.out.println("Remarcar consulta");
        var protocolo = menuProtocolo();
        var dataHora = menuDataHora();

        pessoa.setProtocolo(protocolo);
        pessoa.setDataHora(dataHora);

            
        register.reagendarConsulta(pessoa);

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
}
