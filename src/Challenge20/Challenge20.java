package Challenge20;

import Util.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge20 {

    private static HashMap<Point,Tile> finalLayout;

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge20/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge20/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static HashMap<Long,Tile> process(String fname) {
        File file = new File(fname);
        HashMap<Long,Tile> map = new HashMap<>();
        try {
            Scanner reader = new Scanner(file);
            int lines = 1;
            while(reader.hasNextLine()){
                HashMap<Point,Boolean> grid = new HashMap<>();
                long id = 0;
                int y = 0;
                while(true){
                    String l = "";
                    if(reader.hasNextLine()){
                        l = reader.nextLine();
                    }
                    lines++;
                    if(l.isEmpty()){
                        Tile t = new Tile(id,y,grid);
                        map.put(id,t);
                        break;
                    }
                    Pattern pat = Pattern.compile("Tile (\\d+):");
                    Matcher m = pat.matcher(l);
                    if(m.find()){
                        id = Long.parseLong(m.group(1));
                    }else{
                        for(int x = 0; x < l.length(); x++){
                            if(l.charAt(x) == '#'){
                                grid.put(new Point(x,y),true);
                            }
                        }
                        y++;
                    }
                }
            }

            return map;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static void drawLayout(HashMap<Point,Tile> layout,int distance){
        for(int y = -distance;y < distance;y++){
            for(int x = -distance;x < distance;x++){
                Point p = new Point(x,y);
                if(layout.containsKey(p)){
                    long id = layout.get(p).getId();
                    System.out.print(" " + id + " ");
                }else{
                    System.out.print("      ");
                }
            }
            System.out.print("\n");
        }
    }

    public static ArrayList<Point> getCorners(HashMap<Point,Tile> map){
        // This assumes it is ALWAYS a square
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        for(Point p : map.keySet()){
            if(p.getX() > maxX){
                maxX = p.getX();
            }
            if(p.getX() < minX){
                minX = p.getX();
            }
            if(p.getY() > maxY){
                maxY = p.getY();
            }
            if(p.getY() < minY){
                minY = p.getY();
            }
        }
        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(minX,minY));
        list.add(new Point(minX,maxY));
        list.add(new Point(maxX,minY));
        list.add(new Point(maxX,maxY));
        return list;
    }

    public static long cornerSum(HashMap<Point,Tile> map){
        long total = 1L;
        ArrayList<Point> corners = getCorners(map);
        for(Point p : corners){
            total *= map.get(p).getId();
        }
        return total;
    }

    public static void drawMap(HashMap<Point,Boolean> map,int size){
        for(int y = 0;y < size;y++){
            for(int x = 0;x < size;x++){
                if(map.getOrDefault(new Point(x,y),false)){
                    System.out.print('#');
                }else{
                    System.out.print('.');
                }
            }
            System.out.print('\n');
        }
    }

    public static HashMap<Point,Boolean> getMonster(){
        String str = "                  # \n" +
                     "#    ##    ##    ###\n" +
                     " #  #  #  #  #  #  ";
        HashMap<Point,Boolean> ret = new HashMap<>();
        int y = 0;
        for(String s : str.split("\n")){
            for(int x = 0;x < s.length();x++){
                if(s.charAt(x) == '#'){
                    ret.put(new Point(x,y),true);
                }
            }
            y++;
        }
        return ret;
    }

    public static long partA(HashMap<Long,Tile> map) {
        HashSet<Long> visited = new HashSet<>();
        HashMap<Point,Tile> layout = new HashMap<>();
        HashMap<Long,Point> coords = new HashMap<>();
        // Choose a random starting tile
        long initial = map.keySet().iterator().next();
        Point origin = new Point(0,0);
        visited.add(initial);
        layout.put(origin,map.get(initial));
        coords.put(initial,origin);

        while(!visited.containsAll(map.keySet())){
            for(long v : map.keySet()){
                if(!visited.contains(v)){
                    continue;
                }
                Tile vtile = map.get(v);
                Point p = coords.get(v);

                for(long k : map.keySet()){
                    if(visited.contains(k)){
                        continue;
                    }
                    Tile ptile = map.get(k);
                    Point np = null;
                    // Up
                    np = new Point(p.getX(),p.getY()-1);
                    if(!layout.containsKey(np)){
                        if(ptile.containsEdge(vtile.getTopEdge())){
                            if(!vtile.tryAttachTop(ptile)){
                                System.out.println("Failed attach top " + vtile + ptile);
                            }
                            layout.put(np,ptile);
                            visited.add(k);
                            coords.put(k,np);
                            continue;
                        }
                    }
                    // Down
                    np = new Point(p.getX(),p.getY()+1);
                    if(!layout.containsKey(np)){
                        if(ptile.containsEdge(vtile.getBottomEdge())){
                            if(!vtile.tryAttachBottom(ptile)){
                                System.out.println("Failed attach bottom " + vtile + ptile);
                            }
                            layout.put(np,ptile);
                            visited.add(k);
                            coords.put(k,np);
                            continue;
                        }
                    }
                    // Right
                    np = new Point(p.getX()+1,p.getY());
                    if(!layout.containsKey(np)){
                        if(ptile.containsEdge(vtile.getRightEdge())){
                            if(!vtile.tryAttachRight(ptile)){
                                System.out.println("Failed attach right " + vtile + ptile);
                            }
                            layout.put(np,ptile);
                            visited.add(k);
                            coords.put(k,np);
                            continue;
                        }
                    }
                    // Left
                    np = new Point(p.getX()-1,p.getY());
                    if(!layout.containsKey(np)){
                        if(ptile.containsEdge(vtile.getLeftEdge())){
                            if(!vtile.tryAttachLeft(ptile)){
                                System.out.println("Failed attach left " + vtile + ptile);
                            }
                            layout.put(np,ptile);
                            visited.add(k);
                            coords.put(k,np);
                            continue;
                        }
                    }
                }
            }
        }
//        drawLayout(layout,10);
        finalLayout = layout;
        return cornerSum(layout);
    }

    public static long partB(HashMap<Long,Tile> map) {
        // Hacky but whatever
        ArrayList<Point> corners = getCorners(finalLayout);
        Point topleft = corners.get(0); // First one is top left
        Point bottomright = corners.get(3); // last one is bottom right
        int xdiff = 1 + bottomright.getX()-topleft.getX();
        int ydiff = 1 + bottomright.getY()-topleft.getY();
        int offset = finalLayout.get(finalLayout.keySet().iterator().next()).getSize() - 2;

        HashMap<Point,Boolean> full = new HashMap<>();
        for(int i = 0;i < ydiff;i++){
            for(int j = 0;j < xdiff;j++){
                Point pos = new Point(topleft.getX()+j,topleft.getY()+i);
                HashMap<Point,Boolean> part = finalLayout.get(pos).getGridWithRemovedBorder();
                for(Point p : part.keySet()){
                    boolean val = part.get(p);
                    Point np = new Point(p.getX() + j*offset,p.getY() + i*offset);
                    full.put(np,val);
                }
            }
        }
        // Get all of the possible map versions by reusing the tile methods
        Tile tfull = new Tile(0,xdiff*offset,full);
        HashMap<Point,Boolean> monster = getMonster();
        return tfull.findPatternAndCountWaves(new HashSet<Point>(monster.keySet()));
    }
}
