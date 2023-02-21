package rocks.basset.spring5mongorecipe.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import rocks.basset.spring5mongorecipe.domain.Recipe;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
