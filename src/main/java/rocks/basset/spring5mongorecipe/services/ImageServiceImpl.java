package rocks.basset.spring5mongorecipe.services;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import rocks.basset.spring5mongorecipe.domain.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rocks.basset.spring5mongorecipe.repositories.reactive.RecipeReactiveRepository;

import java.io.IOException;

/**
 * Created by jt on 7/3/17.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {


    private final RecipeReactiveRepository recipeReactiveRepository;

    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {
        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId)
                .map(recipe -> {
                    Byte[] byteObjects = new Byte[0];
                    try {
                        byteObjects = new Byte[file.getBytes().length];

                        int i = 0;

                        for (byte b : file.getBytes()) {
                            byteObjects[i++] = b;
                        }

                        recipe.setImage(byteObjects);

                        return recipe;

                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });

        recipeReactiveRepository.save(recipeMono.block()).block();

        return Mono.empty();
    }
}
