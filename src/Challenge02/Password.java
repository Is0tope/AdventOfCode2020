package Challenge02;

import java.util.HashMap;

public class Password {
    private final int min;
    private final int max;
    private final char letter;
    private final String password;

    public Password(int min, int max, char letter, String password) {
        this.min = min;
        this.max = max;
        this.letter = letter;
        this.password = password;
    }

    public boolean isValidA() {
        HashMap<Character,Integer> map = new HashMap<>();
        for (char c : password.toCharArray()) {
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }else{
                map.put(c,1);
            }
        }
        int numOccurrences = map.getOrDefault(letter,0);
        if (numOccurrences < min || numOccurrences > max){
            return false;
        }
        return true;
    }

    public boolean isValidB(){
        char[] arr = password.toCharArray();
        boolean minContains = arr[min-1] == letter;
        boolean maxContains = arr[max-1] == letter;
        return minContains ^ maxContains;
    }
}
