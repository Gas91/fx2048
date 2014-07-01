package giocatoreAutomatico;

import game2048.Location;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * Implementazione della classe Griglia.
 * Questa classe permette di creare una griglia con una Key di tipo Location e un valore di tipo Integer
 * @author Luigi Fiorelli
 */
public class MyGriglia implements Griglia{
    private final Map<Location, Integer> gameGrid;
    
    public MyGriglia(){
        gameGrid = new HashMap<>();
    }
    
    @Override
    public Integer get(Object key) {
        return gameGrid.get(key);
    }

    @Override
    public Integer put(Location key, Integer value) {
        return gameGrid.put(key, value);
    }
    
    @Override
    public int size() {
        return gameGrid.size();
    }

    @Override
    public boolean isEmpty() {
        return gameGrid.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return gameGrid.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return gameGrid.containsValue(value);
    }

    @Override
    public Integer remove(Object key) {
        return gameGrid.remove(key);
    }

    @Override
    public void putAll(Map<? extends Location, ? extends Integer> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        gameGrid.clear();
    }

    @Override
    public Set<Location> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Integer> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<Location, Integer>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}