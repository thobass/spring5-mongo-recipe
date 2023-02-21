package rocks.basset.spring5mongorecipe.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import rocks.basset.spring5mongorecipe.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String> {
    Mono<UnitOfMeasure> findByDescription(String description);

}
