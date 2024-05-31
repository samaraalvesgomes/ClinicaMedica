
package clinicamedica;

/* import clinicamedica.Perfil.*;
import connection.*; 
import query.*;*/
import java.util.Scanner;

import Perfil.GerenciarPerfil;
    public class ClinicaMedica {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String opcao;

            //instanciando as classes
            GerenciarPerfil perfil = new GerenciarPerfil();


            System.out.println("Bem-vindo(a) a Mais Saúde!!!");
            System.out.println("MENU");
            System.out.println("A - Criar usuário");
            System.out.println("B - login");
            System.out.println("C - sair");
            opcao = scanner.nextLine();


            switch(opcao) { // selecionar a opção do menu
                case "A": 
                
                perfil.cadastrousuario();
                System.out.println("Cadastrar novo usuário? S/N");
                    
                case "B": 
                    //execute as instruções do bloco y
                    
                default:
                        //execute as instruções do default 

            }

            scanner.close();
        }
        
    }
