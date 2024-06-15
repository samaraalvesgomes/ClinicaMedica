package servicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random; 

// import javax.swing.plaf.synth.SynthDesktopIconUI;

import clinicamedica.ClinicaMedica;
import connection.ConnectionFactory;
import perfil.*;
import query.*;
public class Consulta {
    Scanner scanner=new Scanner(System.in);
    Pessoa pessoa = new Pessoa();
    Cliente cliente = new Cliente();
    Register register =new Register();
    int especialidade; 
    String cancelar;
    String confirm;


    public void menuEspecialidades(){
        System.out.println("Marcar consulta");

        System.out.println("1 - Clínica médica");
        System.out.println("2 - Cardiologia");
        System.out.println("3 - Ginecologia");
        System.out.println("4 - Oftalmologia");
        System.out.println("5 - Neurologia");
        System.out.println("6 - Pediatria");
        System.out.println("7 - Sair");
    }

    public void marcarConsulta(){

        System.out.println("Marcar consulta");
        menuEspecialidades();
        System.out.print("Escolha uma especialidade: ");
        especialidade = scanner.nextInt();

        if(especialidade >= 1 && especialidade <= 6){
            String auxDataHora;
            System.out.println("Digite a data e hora que deseja marcar a consulta: dd/MM/yyyy HH:mm");
            auxDataHora = scanner.nextLine();
            System.out.println(auxDataHora);

           // LocalDateTime auxDaLocalDateTime = parseDateTime(auxDataHora);
           // pessoa.setDataHora(auxDaLocalDateTime);
            //Random gerar = new Random();
           // int gerador = gerar.nextInt(99);
            
          /*   DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalTime localTime = LocalTime.now();
            int finalprot = localTime.getMinute() + localTime.getSecond();*/
           // String auxProtocolo = auxDaLocalDateTime.toLocalDate().format(outputFormatter) + String.valueOf(finalprot) ; 
//System.out.println("O n° do protocolo da consulta é: " + auxProtocolo);
           // pessoa.setProtocolo(auxProtocolo);

            register.criarConsulta(pessoa);

        }else if(especialidade == 7){
            System.out.println("Programa finalizado.");
            scanner.close();
            System.exit(0);
        }else{
            System.out.println("Opção inválida. Tente novamente.");
        }   
    }
    public static LocalDateTime parseDateTime(String dateTime) {
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    return LocalDateTime.parse(dateTime, inputFormatter);
}

    public void menuPosMarcarConsulta(){

        System.out.println("1 - Marcar outra consulta");
        System.out.println("2 - Voltar ao menu principal");
        System.out.println("3 - Sair");
    }

    public void cancelarConsulta(){
        
        auxCancelarConsulta();
            
        while((cancelar != pessoa.getProtocolo())){
            auxCancelarConsulta();
        }  
    }

    public void reagendarConsulta(){
        //passar
    }

    private void auxCancelarConsulta(){
        System.out.println("Digite o protocolo da consulta você deseja cancelar:");
        cancelar = scanner.nextLine();
        pessoa.setProtocolo(cancelar);
        
        
        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = con.prepareStatement("SELECT protocolo FROM agendamento_consultas WHERE protocolo = ? ");
            stmt.setString(1, cancelar);
            rs = stmt.executeQuery();

            while (rs.next()) {
                pessoa.setProtocolo(rs.getString("protocolo"));
            }
            register.deletar(pessoa);

        } catch (SQLException ex) {
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    
}
