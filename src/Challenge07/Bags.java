package Challenge07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Bags {
    private HashMap<String,Long> bags;
    private ArrayList<String> bagList;

    public Bags() {
        this.bags = new HashMap<String, Long>();
        this.bagList = new ArrayList<String>();
    }

    private String bagRelationshipKey(String container,String item){
        return container + "|" + item;
    }

    public void addBag(String b){
        this.bagList.add(b);
    }

    public void addRelationship(String container,String item,Long count){
        bags.put(bagRelationshipKey(container,item),count);
    }

    public HashSet<String> contains(String bag){
        HashSet<String> ret = new HashSet<>();
        for(String b: bagList){
            if(bags.containsKey(bagRelationshipKey(bag,b))){
                ret.add(b);
            }
        }
        return ret;
    }

    public HashSet<String> containedBy(String bag){
        HashSet<String> ret = new HashSet<>();
        for(String b: bagList){
            if(bags.containsKey(bagRelationshipKey(b,bag))){
                ret.add(b);
            }
        }
        return ret;
    }

    public Long getContainedBagCount(String bag,String subbag){
        return bags.getOrDefault(bagRelationshipKey(bag,subbag),0L);
    }

    public HashMap<String, Long> getBags() {
        return bags;
    }

    public ArrayList<String> getAllBags(){
        return bagList;
    }
}
