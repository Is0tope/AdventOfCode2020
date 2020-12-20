package Challenge20;

import Util.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge20 {

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
            while(reader.hasNextLine()){
                HashMap<Point,Boolean> grid = new HashMap<>();
                long id = 0;
                int y = 0;
                while(true){
                    String l = reader.nextLine();
                    if(l.isEmpty() || !reader.hasNextLine()){
                        map.put(id,new Tile(id,y,grid));
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

    public static long partA(HashMap<Long,Tile> map) {
        HashSet<Long> visited = new HashSet<>();
        // Choose a random starting tile
//        visited.add(map.keySet().iterator().next());
        map.get(1951L).print();
        System.out.println();
        map.get(1951L).flip();
        map.get(1951L).rotate90();
        map.get(1951L).rotate90();
        map.get(1951L).rotate90();
        map.get(1951L).print();
        visited.add(1951L);
//        Tile t = map.get(1951L);
//        t.print();
//        System.out.println();
//        Tile t2 = map.get(2311L);
//        t2.print();
//        System.out.println(t2.containsEdge(t.getTopEdge()));
//        t.tryAttachTop(t2);
//        Tile t3 = map.get(2729L);
//        System.out.println(t3.containsEdge(t.getLeftEdge()));
//        t.tryAttachLeft(t3);
//        System.out.println(t);
//        System.out.println(t2);
//        System.out.println(t3);
//        t2.print();
//        t.print();
//        System.out.println();
//        t3.print();

        while(!visited.containsAll(map.keySet())){
            for(long v : map.keySet()){
                if(!visited.contains(v)){
                    continue;
                }
                Tile vtile = map.get(v);
                for(long k : map.keySet()){
                    if(visited.contains(k)){
                        continue;
                    }
                    Tile ptile = map.get(k);
                    if(vtile.getUp() == null && ptile.containsEdge(vtile.getTopEdge())){
                        vtile.tryAttachTop(ptile);
                        visited.add(k);
                        continue;
                    }
                    if(vtile.getDown() == null && ptile.containsEdge(vtile.getBottomEdge())){
                        vtile.tryAttachBottom(ptile);
                        visited.add(k);
                        continue;
                    }
                    if(vtile.getRight() == null && ptile.containsEdge(vtile.getRightEdge())){
                        vtile.tryAttachRight(ptile);
                        visited.add(k);
                        continue;
                    }
                    if(vtile.getLeft() == null && ptile.containsEdge(vtile.getLeftEdge())){
                        vtile.tryAttachLeft(ptile);
                        visited.add(k);
                        continue;
                    }
                }
            }
            System.out.println(visited);
            System.out.println(map);
        }


//        System.out.println(map);
//        Tile t = map.get(map.keySet().iterator().next());
//        t.print();
//        System.out.print('\n');
//        t.rotate90();
//        t.print();
//        System.out.print('\n');
//        t.rotate90();
//        t.print();
//        System.out.print('\n');
//        t.rotate90();
//        t.print();
//        System.out.print('\n');
//        t.rotate90();
//        t.print();
//        System.out.print('\n');
//        t.flip();
//        t.print();
//        System.out.print('\n');
        return -1;
    }

    public static long partB(HashMap<Long,Tile> map) {
        return -1;
    }
}
