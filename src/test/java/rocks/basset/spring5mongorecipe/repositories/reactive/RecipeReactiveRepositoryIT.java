package rocks.basset.spring5mongorecipe.repositories.reactive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import rocks.basset.spring5mongorecipe.domain.Recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class RecipeReactiveRepositoryIT {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @BeforeEach
    void setUp() {
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    void testRecipeSave() {
        Recipe recipe = new Recipe();
        recipe.setDescription("New Recipe");

        Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();

        Long count = recipeReactiveRepository.count().block();

        assertNotNull(savedRecipe.getId());
        assertEquals(1, count);
    }
}