package Challenge17;

import Util.Point3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CubeReactor {
    private HashMap<Point3,Boolean> cubes;

    public CubeReactor() {
        this.cubes = new HashMap<>();
    }

    public void addCube(long x,long y,long z, boolean state){
        cubes.put(new Point3(x,y,z),state);
    }

    public long activeCount(){
        long active = 0;
        for(boolean b : cubes.values()){
            if(b){
                active++;
            }
        }
        return active;
    }

    @Override
    public String toString() {
        return "CubeReactor{" +
                "cubes=" + cubes +
                '}';
    }

    public void tick() {
        HashMap<Point3,Boolean> newMap = new HashMap<>();
        HashSet<Point3> toCheck = new HashSet<>();
        for(Point3 p : cubes.keySet()) {
            toCheck.add(p);
            toCheck.addAll(p.getNeighbours());
        }
        for(Point3 p : toCheck){
            boolean active = cubes.getOrDefault(p,false);
            boolean newState = active;
            ArrayList<Point3> neighbours = p.getNeighbours();
            long activeNeighbours = 0;
            for(Point3 n : neighbours){
                if(cubes.getOrDefault(n,false)){
                    activeNeighbours++;
                }
            }
            if(active){
                if(activeNeighbours >= 2 && activeNeighbours <= 3){
                    newState = true;
                }else{
                    newState = false;
                }
            }else{
                if(activeNeighbours == 3){
                    newState = true;
                }
            }
            if(newState){
                newMap.put(p,newState);
            }else{
                if(newMap.containsKey(p)){
                    newMap.remove(p);
                }
            }
        }
        cubes = newMap;
    }
}
