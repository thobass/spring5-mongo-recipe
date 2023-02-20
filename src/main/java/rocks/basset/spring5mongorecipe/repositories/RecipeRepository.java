package rocks.basset.spring5mongorecipe.repositories;

import rocks.basset.spring5mongorecipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
