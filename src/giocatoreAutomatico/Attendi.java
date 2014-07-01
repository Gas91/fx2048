/*Questa classe permette di attendere n ms*/

package giocatoreAutomatico;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luigi Fiorelli
 * Questa classe permette di far attendere il thread che la richiama un tot di tempo
 */
public class Attendi {

    /**
     * @author Luigi Fiorelli
     * Questo metodo permette di far attendere n <b>millisecondi</b> il thread che lo richiama
     * @param n Attendi n <b>millisecondi</b>
     */
    public static void ms(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            Logger.getLogger(Attendi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @author Luigi Fiorelli
     * Questo metodo permette di far attendere n <b>secondi</b> il thread che lo richiama
     * @param n Attendi n <b>secondi</b>
     */
    public static void s(int n){
        ms(n*1000);
    }
}