package servicos;

import java.util.Scanner;
public class Consulta {
    Scanner scanner=new Scanner(System.in);
    int especialidade;

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
}
