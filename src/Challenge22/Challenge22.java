package Challenge22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge22 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge22/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge22/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static HashMap<Long, LinkedList<Long>> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            HashMap<Long,LinkedList<Long>> decks = new HashMap<>();
            while(reader.hasNextLine()){
                LinkedList<Long> deck = new LinkedList<>();
                long player = 0;
                while(true){
                    String l = "";
                    if(reader.hasNextLine()){
                        l = reader.nextLine();
                    }
                    if(l.isEmpty()){
                        decks.put(player,deck);
                        break;
                    }
                    Pattern pat = Pattern.compile("Player (\\d+):");
                    Matcher m = pat.matcher(l);
                    if(m.find()){
                        player = Long.parseLong(m.group(1));
                        continue;
                    }
                    deck.addLast(Long.parseLong(l));
                }
            }

            return decks;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long partA(HashMap<Long,LinkedList<Long>> decks) {
        LinkedList<Long> d1 = decks.get(1L);
        LinkedList<Long> d2 = decks.get(2L);
        LinkedList<Long> winner = null;
        while(true){
            long c1 = d1.removeFirst();
            long c2 = d2.removeFirst();
            if(c1 > c2){
                d1.addLast(c1);
                d1.addLast(c2);
            }else{
                d2.addLast(c2);
                d2.addLast(c1);
            }
            if(d1.isEmpty()){
                winner = d2;
                break;
            }
            if(d2.isEmpty()){
                winner = d1;
                break;
            }
        }
        return RecursiveGame.calcScore(winner);
    }

    public static long partB(HashMap<Long,LinkedList<Long>> decks) {
        RecursiveGame game = new RecursiveGame(decks.get(1L),decks.get(2L));
        long winner = game.play(0);
//        System.out.println(game);
        return game.getPlayerScore(winner);
    }
}
