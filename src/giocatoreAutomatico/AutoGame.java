
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
    private EventListenerList listeners;
    private int stato = 0;
    
    public AutoGame(){
        listeners = new EventListenerList();
    }
    
    @Override
    public void run() {
        while(true){
            if(stato==1){
                fireNewDirection(1);
            }
            Attendi.ms(TIME_DELAY_BOT);
        }
        
    }
    
    public void off(){
        stato = 2;
    }
       public void on(){
        stato = 1;
    }
    
    public int getStato(){
        return stato;
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
