
package game2048;

import giocatoreAutomatico.event.BotEvent;
import giocatoreAutomatico.event.BotEventListener;

/**
 *
 * @author Luigi
 */
public class SimpleBotEventListener implements BotEventListener{
    private static GameManager gM;
    public SimpleBotEventListener(GameManager gM){
        this.gM = gM;
    }

    @Override
    public void botNewDirectiondEvent(BotEvent e) {
        System.out.println("x");
        
        switch(e.getResult()){
            case 0:
                gM.move(Direction.UP);
            break;
            case 1:
                gM.move(Direction.RIGHT);
            break;
            case 2:
                gM.move(Direction.LEFT);
            break;
            default:
                gM.move(Direction.RIGHT);
            break;
        }
        
        //gM.move(e.getResult());
    }
}
