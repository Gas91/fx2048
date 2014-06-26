/*Questa classe permette di attendere n ms*/

package giocatoreAutomatico;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luigi Fiorelli
 */
public class Attendi {

    public static void ms(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            Logger.getLogger(Attendi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
