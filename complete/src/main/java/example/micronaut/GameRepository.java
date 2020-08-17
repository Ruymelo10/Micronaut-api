package example.micronaut;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
@Singleton
public class GameRepository {
    Map<Integer, GameResponse> reportgame = new HashMap<>();

    public void insert(Integer id, GameResponse gameResponse){
        reportgame.put(id, gameResponse);
    }

    public GameResponse search(Integer id){
        return reportgame.get(id);
    }

}
