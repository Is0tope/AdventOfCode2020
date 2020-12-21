package Challenge21;

import java.util.HashSet;

public class Food {
    private HashSet<String> ingredients;
    private HashSet<String> allergens;

    public Food(HashSet<String> ingredients, HashSet<String> allergens) {
        this.ingredients = ingredients;
        this.allergens = allergens;
    }

    public HashSet<String> getIngredients() {
        return ingredients;
    }

    public HashSet<String> getAllergens() {
        return allergens;
    }

    @Override
    public String toString() {
        return "Food{" +
                "ingredients=" + ingredients +
                ", allergens=" + allergens +
                '}';
    }
}
