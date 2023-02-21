package rocks.basset.spring5mongorecipe.services;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rocks.basset.spring5mongorecipe.commands.RecipeCommand;
import rocks.basset.spring5mongorecipe.converters.RecipeCommandToRecipe;
import rocks.basset.spring5mongorecipe.converters.RecipeToRecipeCommand;
import rocks.basset.spring5mongorecipe.domain.Recipe;
import rocks.basset.spring5mongorecipe.exceptions.NotFoundException;
import rocks.basset.spring5mongorecipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rocks.basset.spring5mongorecipe.repositories.reactive.RecipeReactiveRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeReactiveRepository recipeReactiveRepository;

    @Override
    public Flux<Recipe> getRecipes() {
        log.debug("I'm in the service");

        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {
        return recipeReactiveRepository.findById(id);
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {
        return recipeReactiveRepository.findById(id)
                .map(recipe -> {
                    RecipeCommand command = recipeToRecipeCommand.convert(recipe);

                    command.getIngredients().forEach(ingredientCommand -> {
                        ingredientCommand.setRecipeId(command.getId());
                    });

                    return command;
                });
    }


    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        return recipeReactiveRepository.save(recipeCommandToRecipe.convert(command))
                .map(recipeToRecipeCommand::convert);
    }

    @Override
    public Mono<Void> deleteById(String idToDelete) {
        recipeReactiveRepository.deleteById(idToDelete);
        return Mono.empty();
    }
}
