
package giocatoreAutomatico.player;

import giocatoreAutomatico.*;
import java.util.Random;

/**
 *
 * @author Luigi Fiorelli
 * @author Marco Loriga
 * @author Dario Puligheddu
 */
public class MyGiocatoreAutomatico implements GiocatoreAutomatico{
    private Random r = new Random();
    @Override
    public int prossimaMossa(Griglia g) {
        return r.nextInt(4);
    }
    
}
