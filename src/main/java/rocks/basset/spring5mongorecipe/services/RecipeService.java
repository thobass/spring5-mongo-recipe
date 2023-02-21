package rocks.basset.spring5mongorecipe.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rocks.basset.spring5mongorecipe.commands.RecipeCommand;
import rocks.basset.spring5mongorecipe.domain.Recipe;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Flux<Recipe> getRecipes();

    Mono<Recipe> findById(String id);

    Mono<RecipeCommand> findCommandById(String id);

    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);

    Mono<Void> deleteById(String idToDelete);
}
