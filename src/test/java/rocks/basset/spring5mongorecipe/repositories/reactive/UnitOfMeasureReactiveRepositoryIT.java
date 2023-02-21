package rocks.basset.spring5mongorecipe.repositories.reactive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import rocks.basset.spring5mongorecipe.domain.Recipe;
import rocks.basset.spring5mongorecipe.domain.UnitOfMeasure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class UnitOfMeasureReactiveRepositoryIT {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @BeforeEach
    void setUp() {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    void testSave() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("New UOM");

        UnitOfMeasure savedUOM = unitOfMeasureReactiveRepository.save(uom).block();

        Long count = unitOfMeasureReactiveRepository.count().block();

        assertNotNull(savedUOM.getId());
        assertEquals(1, count);
    }

    @Test
    void testFindByDescription() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("New UOM");

        unitOfMeasureReactiveRepository.save(uom).block();

        UnitOfMeasure savedUOM = unitOfMeasureReactiveRepository.findByDescription("New UOM").block();

        assertNotNull(savedUOM.getId());
    }
}