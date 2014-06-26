

package giocatoreAutomatico.event;

import java.util.EventListener;

/**
 *
 * @author Luigi
 */
public interface BotEventListener extends EventListener{

    public void botNewDirectiondEvent(BotEvent e);

}