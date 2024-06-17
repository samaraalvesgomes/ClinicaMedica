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

    /**
     *  Este método é responsável por exibir as opções das especialidades para que possa escolher em qual deseja marcar a consulta, retornando a especialidade desejada
     * @return Retorna o valor da variável "opcao" que corresponde a especialidade escolhida, caso seja um valor inválido, ele chama o próprio método para execução novamente
     */
    public int menuEspecialidades() {
        System.out.println("");
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
        scanner.nextLine();

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

    /**
     * Este método é responsável por coletar o protocolo que o usuario deseja escolher 
     * @return retorna o protocolo da consulta escolhida para fazer reagendamento ou cancelamento da consulta
     */
    public String menuProtocolo() {
        String auxProtocolo;
        System.out.println("Digite o protocolo da consulta que deseja remarcar: ");
        auxProtocolo = scanner.nextLine();
        return auxProtocolo;
    }

    /**
     * Este método coleta e retorna a data e hora que o usuário deseja passar, seja para marcar uma consulta ou reagendar
     * @return o valor da variável dateTime, que seria a data inserida pelo usuário formatada
     */
    public LocalDateTime menuDataHora() {

        String auxDataHora;
        LocalDateTime dateTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (dateTime == null) {
            System.out.println("");
            System.out.println("Digite a data e hora que deseja marcar a consulta: dd/MM/yyyy HH:mm");
            auxDataHora = scanner.nextLine();

            dateTime = parseDateTime(auxDataHora, formatter);
            if (dateTime == null) {
                System.out.println("");
                System.out.println("Formato inválido. Por favor, digite novamente no formato dd/MM/yyyy HH:mm.");
            }
        }
        return dateTime;
    }

    /**
     * Este método é responsável por recuperar e coletar os dados necessários para marcar uma consulta e chamar o método register.criarConsulta(pessoa);
     * @param usuario retorna o nome do usuário contido na variável "usuario"
     */
    public void marcarConsulta(String usuario) {
        LimparConsole.clearConsole();
        pessoa.setUsuario(usuario);
        var especialidade = menuEspecialidades();
        var dataHora = menuDataHora();

        pessoa.setEspecialidade(String.valueOf(especialidade));
        pessoa.setDataHora(dataHora);

            
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalTime localTime = LocalTime.now();
        int finalprot = localTime.getMinute() + localTime.getSecond();
        String auxProtocolo = dataHora.toLocalDate().format(outputFormatter) + String.valueOf(finalprot); 
        LimparConsole.clearConsole();
        System.out.println("");
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

    /**
     * Este método aciona o auxCancelarConsulta(); 
     */
    public void cancelarConsulta() {
        auxCancelarConsulta();

        while (!cancelar.equals(pessoa.getProtocolo())) {
            auxCancelarConsulta();
        }
    }

    /**
     * Este método é responsável por coletar o protocolo, a data e horário (acionando outros métodos) e setar estes valores para acionar o método register.reagendarConsulta(pessoa); (faz a alteração no banco)
     */
    public void reagendarConsulta() {
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("Reagendamento de consulta");
        System.out.println("------------------------------------");
        System.out.println("");
        var protocolo = menuProtocolo();
        var dataHora = menuDataHora();

        pessoa.setProtocolo(protocolo);
        pessoa.setDataHora(dataHora);

        register.reagendarConsulta(pessoa);

    }

    /**
     * Este método identifica a consulta que o usuário deseja cancelar chamando o método register.deletar(pessoa); para fazer o delete no banco
     */
    private void auxCancelarConsulta() {
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("Cancelamento de consulta");
        System.out.println("------------------------------------");
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
