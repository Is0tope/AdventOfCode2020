package Challenge21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Challenge21 {

    private static HashSet<String> noAllergens = new HashSet<>();

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge21/input.txt"));
        System.out.println("Part A: " + resA);
        String resB = partB(process("src/Challenge21/input.txt"));
        System.out.println("Part B: " + resB);
    }

    public static ArrayList<Food> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<Food> list = new ArrayList<>();
            while(reader.hasNextLine()){
                String l = reader.nextLine();
                String[] tmp = l.split(" \\(contains ");
                HashSet<String> ingredients = new HashSet<>();
                for(String ing : tmp[0].split(" ")){
                    ingredients.add(ing.strip());
                }
                HashSet<String> allergens = new HashSet<>();
                for(String ing : tmp[1].replace(")","").split(",")){
                    allergens.add(ing.strip());
                }
                list.add(new Food(ingredients,allergens));
            }
            return list;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static boolean validateMapping(ArrayList<Food> foods,String ingredient,String allergen){
        for(Food f : foods){
            if(f.getAllergens().contains(allergen)){
                if(!f.getIngredients().contains(ingredient)){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean validateArrayMapping(ArrayList<HashSet<String>> ingredients,ArrayList<HashSet<String>> allergens,String ingredient,String allergen){
        for(int i = 0;i < ingredients.size();i++){
            HashSet<String> ings = ingredients.get(i);
            HashSet<String> als = allergens.get(i);
            if(als.contains(allergen)){
                if(!ings.contains(ingredient)){
                    return false;
                }
            }
        }
        return true;
    }

    public static long countOccurances(ArrayList<Food> foods,String ingredient){
        long count = 0;
        for(Food f : foods){
            if(f.getIngredients().contains(ingredient)){
                count++;
            }
        }
        return count;
    }


    public static long partA(ArrayList<Food> list) {
        HashSet<String> allIngredients = new HashSet<>();
        HashSet<String> allAllergens = new HashSet<>();

        for(Food f : list){
            allIngredients.addAll(f.getIngredients());
            allAllergens.addAll(f.getAllergens());
        }

        for(String ingredient : allIngredients){
            boolean possible = false;
            for(String allergen : allAllergens){
                if(validateMapping(list,ingredient,allergen)){
                    possible = true;
                    break;
                }
            }
            if(!possible){
                noAllergens.add(ingredient);
            }
        }

        long count = 0;
        for(String ing : noAllergens){
            count += countOccurances(list,ing);
        }

        return count;
    }

    public static String partB(ArrayList<Food> list) {
        HashSet<String> allIngredients = new HashSet<>();
        HashSet<String> allAllergens = new HashSet<>();
        for(Food f : list){
            allIngredients.addAll(f.getIngredients());
            allAllergens.addAll(f.getAllergens());
        }
        allIngredients.removeAll(noAllergens);

        ArrayList<HashSet<String>> left = new ArrayList<>();
        ArrayList<HashSet<String>> right = new ArrayList<>();
        for(Food f : list){
            HashSet<String> ings = new HashSet<>(f.getIngredients());
            ings.removeAll(noAllergens);
            HashSet<String> als = new HashSet<>(f.getAllergens());
            left.add(ings);
            right.add(als);
        }

        HashMap<String,String> mapping = new HashMap<>();
        while(!mapping.keySet().containsAll(allIngredients)){
            for(String ingredient : allIngredients) {
                if(mapping.containsKey(ingredient)){
                    continue;
                }
                int count = 0;
                String lastAllergen = "";
                for (String allergen : allAllergens) {
                    if(mapping.containsValue(allergen)){
                        continue;
                    }
                    if(validateArrayMapping(left,right,ingredient,allergen)){
                        count++;
                        lastAllergen = allergen;
                    }
                }
                if(count == 1){
                    mapping.put(ingredient,lastAllergen);
                    for(int i = 0;i < left.size();i++){
                        left.get(i).remove(ingredient);
                        right.get(i).remove(lastAllergen);
                    }
                }
            }
        }
        HashMap<String,String> reverse = new HashMap<>();
        for(String k : mapping.keySet()){
            reverse.put(mapping.get(k),k);
        }
        StringJoiner joiner = new StringJoiner(",");
        ArrayList<String> sorted = new ArrayList<>(reverse.keySet());
        Collections.sort(sorted);
        for(String al : sorted){
            joiner.add(reverse.get(al));
        }
        return joiner.toString();
    }
}
