package clinicamedica.organizacao;
import java.io.IOException;

public class LimparConsole {
    
    /**
     * Este método faz a limpeza do terminal quando executado
     */
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
  
}
