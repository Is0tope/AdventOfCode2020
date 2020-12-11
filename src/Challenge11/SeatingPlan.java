package Challenge11;

import java.util.ArrayList;
import java.util.HashMap;

public class SeatingPlan {
    private HashMap<Point,Boolean> seats;
    private int width;
    private int height;

    public SeatingPlan() {
        seats = new HashMap<>();
    }


    private int getOccupiedNeighbours(Point p){
        int occupied = 0;
        int x = p.getX();
        int y = p.getY();
        ArrayList<Point> positions = new ArrayList<>();
        positions.add(new Point(x-1,y-1));
        positions.add(new Point(x+0,y-1));
        positions.add(new Point(x+1,y-1));
        positions.add(new Point(x+1,y+0));
        positions.add(new Point(x-1,y+0));
        positions.add(new Point(x-1,y+1));
        positions.add(new Point(x+0,y+1));
        positions.add(new Point(x+1,y+1));

        for(Point o : positions){
            if(seats.getOrDefault(o,false)){
                occupied++;
            }
        }
        return occupied;
    }

    private int getOccupiedVectors(Point p){
        int occupied = 0;

        ArrayList<Point> vectors = new ArrayList<>();
        vectors.add(new Point(-1,-1));
        vectors.add(new Point(+0,-1));
        vectors.add(new Point(+1,-1));
        vectors.add(new Point(+1,+0));
        vectors.add(new Point(-1,+0));
        vectors.add(new Point(-1,+1));
        vectors.add(new Point(+0,+1));
        vectors.add(new Point(+1,+1));

        for(Point v : vectors){
            if(searchVector(p,v)){
                occupied++;
            }
        }
        return occupied;
    }

    private boolean searchVector(Point p, Point v){
        int x = p.getX();
        int y = p.getY();
        int xv = v.getX();
        int yv = v.getY();
        do {
            x += xv;
            y += yv;
            Point p2 = new Point(x,y);
            if(seats.containsKey(p2)){
                if(seats.getOrDefault(p2,false)){
                    return true;
                }
                return false;
            }
        }while((x<width && x>=0) && (y<height && y>=0));
        return false;
    }

    public void addSeat(int x,int y){
        seats.put(new Point(x,y),false);
        if(x+1>width){
            width = x+1;
        }
        if(y+1>height){
            height = y+1;
        }
    }

    public void tick(String strategy){
        HashMap<Point,Boolean> newseats = new HashMap<>();
        for(Point p : seats.keySet()){
            boolean occupied = seats.get(p);
            int neighbours = strategy.equals("A") ? getOccupiedNeighbours(p) : getOccupiedVectors(p);
            int threshold = strategy.equals("A") ? 4 : 5;
            boolean newstate = occupied;
            if(occupied){
                if(neighbours >= threshold ){
                    newstate = false;
                }
            }else{
                if(neighbours == 0){
                    newstate = true;
                }
            }
            newseats.put(p,newstate);
        }
        this.seats = newseats;
    }

    // Violates encapsulation but whatever
    public HashMap<Point, Boolean> getSeats() {
        return seats;
    }

    public boolean compareState(HashMap<Point,Boolean> map){
        for(Point p : seats.keySet()){
            // Hacky, but just assume points are always the same in both
            if(seats.get(p) != map.get(p)){
                return false;
            }
        }
        return true;
    }

    public long getOccupiedCount(){
        long occupied = 0;
        for(Point p : seats.keySet()){
            if(seats.get(p)){
                occupied++;
            }
        }
        return occupied;
    }

    @Override
    public String toString() {
        return "SeatingPlan{" +
                "seats=" + seats +
                '}';
    }

    public void drawPlan(int width,int height){
        for(int i = 0;i<height;i++){
            for(int j = 0;j<width;j++){
                Point p = new Point(j,i);
                if(seats.containsKey(p)){
                    if(seats.get(p)){
                        System.out.print('#');
                    }else{
                        System.out.print('L');
                    }
                }else{
                    System.out.print('.');
                }
            }
            System.out.print('\n');
        }
    }
}
