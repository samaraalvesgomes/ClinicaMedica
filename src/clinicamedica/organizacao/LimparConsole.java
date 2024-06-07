package clinicamedica.organizacao;

import java.io.IOException;

public class LimparConsole {
    
    // Método para limpar o console no Windows
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
