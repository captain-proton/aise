package ude.masteraise.concurrency.smoker;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nils on 03.08.16.
 */
public enum Ingredient
{
    Tobacco,
    Match,
    Paper;

    public static Set<Ingredient> random(int count)
    {
        int ingredientCount = values().length;
        if (count > ingredientCount)
            throw new IllegalArgumentException("count max value is " + ingredientCount + " was " + count);

        int sum = 0;
        Set<Ingredient> result = new HashSet<>();
        List<Ingredient> ingredients = Arrays.stream(values()).collect(Collectors.toList());
        while (sum < count)
        {
            int random = RandomUtils.nextInt(0, ingredients.size());
            sum += result.add(ingredients.remove(random))
                   ? 1
                   : 0;
        }
        return result;
    }
}
