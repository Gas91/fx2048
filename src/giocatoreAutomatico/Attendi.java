/*Questa classe permette di attendere n ms*/

package giocatoreAutomatico;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luigi Fiorelli
 */
public class Attendi {

    /*
     * Attendi n millisecondi
    */
    public static void ms(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            Logger.getLogger(Attendi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
     * Attendi n secondi
    */
    public static void s(int n){
        ms(n*1000);
    }
}
