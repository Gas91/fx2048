
package giocatoreAutomatico.player;

import game2048.Location;
import game2048.Tile;
import giocatoreAutomatico.*;
import java.util.Random;

/**
 * Questa classe riceve come input una griglia di tipo MyGriglia e genera la mossa successiva da fare
 * @author Luigi Fiorelli
 * @author Marco Loriga
 * @author Dario Puligheddu
 */
public class MyGiocatoreAutomatico implements GiocatoreAutomatico{
    
    private Random r = new Random(); 
    
    @Override
    /**
     * Genera la mossa successiva in base alla griglia
     * @return Restituisce un intero che indica la mossa successiva. 0=SU 1=DX 2=SX 3=GIU
     */
    public int prossimaMossa(Griglia g) {
        int i;
        int j;
        Location loc;
        Tile tile;
        //System.out.println("aaaa");
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                loc = new Location(j,i);
                //System.out.print(g.get(loc));
            }
            //System.out.println("");
        }
        //System.out.println("zzzz");
        return r.nextInt(4);
    }
}
