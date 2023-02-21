package rocks.basset.spring5mongorecipe.services;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import rocks.basset.spring5mongorecipe.commands.UnitOfMeasureCommand;
import rocks.basset.spring5mongorecipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import rocks.basset.spring5mongorecipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;
import rocks.basset.spring5mongorecipe.repositories.reactive.UnitOfMeasureReactiveRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by jt on 6/28/17.
 */
@Service
@RequiredArgsConstructor
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureReactiveRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Override
    public Flux<UnitOfMeasureCommand> listAllUoms() {
        return unitOfMeasureRepository.findAll().map(unitOfMeasureToUnitOfMeasureCommand::convert);

//        return StreamSupport.stream(unitOfMeasureRepository.findAll()
//                .spliterator(), false)
//                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
//                .collect(Collectors.toSet());
    }
}
