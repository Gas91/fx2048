package giocatoreAutomatico;

import giocatoreAutomatico.event.*;
import javax.swing.event.EventListenerList;

/** 
 * Questa classe è un thread che ogni tot ms fa partire l'EventListener che gli viene passato
 * @author Luigi Fiorelli
 */
public class AutoGame implements Runnable{
    
    /** Determina quanti <b>millisecondi</b> passano tra una mossa e l'altra del bot */
    private final int TIME_DELAY_BOT = 250;
    /** Lista degli eventi da far partire ogni <b>TIME_DELAY_BOT</b> ms */
    private final EventListenerList listeners = new EventListenerList();;
    /** Questa variabile determina lo stato di esecuzione del thread:
     * <UL><LI>Valore <b>0</b>: Il thread è disattivato e non è mai stato attivato</LI>
     * <LI>Valore <b>1</b>: Il thread è attivo</LI>
     * <LI>Valore <b>2</b>: Il thread è disattivato</LI></UL>
     */
    private int stato = 0;
    
    @Override
    /** 
     * Metodo del thread che viene eseguito all'infinito durante la vita del thread stesso.
     * Ad ogni ciclo controlla lo <b>stato</b>, e se questo è pari a <B>1</B> (thread abilitato) fa partire l'evento; 
     * dopo attende <B>TIME_DELAY_BOT</B> ms
     */
    public void run() {
        while(true){
            if(stato==1){
                fireNewDirection(1);
                Attendi.ms(TIME_DELAY_BOT);
            } else {
                Attendi.ms(10);
            } //end if
        }//end while
    }//end run
    
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
     * Fa partire gli eventi che gli sono stati passati
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
