package rocks.basset.spring5mongorecipe.services;

import rocks.basset.spring5mongorecipe.commands.RecipeCommand;
import rocks.basset.spring5mongorecipe.domain.Recipe;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeCommand findCommandById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(String idToDelete);
}
