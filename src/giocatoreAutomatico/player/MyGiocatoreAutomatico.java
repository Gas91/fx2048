
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
        
        //this.g=griglia;
        
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                loc = new Location(j,i); //x,y
                /*if (this.mosse == 0) {
                    preG.put(loc,griglia.get(loc));
                } else {
                    preG.put(loc,g.get(loc));
                }*/
                g.put(loc, griglia.get(loc));
                //if (preG.get(loc) != g.get(loc)) this.mossaEseguita=1;     //SCOMMENTARE PER TEST MODE
                //System.out.print(" " +g.get(loc));
                //System.out.print( "|" + preG.get(loc)+ " ");
            }
        }      
        //System.out.println("scelta if");
        System.out.println("IS possible si 0: "+this.isPossibile(0));
        if (this.isPossibile(0)==1) return 0;
        //System.out.println("primo if fallito");
        if (this.isPossibile(1)==1) return 1;
        //System.out.println("secondo if fallito");
        if (this.isPossibile(2)==1) return 2;
        //System.out.println("terzo if fallito");
        if (this.isPossibile(3)==1) return 3;
        //System.out.println("quarto if fallito");
        
        
        
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
        
        
        
          /*                                                                  //SCOMMENTARE PER TEST MODE
        if (this.mossaEseguita==1){
            System.out.println(mossaEseguita);
            this.mossaEseguita=0;
            this.mosse++;
            return r.nextInt(2);
        }
        System.out.println(mossaEseguita);
        this.mosse++;
        return 2;
        */
        //return r.nextInt(4);
        
      return 3;  
    }
    
    /**
     * Questo metodo definisce se è possibile o meno fare una determinata mossa con la griglia
     * @param move determina se la mossa richiesta è possibile 0=SU 1=DX 2=SX 3=GIU
     * @return restituisce 1 se è possibile, 0 se non è possibile
     */
    
    private int isPossibile(int move){
        
        int i,j,x,y;
        Location loc;
        
         
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                loc = new Location(j,i); //x,y
                System.out.print(" " +g.get(loc));
            }
            System.out.println("");
        }
              
        switch (move) {
            case 0:  // su corretto
                for(y=0;y<3;y++){
                    for(x=0;x<4;x++){
                        if(g.get(new Location(x,y)) == g.get(new Location(x,y+1)) && g.get(new Location(x,y)) != -1){
                            System.out.println("looooooool 1 "+g.get(new Location(x,y))+g.get(new Location(x,y+1)) );
                            return 1;
                        }
                        if( g.get(new Location(x,y)) == -1 &&  g.get(new Location(x,y+1))  != -1 ){
                            System.out.println("looooooool 2");
                            return 1;
                        }
                    }
                }
                return 0;

            case 1:  //destra bug
                for(y=0;y<4;y++){
                     for(x=0;x<3;x++){
                         
                         if(g.get(new Location(x,y)) == g.get(new Location(x+1,y)) && g.get(new Location(x,y)) != -1){
                             System.out.print("aaa1");
                             return 1;
                         }
                         System.out.println("o");
                         if( g.get(new Location(x,y)) == -1 &&  g.get(new Location(x+1,y))  != -1 ){
                             System.out.print("aaa1");
                             return 1;
                         }
                         System.out.println("b");
                     }
                 }
            return 0;
             case 2:  //sinistra bug


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



             break;
            case 3: // giu corretto
                for(y=0;y<3;y++){
                    for(x=3;x<=0;x--){
                        if(g.get(new Location(x,y)) == g.get(new Location(x,y-1)) && g.get(new Location(x,y)) != -1){
                            System.out.println("looooooool 1 "+g.get(new Location(x,y))+g.get(new Location(x,y-1)) );
                            return 1;
                        }
                        if( g.get(new Location(x,y)) == -1 &&  g.get(new Location(x,y-1))  != -1 ){
                            System.out.println("looooooool 2");
                            return 1;
                        }
                    }
                }
                return 0;
        }       
        return 0;
    }
}
