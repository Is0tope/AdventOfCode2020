package Challenge17;

import Util.Point4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HyperCubeReactor {
    private HashMap<Point4,Boolean> cubes;

    public HyperCubeReactor() {
        this.cubes = new HashMap<>();
    }

    public void addCube(long x,long y,long z,long w, boolean state){
        cubes.put(new Point4(x,y,z,w),state);
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
        return "HyperCubeReactor{" +
                "cubes=" + cubes +
                '}';
    }

    public void tick() {
        HashMap<Point4,Boolean> newMap = new HashMap<>();
        HashSet<Point4> toCheck = new HashSet<>();
        for(Point4 p : cubes.keySet()) {
            toCheck.add(p);
            toCheck.addAll(p.getNeighbours());
        }
        for(Point4 p : toCheck){
            boolean active = cubes.getOrDefault(p,false);
            boolean newState = active;
            ArrayList<Point4> neighbours = p.getNeighbours();
            long activeNeighbours = 0;
            for(Point4 n : neighbours){
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
