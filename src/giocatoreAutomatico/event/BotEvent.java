package giocatoreAutomatico.event;

import java.util.EventObject;

/**
 * @author Luigi Fiorelli
 */
public class BotEvent extends EventObject{
    private int r;
    public BotEvent(Object source, int n){
        super(source);
        r=n;
    }
    public int getResult(){
        return r;
    }
}

