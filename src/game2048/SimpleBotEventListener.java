
package game2048;


import giocatoreAutomatico.event.BotEvent;
import giocatoreAutomatico.event.BotEventListener;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author Luigi
 */
public class SimpleBotEventListener implements BotEventListener{
    private GameManager gM;
    public SimpleBotEventListener(GameManager gM){
        this.gM = gM;
    }

    @Override
    public void botNewDirectiondEvent(BotEvent e) {
        System.out.println("x");
        
        try {
            Robot robot = new Robot();
            switch(e.getResult()){
                case 0:
                    //gM.move(Direction.UP);
                    robot.keyPress(KeyEvent.VK_UP);
                    robot.keyRelease(KeyEvent.VK_UP);
                break;
                case 1:
                    //gM.move(Direction.RIGHT);
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                break;
                case 2:
                    //gM.move(Direction.LEFT);
                    robot.keyPress(KeyEvent.VK_LEFT);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                break;
                default:
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                break;
            }
        } catch (AWTException e2) {
            e2.printStackTrace();
        }
        
        //gM.move(e.getResult());
    }
}
