
package game2048;


import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;
import giocatoreAutomatico.Attendi;
import giocatoreAutomatico.GiocatoreAutomatico;
import giocatoreAutomatico.event.BotEvent;
import giocatoreAutomatico.event.BotEventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

/**
 * Questa classe si preoccupa di gestire l'evento del thread e, quindi, la mossa successiva da eseguire
 * @author Luigi Fiorelli
 */
public class SimpleBotEventListener implements BotEventListener{
    private Scene scene;
    private GameManager gM;
    private GiocatoreAutomatico gA;
    private FXRobot robot;
    public SimpleBotEventListener(GameManager gM){
        this.gM = gM;
        try {
            gA = GiocatoreAutomatico.getGiocatoreAutomatico();
        } catch (Exception ex) {
            Logger.getLogger(SimpleBotEventListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setMyScene(Scene sc){
        this.scene = sc;
        robot = FXRobotFactory.createRobot(this.scene);
    }

    @Override
    public void botNewDirectiondEvent(BotEvent e) {
        int m = gA.prossimaMossa(gM.getGriglia()); //Scelgo la mossa successiva
        System.out.println("x"+m);
            
        switch(m){
            case 0:
                robot.keyPress(KeyCode.UP);
                Attendi.ms(50);
                robot.keyRelease(KeyCode.UP);
            break;
            case 1:
                robot.keyPress(KeyCode.RIGHT);
                Attendi.ms(50);
                robot.keyRelease(KeyCode.RIGHT);
            break;
            case 2:
                robot.keyPress(KeyCode.LEFT);
                Attendi.ms(50);
                robot.keyRelease(KeyCode.LEFT);
            break;
            default:
                robot.keyPress(KeyCode.DOWN);
                Attendi.ms(50);
                robot.keyRelease(KeyCode.DOWN);
            break;
        }
    }    
}

