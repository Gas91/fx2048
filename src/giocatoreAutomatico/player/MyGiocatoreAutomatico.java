
package giocatoreAutomatico.player;

import game2048.Location;
import game2048.Tile;
import giocatoreAutomatico.*;
import java.util.Random;

/**
 * Questa classe riceve come input una griglia di tipo MyGriglia e genera la mossa successiva da fare
 * @author Luigi Fiorelli
 * @author Marco Loriga
 * @author Dario Puligheddu
 */
public class MyGiocatoreAutomatico implements GiocatoreAutomatico{
    
    private final Random r = new Random(); 
    private final Griglia g = new MyGriglia();
    int pre=0;
    @Override
    /**
     * Genera la mossa successiva in base alla griglia
     * @return Restituisce un intero che indica la mossa successiva. 0=SU 1=DX 2=SX 3=GIU
     */
    public int prossimaMossa(Griglia griglia) {
        int i,j;
        Location loc;
        
        g.clear();
        
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                loc = new Location(j,i); 
                g.put(loc, griglia.get(loc));
            }
        }
        
        if ( pre==2 && this.isPossibile(1)==1){
                pre = 1;
                return 1;
            }    
        
            
        if (this.isPossibile(0)==1){
            pre = 0;
            return 0;
        }
        if (this.isPossibile(1)==1){
            pre = 1;
            return 1;
        }
        if (this.isPossibile(2)==1){
            pre = 2;
            return 2;
        }
        if (this.isPossibile(3)==1){
            pre = 3;
            return 3;
        }
        
      return 3;  
    }
    
    /**
     * Questo metodo definisce se è possibile o meno fare una determinata mossa con la griglia
     * @param move determina se la mossa richiesta è possibile 0=SU 1=DX 2=SX 3=GIU
     * @return restituisce 1 se è possibile, 0 se non è possibile
     */
    private int isPossibile(int move){
        
        int i,j,x,y;
        int a,b;
        Location loc;
        
         
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                loc = new Location(j,i); //x,y
                //System.out.print(" " +g.get(loc));
            }
            //System.out.println("");
        }
              
        switch (move) {
            case 0:  // su corretto
                System.out.print("su ");
                for(y=0;y<3;y++){
                    for(x=0;x<4;x++){
                        if(g.get(new Location(x,y)) == g.get(new Location(x,y+1)) && g.get(new Location(x,y)) != -1){
                            System.out.print("1 - ");
                            return 1;
                        }
                        if( g.get(new Location(x,y)) == -1 &&  g.get(new Location(x,y+1))  != -1 ){
                            System.out.print("1 - ");
                            return 1;
                        }
                    }
                }
                System.out.print("0 - ");
                return 0;

            case 1:{  //destra bug
                System.out.print("dx ");
                for(y=0;y<4;y++){                               
                    for(x=0;x<3;x++){// non scorro l'ultima colonna perche devo controllare verso destra
                        a= g.get(new Location(x,y));
                        b= g.get(new Location(x+1,y));
                        if (a != -1){ //se la cella non è vuota 
                            if (b == -1){ // e quella a destra è vuota
                                System.out.print("1 - ");
                                return 1; 
                            }
                            
                        if(a == b){
                            System.out.print("1 - ");
                            return 1; 
                        }
 }
                    }
                }
                System.out.print("0 - ");
                return 0;  }
            
             case 2:  //sinistra bugf
                System.out.print("sx ");
                //System.out.println("sx1");
                for(i=0;i<4;i++){
                    for(j=1;j<4;j++){
                        a=g.get(new Location(i,j));
                        b=g.get(new Location(i,j-1));
                        
                        if (a != -1){ //se la cella non è vuota 
                                //System.out.println("sx2");
                            if (b == -1){ // e quella a sinistra è vuota
                                System.out.print("1 - ");
                                return 1; 
                            
                            } else { 
                                if(a == b){ 
                                    System.out.print("1 - ");
                                    return 1; 
                                } 
                            }
                               //System.out.println("sx3");
                        }
                    }
                }
                System.out.print("0 - ");
                return 0;  
            case 3: // giu corretto
                System.out.print("gi ");
                for(y=0;y<3;y++){
                    for(x=3;x<=0;x--){
                        if(g.get(new Location(x,y)) == g.get(new Location(x,y-1)) && g.get(new Location(x,y)) != -1){
                            System.out.print("1 - ");
                            return 1;
                        }
                        if( g.get(new Location(x,y)) == -1 &&  g.get(new Location(x,y-1))  != -1 ){
                            System.out.print("1 - ");
                            return 1;
                        }
                    }
                }
                System.out.print("0 - ");
                return 0;
        }       
        return 0;
    }
}
