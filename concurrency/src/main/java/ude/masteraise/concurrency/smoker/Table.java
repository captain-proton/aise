package ude.masteraise.concurrency.smoker;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nils on 03.08.16.
 */
public class Table
{
    private Set<Ingredient> ingredients;

    public Table()
    {
        this.ingredients = new HashSet<>();
    }

    public boolean addIngredient(Ingredient ingredient)
    {
        return ingredients.add(ingredient);
    }

    public boolean removeIngredient(Ingredient ingredient)
    {
        return ingredients.remove(ingredient);
    }

    public Set<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public boolean hasIngredients()
    {
        return ingredients.size() > 0;
    }
}
