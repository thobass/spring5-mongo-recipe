package rocks.basset.spring5mongorecipe.repositories.reactive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import rocks.basset.spring5mongorecipe.domain.Category;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @BeforeEach
    void setUp() {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    void testSave() {
        Category category = new Category();
        category.setDescription("Test Category");

        Category savedCategory = categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertNotNull(savedCategory.getId());
        assertEquals(1, count);
    }

    @Test
    void testFindByDescription() {
        Category category = new Category();
        category.setDescription("Test Category");

        categoryReactiveRepository.save(category).then().block();

        Category fetchedCategory = categoryReactiveRepository.findByDescription("Test Category").block();

        assertNotNull(fetchedCategory.getId());
    }
}