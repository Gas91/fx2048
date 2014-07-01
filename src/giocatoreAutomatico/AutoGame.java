
package giocatoreAutomatico;

import giocatoreAutomatico.event.*;
import javax.swing.event.EventListenerList;
/**
 * @author Luigi Fiorelli
 */
public class AutoGame implements Runnable{
    
    /** Determina quanti <b>millisecondi</b> passano tra una mossa e l'altra del bot */
    private final int TIME_DELAY_BOT = 500;     
    private final EventListenerList listeners;
    /** Questa variabile determina lo stato di esecuzione del thread:
     * <UL>
     * <LI><b>Valore 0</b> Il thread è disattivato e non è mai stato attivato</LI>
     * <LI><b>Valore 1</b> Il thread è attivo</LI>
     * <LI><b>Valore 2</b> Il thread è disattivato</LI>
     * </UL>
     */
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
    
    /**
     * Disabilita il thread
     */
    public void off(){
        stato = 2;
    }
    
    /**
     * Abilita il thread
     */
    public void on(){
        stato = 1;
    }
    
    /**
     * Restituisce lo stato attuale del thread
     * @return Restituisce lo stato attuale del thread
     */
    public int getStato(){
        return stato;
    }
    
    /**
     * Richiama l'evento
     */
    private void fireNewDirection(int move){
        BotEvent event = new BotEvent(this, move);
        Object[] listenersArray = listeners.getListenerList();
        for(int i = listenersArray.length - 2; i >= 0; i -= 2){
            if(listenersArray[i] == BotEventListener.class){
                ((BotEventListener)listenersArray[i+1]).botNewDirectiondEvent(event);
            }
        }
    }
    
    /**
     * Aggiunge un BotEvetListener
     * @param l Il BotEventListener da aggiungere
     */
    public void addBotEventListener(BotEventListener l){
        listeners.add(BotEventListener.class, l);
    }
    
    /**
     * Rimuove un BotEventListener
     * @param l Il BotEventListener da rimuovere
     */
    public void removeBotEventListener(BotEventListener l){
        listeners.remove(BotEventListener.class, l);
    }
}
