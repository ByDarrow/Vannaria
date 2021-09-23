package cat.copernic.m03.uf4.p01.heroesofvannaria;

import java.io.IOException;
import java.util.Scanner;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

/**
 *
 * @author ByDarrow
 */
public class Utils {


    public static int llegeixEnterRang(String missatge, int min,int max) {
        
        Scanner in = new Scanner(System.in);
        boolean correcte = false;
        
        int resultat = -1;
        while(!correcte) {
            System.out.printf(missatge + " [%d-%d]: ",min,max);
            String entradaUsuari = in.nextLine();
            
            try {
                resultat = Integer.parseInt(entradaUsuari);
                if(resultat<min || resultat>max) {
                    System.out.println("L'entrada no és vàlida!");
                    resultat = -1;
                } else
                    correcte = true;
            } catch (NumberFormatException e) {
                System.out.println("L'entrada no és vàlida!");
                resultat = -1;
            }
        }
        return resultat;
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                //Runtime.getRuntime().exec("clear");
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error en netejar pantalla: " + ex.getMessage());
        }
    }
    
    /**
     * Method that waits the user to press any keyqualsevol tecla.
     * No need to press ENTER to receive the key pressed.
     */
    public static void pauseUntilKey() {
        //try {
        // raw mode means we get keypresses rather than line buffered input
        try (Terminal terminal = TerminalBuilder.builder()
                .jna(true)
                .system(true)
                .build()) {
            // raw mode means we get keypresses rather than
            //line buffered input
            terminal.enterRawMode();
            try (NonBlockingReader reader = terminal.reader()) {
                reader.read();
            }
        } catch (IOException ex) {
            System.out.println("Error d'entrada/sortida: " + ex.getMessage());
        }
    }
}

