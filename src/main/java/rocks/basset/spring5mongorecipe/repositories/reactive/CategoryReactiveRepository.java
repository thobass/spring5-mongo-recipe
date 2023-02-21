package rocks.basset.spring5mongorecipe.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import rocks.basset.spring5mongorecipe.domain.Category;

import java.util.Optional;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> findByDescription(String description);
}
