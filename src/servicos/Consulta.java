package servicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    int especialidade, cancelar;
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
            //buscar no banco todos os horários disponíveis da especialidade escolhida
            //Escolher data e hora
	        //gerar protocólo para registrar no banco
	        //buscar usuário e especialidade
	        //registrar na tabela agendamento_consultas
        }else if(especialidade == 7){
            System.out.println("Programa finalizado.");
            scanner.close();
            System.exit(0);
        }else{
            System.out.println("Opção inválida. Tente novamente.");
        }   
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
        cancelar = scanner.nextInt();
        pessoa.setProtocolo(cancelar);
        
        
        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = con.prepareStatement("SELECT protocolo FROM agendamento_consultas WHERE protocolo = ? ");
            stmt.setInt(1, cancelar);
            rs = stmt.executeQuery();

            while (rs.next()) {
                pessoa.setProtocolo(rs.getInt("protocolo"));
            }
            register.deletar(pessoa);

        } catch (SQLException ex) {
            Logger.getLogger(ClinicaMedica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }
}
