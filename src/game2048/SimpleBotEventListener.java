
package game2048;


import giocatoreAutomatico.Attendi;
import giocatoreAutomatico.GiocatoreAutomatico;
import giocatoreAutomatico.event.BotEvent;
import giocatoreAutomatico.event.BotEventListener;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Luigi Fiorelli
 */
public class SimpleBotEventListener implements BotEventListener{
    private GameManager gM;
    private GiocatoreAutomatico gA;
    public SimpleBotEventListener(GameManager gM){
        this.gM = gM;
        try {
            gA = GiocatoreAutomatico.getGiocatoreAutomatico();
        } catch (Exception ex) {
            Logger.getLogger(SimpleBotEventListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void botNewDirectiondEvent(BotEvent e) {
        int m = gA.prossimaMossa(gM.getGriglia()); //Scelgo la mossa successiva
        System.out.println("x"+m);
        try {
            Robot robot = new Robot();
            switch(m){
                case 0:
                    //gM.move(Direction.UP);
                    robot.keyPress(KeyEvent.VK_UP);
                    Attendi.ms(50);
                    robot.keyRelease(KeyEvent.VK_UP);
                break;
                case 1:
                    //gM.move(Direction.RIGHT);
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    Attendi.ms(50);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                break;
                case 2:
                    //gM.move(Direction.LEFT);
                    robot.keyPress(KeyEvent.VK_LEFT);
                    Attendi.ms(50);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                break;
                default:
                    //gM.move(Direction.valueFor(KeyCode.DOWN));
                    robot.keyPress(KeyEvent.VK_DOWN);
                    Attendi.ms(50);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                break;
            }
        } catch (AWTException e2) {
            e2.printStackTrace();
        }
    }
}
