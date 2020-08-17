package example.micronaut;

import java.util.List;
import java.util.Map;

public class GameResponse {
    Integer totalkills;
    List<String> players;
    Map<String, Integer> kills;

    public GameResponse(Integer totalkills, List<String> players, Map<String, Integer> kills) {
        this.totalkills = totalkills;
        this.players = players;
        this.kills = kills;
    }

    public Integer getTotalkills() { return totalkills; }

    public List<String> getPlayers() { return players; }

    public Map<String, Integer> getKills() { return kills; }


}
