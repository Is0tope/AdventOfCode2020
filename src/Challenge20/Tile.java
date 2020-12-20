package Challenge20;

import Util.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Tile {
    private long id;
    private int size;
    private HashMap<Point,Boolean> grid;
    private HashSet<Edge> edges;

    public Tile(long id, int size, HashMap<Point, Boolean> grid) {
        this.id = id;
        this.size = size;
        this.grid = grid;

        this.edges = new HashSet<Edge>();
        for(int i = 0;i < 4;i++){
            edges.add(getBottomEdge());
            edges.add(getTopEdge());
            edges.add(getLeftEdge());
            edges.add(getRightEdge());
            flip();
            edges.add(getBottomEdge());
            edges.add(getTopEdge());
            edges.add(getLeftEdge());
            edges.add(getRightEdge());
            flip();
            rotate90();
        }
    }

    public Edge getLeftEdge(){
        // Top to bottom
        ArrayList<Boolean> arr = new ArrayList<>();
        for(int i = 0;i < size;i++){
            arr.add(grid.getOrDefault(new Point(0,i),false));
        }
        return new Edge(arr);
    }

    public Edge getRightEdge(){
        // Top to bottom
        ArrayList<Boolean> arr = new ArrayList<>();
        for(int i = 0;i < size;i++){
            arr.add(grid.getOrDefault(new Point(size-1,i),false));
        }
        return new Edge(arr);
    }

    public Edge getTopEdge(){
        // Left to right
        ArrayList<Boolean> arr = new ArrayList<>();
        for(int i = 0;i < size;i++){
            arr.add(grid.getOrDefault(new Point(i,0),false));
        }
        return new Edge(arr);
    }

    public Edge getBottomEdge(){
        // Left to right
        ArrayList<Boolean> arr = new ArrayList<>();
        for(int i = 0;i < size;i++){
            arr.add(grid.getOrDefault(new Point(i,size-1),false));
        }
        return new Edge(arr);
    }

    public void rotate90(){
        HashMap<Point,Boolean> newgrid = new HashMap<>();
        for(Point p : grid.keySet()){
            newgrid.put(new Point((size-1)-p.getY(),p.getX()),grid.get(p));
        }
        grid = newgrid;
    }

    public boolean containsEdge(Edge e){
        return edges.contains(e);
    }

    public void flip(){
        HashMap<Point,Boolean> newgrid = new HashMap<>();
        for(Point p : grid.keySet()){
            newgrid.put(new Point(p.getX(),(size-1)-p.getY()),grid.get(p));
        }
        grid = newgrid;
    }

    public void print(){
//        System.out.println("Tile " + id + ":");
        for(int y = 0;y < size;y++){
            for(int x = 0;x < size;x++){
                if(grid.getOrDefault(new Point(x,y),false)){
                    System.out.print('#');
                }else{
                    System.out.print('.');
                }
            }
            System.out.print('\n');
        }
    }

    public boolean tryAttachRight(Tile t){
        Edge current = getRightEdge();
        for(int i = 0;i < 4;i++){
            if(current.equals(t.getLeftEdge())){
                return true;
            }
            t.flip();
            if(current.equals(t.getLeftEdge())){
                return true;
            }
            t.flip();
            t.rotate90();
        }
        return false;
    }

    public boolean tryAttachLeft(Tile t){
        Edge current = getLeftEdge();
        for(int i = 0;i < 4;i++){
            if(current.equals(t.getRightEdge())){
                return true;
            }
            t.flip();
            if(current.equals(t.getRightEdge())){
                return true;
            }
            t.flip();
            t.rotate90();
        }
        return false;
    }

    public boolean tryAttachBottom(Tile t){
        Edge current = getBottomEdge();
        for(int i = 0;i < 4;i++){
            if(current.equals(t.getTopEdge())){
                return true;
            }
            t.flip();
            if(current.equals(t.getTopEdge())){
                return true;
            }
            t.flip();
            t.rotate90();
        }
        return false;
    }

    public boolean tryAttachTop(Tile t){
        Edge current = getTopEdge();
        for(int i = 0;i < 4;i++){
            if(current.equals(t.getBottomEdge())){
                return true;
            }
            t.flip();
            if(current.equals(t.getBottomEdge())){
                return true;
            }
            t.flip();
            t.rotate90();
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public HashMap<Point,Boolean> getGridWithRemovedBorder(){
        HashMap<Point,Boolean> map = new HashMap<>();
        for(Point p : grid.keySet()){
            boolean val = grid.get(p);
            if(p.getX() != 0 && p.getX() != size - 1 && p.getY() != 0 && p.getY() != size - 1){
                map.put(new Point(p.getX()-1,p.getY()-1),val);
            }
        }
        return map;
    }

    public long countSetExcept(HashSet<Point> s1,HashSet<Point> s2){
        HashSet<Point> s3 = new HashSet<>(s1);
        s3.removeAll(s2);
        return s3.size();
    }

    public long findPatternAndCountWaves(HashSet<Point> pattern){
        for(int i = 0;i < 4;i++){
            HashSet<Point> matches = searchPattern(grid,pattern);
            if(matches.size() > 0){
                return countSetExcept(new HashSet<>(grid.keySet()),matches);
            }
            flip();
            matches = searchPattern(grid,pattern);
            if(matches.size() > 0){
                return countSetExcept(new HashSet<>(grid.keySet()),matches);
            }
            flip();
            rotate90();
        }
        return 0;
    }

    public HashSet<Point> searchPattern(HashMap<Point,Boolean> map, HashSet<Point> pattern){
        HashSet<Point> allmatches = new HashSet<Point>();
        int cnt = 0;
        // Overscan the pattern but who cares
        for(int y = 0;y < size;y++) {
            for (int x = 0; x < size; x++) {
                boolean found = true;
                HashSet<Point> matches = new HashSet<Point>();
                for(Point p : pattern){
                    Point np = new Point(p.getX()+x,p.getY()+y);
                    if(map.getOrDefault(np,false)){
                        matches.add(np);
                    }else{
                        found = false;
                        break;
                    }
                }
                if(found){
                    allmatches.addAll(matches);
                    cnt++;
                }
            }
        }
        return allmatches;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", size=" + size +
                '}';
    }
}
