package rocks.basset.spring5mongorecipe.services;

import reactor.core.publisher.Flux;
import rocks.basset.spring5mongorecipe.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by jt on 6/28/17.
 */
public interface UnitOfMeasureService {

    Flux<UnitOfMeasureCommand> listAllUoms();
}
