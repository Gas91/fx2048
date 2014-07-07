
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
    
    private Random r = new Random(); 
    private static Griglia g = new MyGriglia();
    private static Griglia preG =  new MyGriglia(); //Griglia di appoggio                               //SCOMMENTARE PER TEST MODE
    private int mosse=0;  //conta mosse
    private int mossaEseguita=0; 
    @Override
    /**
     * Genera la mossa successiva in base alla griglia
     * @return Restituisce un intero che indica la mossa successiva. 0=SU 1=DX 2=SX 3=GIU
     */
    public int prossimaMossa(Griglia griglia) {
        
        //Ricordiamoci di fare i contrlli per evitare che la griglia sia vuota (ad esempio al primo turno)
        
        int i;
        int j;
        Location loc;
        Tile tile;
        
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                loc = new Location(j,i); //x,y
                if (this.mosse == 0) {
                    preG.put(loc,griglia.get(loc));
                } else {
                    preG.put(loc,g.get(loc));
                }
                g.put(loc, griglia.get(loc));
                if (preG.get(loc) != g.get(loc)) this.mossaEseguita=1;     //SCOMMENTARE PER TEST MODE
                /*System.out.print(" " +g.get(loc));
                System.out.print( "|" + preG.get(loc)+ " ");*/
            }
        }        
        /*
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                loc = new Location(j,i); //x,y
                if (preG.get(loc) != g.get(loc)) this.mossaEseguita=1;     //SCOMMENTARE PER TEST MODE
                System.out.print(" " +g.get(loc));
                System.out.print( "|" + preG.get(loc)+ " ");                         //SCOMMENTARE PER TEST MODE
                
            }
            System.out.println("");
        }
        System.out.println("zzzz");*/
        
        
        
                                                                            //SCOMMENTARE PER TEST MODE
        if (this.mossaEseguita==1){
            System.out.println(mossaEseguita);
            this.mossaEseguita=0;
            this.mosse++;
            return r.nextInt(2);
        }
        System.out.println(mossaEseguita);
        this.mosse++;
        return 2;
        
        //return r.nextInt(4);
        
        
    }
    
    /**
     * Questo metodo definisce se è possibile o meno fare una determinata mossa con la griglia
     * @param move determina se la mossa richiesta è possibile 0=SU 1=DX 2=SX 3=GIU
     * @return restituisce 1 se è possibile, 0 se non è possibile
     */
    
    private int isPossibile(int move){
        
        int i;
        int j;
        Location loc;
        
              
     switch (move) {
         case 0:{ // su
         

                            for(i=1;i<4;i++){// non scorro la prima riga perche devo controllare verso su
                                   for(j=0;j<4;j++){
                                       if (g.get(new Location(i,j))!=-1){ //se la cella non è vuota 
                                       
                                           if (g.get(new Location(i-1,j))==-1) // e quella sopra è vuota
                                           return 1; 
                                           }
                                           else if(g.get(new Location(i-1,j)) == (g.get(new Location(i,j))))
                                               // esiste una cella qua sopra con lo stesso numero che quindi posso sommare
                                           return 1;
                                        
                                    }
                             }
                    
         } 
         break;
         case 1: { //destra
      
             
             
                for(i=0;i<4;i++){
                                   for(j=0;j<3;j++){// non scorro l'ultima colonna perche devo controllare verso destra
                                       if (g.get(new Location(i,j))!=-1){ //se la cella non è vuota 
                                       
                                           if (g.get(new Location(i,j+1))==-1) // e quella a destra è vuota
                                           return 1; 
                                           }
                                           else if(g.get(new Location(i,j+1)) == (g.get(new Location(i,j))))
                                               // esiste una cella a dx con lo stesso numero che quindi posso sommare
                                           return 1;
                                        
                                    }
                             }
             
         }
         break;
         case 2: { //sinistra
            
              
                for(i=0;i<4;i++){
                                   for(j=1;j<4;j++){// non scorro l'ultima colonna perche devo controllare verso sinistra
                                       if (g.get(new Location(i,j))!=-1){ //se la cella non è vuota 
                                       
                                           if (g.get(new Location(i,j-1))==-1) // e quella a sinistra è vuota
                                           return 1; 
                                           }
                                           else if(g.get(new Location(i,j-1)) == (g.get(new Location(i,j))))
                                               // esiste una cella a sx con lo stesso numero che quindi posso sommare
                                           return 1;
                                        
                                    }
                             }
             
             
         }
         break;
             case 3: { // giu
                 
                 for(i=0;i<4;i++){
                                   for(j=0;j<3;j++){// non scorro l'ultima colonna perche devo controllare verso il basso
                                       if (g.get(new Location(i,j))!=-1){ //se la cella non è vuota 
                                       
                                           if (g.get(new Location(i+1,j))==-1) // e quella in basso è vuota
                                           return 1; 
                                           }
                                           else if(g.get(new Location(i+1,j)) == (g.get(new Location(i,j))))
                                               // esiste una cella in basso con lo stesso numero che quindi posso sommare
                                           return 1;
                                        
                                    }
                             }
                 
                 
                 
                 
            
             }
         break;
                  }       
              
        
        
        
        return 0;
    }
}
