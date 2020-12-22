package Challenge22;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class RecursiveGame {
    private LinkedList<Long> d1;
    private LinkedList<Long> d2;
    // Initial state when this game started (for memoizing)
    private RecursiveGame initialState;
    private HashSet<RecursiveGame> previous;

    // Memoizer
    private static HashMap<RecursiveGame,Long> memoizer = new HashMap<>();

    public static long calcScore(LinkedList<Long> list){
        long total = 0;
        for(int i = 0;i < list.size();i++){
            total += list.get((list.size()-1)-i)*(i+1);
        }
        return total;
    }

    public RecursiveGame(LinkedList<Long> deck1, LinkedList<Long> deck2) {
        // Always clone here
        this.d1 = new LinkedList<>(deck1);
        this.d2 = new LinkedList<>(deck2);
        this.initialState = new RecursiveGame(deck1,deck2,false);   // Hacky, but need it to stop recursing in constructor
        this.previous = new HashSet<>();
    }

    public RecursiveGame(LinkedList<Long> deck1, LinkedList<Long> deck2, boolean flag) {
        // Always clone here
        this.d1 = new LinkedList<>(deck1);
        this.d2 = new LinkedList<>(deck2);
        this.initialState = null;
        this.previous = null;
    }

    public long getPlayerScore(long player){
        if(player == 1){
            return calcScore(d1);
        }
        if(player == 2){
            return calcScore(d2);
        }
        return -1;
    }

    // Returns winner number
    public long play(long depth){
        // Skip if we have seen this state before in general
        if(memoizer.containsKey(this)){
            return memoizer.get(this);
        }
        // Run the game loop
        while(true){
            // If we have seen this round before in this game player 1 wins by default
            if(previous.contains(this)){
                return 1;
            }
            // Add this game
            previous.add(new RecursiveGame(d1,d2));
            // Get cards
            long c1 = d1.removeFirst();
            long c2 = d2.removeFirst();
            long roundWinner = 0;
            // Check for recursion
            if(d1.size() >= c1 && d2.size() >= c2){
                RecursiveGame subGame = new RecursiveGame(new LinkedList<>(d1.subList(0,(int)c1)),new LinkedList<>(d2.subList(0,(int)c2)));
                roundWinner = subGame.play(depth++);
            }else{
                if(c1 > c2){
                    roundWinner = 1;
                }else{
                    roundWinner = 2;
                }
            }
            // Move cards around
            if(roundWinner == 1){
                d1.addLast(c1);
                d1.addLast(c2);
            }else{
                d2.addLast(c2);
                d2.addLast(c1);
            }
            // Determine game winner
            if(d1.isEmpty()){
                memoizer.put(initialState,2L);
                return 2;
            }
            if(d2.isEmpty()){
                memoizer.put(initialState,1L);
                return 1;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecursiveGame that = (RecursiveGame) o;
        return d1.equals(that.d1) &&
                d2.equals(that.d2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(d1, d2);
    }

    @Override
    public String toString() {
        return "RecursiveGame{" +
                "d1=" + d1 +
                ", d2=" + d2 +
                ", previous=" + previous +
                '}';
    }
}
