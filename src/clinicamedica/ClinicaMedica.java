package clinicamedica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Perfil.GerenciarPerfil;
import Perfil.GetSet;
import connection.ConnectionFactory;

public class ClinicaMedica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciarPerfil perfil = new GerenciarPerfil();
        String logado = null ;
        String opcao;

        System.out.println("Bem-vindo(a) a Mais Saúde!!!");

        while (true) {
            // Se o usuário não estiver logado, mostrar o menu de login
            if (logado == null) {
                // Exibir menu de login
                System.out.println("MENU DE LOGIN");
                System.out.println("A - Criar usuário");
                System.out.println("B - Login");
                System.out.println("C - Sair");
                opcao = scanner.nextLine();

                switch (opcao.toUpperCase()) {
                    case "A":
                        perfil.cadastroUsuario();
                        break; // Voltar para o menu de login após criar usuário
  /*conferencia */  case "B":
                        logado = perfil.loginUsuario();

                        if (logado == "verdadeiro") {
                            System.out.println("Bem-vindo(a)" + logado); //retornar o nome da pessoa logada
                            break; 
                        } else {
                            System.out.println("Falha no login. Tente novamente.");
                        }
                        
   /*OK*/           case "C":
                        System.out.println("Programa finalizado.");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else { // refazer para 2 telas

                
                // Condição de teste

                // funcionário

                // Menu do site para funcionário após o login
                System.out.println("A - Ver agenda");
                System.out.println("B - Agendar um retorno");
                System.out.println("C - Sair");
                opcao = scanner.nextLine();

                while(opcao != "A" || opcao != "B" || opcao != "C"){
                    switch (opcao.toUpperCase()) {
                        case "A":
                            // Lógica para agendar consulta
                            System.out.println("Funcionalidade de agendar consulta em desenvolvimento...");
                            Connection con = ConnectionFactory.getConnection();
                            System.out.println("conexão criada com sucesso");

                            PreparedStatement stmt = null;
                            ResultSet rs = null;
                            List <GetSet> usuarios = new ArrayList<>();

                            try {
                                stmt = con.prepareStatement(" SELECT * FROM cadastro_usuario");
                                rs = stmt.executeQuery();
                                while (rs.next()){
                                    GetSet usuario = new GetSet();
                                    usuario.setUsuario(rs.getString("usuario"));
                                    usuarios.add(usuario);
                                }

                            } catch (ClassNotFoundException | SQLException ex) {
                                throw new RuntimeException("Erro na conexão: ", ex);
                            }finally {
                                //con.close();
                            } return GetSet;
                            break;

                        case "B":
                            // Lógica para ver histórico de consultas
                            System.out.println("Funcionalidade de ver histórico de consultas em desenvolvimento...");
                            break;

                        case "C":
                            System.out.println("Programa finalizado.");
                            scanner.close();
                            System.exit(0);
                            break;

                        default:
                            System.out.println("Opção inválida. Tente novamente!");
                            break;
                        } 
                            System.out.println("A - Ver agenda");
                            System.out.println("B - Agendar um retorno");
                            System.out.println("C - Sair");
                            opcao = scanner.nextLine();          
                    }
                }
            }
        }
    }
