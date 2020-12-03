package Challenge03;

import java.util.HashSet;

public class Slope {
    private int width;
    private int height;
    private HashSet<String> grid;

    public Slope() {
        this.width = 0;
        this.height = 0;
        this.grid = new HashSet<String>();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addTree(int x,int y){
        grid.add(x + "," + y);
    }

    public boolean isTree(int x, int y) {
        String key = (x % width) + "," + y;
        return grid.contains(key);
    }
}
