package Challenge23;

import java.util.HashMap;

public class CircularList {
    private Cup current;
    private HashMap<Long,Cup> lookup;

    public CircularList() {
        lookup = new HashMap<>();
    }

    public void addCup(Cup c){
        Cup tail = null;
        if(current == null){
            tail = c;
            current = c;
        }else{
            tail = current.getNext();
        }
        current.setNext(c);
        c.setNext(tail);
        current = c;
        lookup.put(c.getValue(),c);
    }

    public Cup popNext(){
        Cup c = current.getNext();
        current.setNext(c.getNext());
        lookup.remove(c.getValue());
        return c;
    }

    public void setCurrent(long val){
        current = lookup.get(val);
    }

    public Cup getCurrent() {
        return current;
    }

    public boolean contains(long val){
        return lookup.containsKey(val);
    }

    @Override
    public String toString() {
        long val = current.getValue();
        Cup c = current.getNext();
        String ret = "CircularList{("+val+"),";
        while(c.getValue() != val){
            ret += c.getValue() + ",";
            c = c.getNext();
        }
        return ret +"}";
    }
}
