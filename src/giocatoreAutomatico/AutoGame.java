/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package giocatoreAutomatico;

import game2048.GameManager;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;
/**
 *
 * @author Luigi Fiorelli
 */
public class AutoGame implements Runnable{
    private GameManager gM;
    private static GiocatoreAutomatico gA;
    private static Griglia gr;
    
    public AutoGame(GameManager gM){
        this.gM=gM;
            try {
                gA = GiocatoreAutomatico.getGiocatoreAutomatico();
            } catch (Exception ex) {
                Logger.getLogger(AutoGame.class.getName()).log(Level.SEVERE, null, ex);
               }
            gr = new MyGriglia();
        }
    
    @Override
    public void run() {
        while(true){
            int m = gA.prossimaMossa(gr);
            System.out.print(m);
            //gM.move(m);
            //gM.move(1);
           
            Attendi.ms(500);
        }
    }
}
