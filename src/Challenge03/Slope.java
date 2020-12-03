package Challenge03;

import java.util.HashSet;

public class Slope {
    private int width;
    private int height;
    private HashSet<String> grid;

    public Slope(int width, int height, HashSet<String> grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isTree(int x, int y) {
        String key = (x % width) + "," + y;
        return grid.contains(key);
    }
}
