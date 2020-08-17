package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import javax.inject.Inject;

@Controller("/games")
public class GameController {
    @Inject GameRepository gameRepository;

    @Get("/{id}")
    public GameResponse returnGame(@PathVariable Integer id){
        return gameRepository.search(id);
    }
}
