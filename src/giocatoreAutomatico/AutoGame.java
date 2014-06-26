
package giocatoreAutomatico;

import game2048.GameManager;
import giocatoreAutomatico.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;
/**
 *
 * @author Luigi Fiorelli
 */
public class AutoGame implements Runnable{
    private final int TIME_DELAY_BOT = 500; //ms tra una mossa e l'altra del bot
    private GameManager gM;
    private GiocatoreAutomatico gA;
    private Griglia gr;
    
    private EventListenerList listeners;
    
    public AutoGame(){
        listeners = new EventListenerList();
        //this.gM=gM;
        //    try {
        //        gA = GiocatoreAutomatico.getGiocatoreAutomatico();
        //    } catch (Exception ex) {
        //        Logger.getLogger(AutoGame.class.getName()).log(Level.SEVERE, null, ex);
        //    }
            gr = new MyGriglia();
    }
    
    @Override
    public void run() {
        while(true){
            int m = gA.prossimaMossa(gr);
            System.out.print(m);
           fireNewDirection(m);
                //gM.move(m);
                //gM.move(1);
                Attendi.ms(TIME_DELAY_BOT);

        }
    }
    
    private void fireNewDirection(int move){
        BotEvent event = new BotEvent(this, move);
        Object[] listenersArray = listeners.getListenerList();
        for(int i = listenersArray.length - 2; i >= 0; i -= 2){
            if(listenersArray[i] == BotEventListener.class){
                ((BotEventListener)listenersArray[i+1]).botNewDirectiondEvent(event);
            }
        }
    }
    public void addBotEventListener(BotEventListener l){
        listeners.add(BotEventListener.class, l);
    }

    public void removeBotEventListener(BotEventListener l){
        listeners.remove(BotEventListener.class, l);
    }
}
