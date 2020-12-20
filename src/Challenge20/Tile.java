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

    private Tile left;
    private Tile right;
    private Tile up;
    private Tile down;

    public boolean hasEmptyEdge(){
        return right == null || left == null || up == null || down == null;
    }

    public Tile(long id, int size, HashMap<Point, Boolean> grid) {
        this.id = id;
        this.size = size;
        this.grid = grid;

        this.up = null;
        this.down = null;
        this.right = null;
        this.left = null;

        this.edges = new HashSet<Edge>();
        for(int i = 0;i < 3;i++){
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
        if(left == null){
            Edge current = getRightEdge();
            for(int i = 0;i < 3;i++){
                if(current.equals(t.getLeftEdge())){
                    attachRight(t);
                    return true;
                }
                t.flip();
                if(current.equals(t.getLeftEdge())){
                    attachRight(t);
                    return true;
                }
                t.flip();
                t.rotate90();
            }
        }
        return false;
    }

    private void attachRight(Tile t){
        this.right = t;
        t.setLeft(this);
    }

    public boolean tryAttachLeft(Tile t){
        if(left == null){
            Edge current = getLeftEdge();
            for(int i = 0;i < 3;i++){
                if(current.equals(t.getRightEdge())){
                    attachLeft(t);
                    return true;
                }
                t.flip();
                if(current.equals(t.getRightEdge())){
                    attachLeft(t);
                    return true;
                }
                t.flip();
                t.rotate90();
            }
        }
        return false;
    }

    private void attachLeft(Tile t){
        this.left = t;
        t.setRight(this);
    }

    public boolean tryAttachBottom(Tile t){
        if(down == null){
            Edge current = getBottomEdge();
            for(int i = 0;i < 3;i++){
                if(current.equals(t.getTopEdge())){
                    attachBottom(t);
                    return true;
                }
                t.flip();
                if(current.equals(t.getTopEdge())){
                    attachBottom(t);
                    return true;
                }
                t.flip();
                t.rotate90();
            }
        }
        return false;
    }

    private void attachBottom(Tile t){
        this.down = t;
        t.setUp(this);
    }


    public boolean tryAttachTop(Tile t){
        if(up == null){
            Edge current = getTopEdge();
            for(int i = 0;i < 3;i++){
                if(current.equals(t.getBottomEdge())){
                    attachTop(t);
                    return true;
                }
                t.flip();
                if(current.equals(t.getBottomEdge())){
                    attachTop(t);
                    return true;
                }
                t.flip();
                t.rotate90();
            }
        }
        return false;
    }

    private void attachTop(Tile t){
        this.up = t;
        t.setDown(this);
    }


    public void setLeft(Tile left) {
        this.left = left;
    }

    public void setRight(Tile right) {
        this.right = right;
    }

    public void setUp(Tile up) {
        this.up = up;
    }

    public void setDown(Tile down) {
        this.down = down;
    }

    public Tile getLeft() {
        return left;
    }

    public Tile getRight() {
        return right;
    }

    public Tile getUp() {
        return up;
    }

    public Tile getDown() {
        return down;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        long l=0;
        long r=0;
        long u=0;
        long d=0;
        if(left != null){
            l = left.getId();
        }
        if(right != null){
            r = right.getId();
        }
        if(up != null){
            u = up.getId();
        }if(down != null){
            d = down.getId();
        }
        return "Tile{" +
                "id=" + id +
                ", left=" + l +
                ", right=" + r +
                ", up=" + u +
                ", down=" + d +
                '}';
    }
}
