package example.micronaut.parser;

import java.util.List;

public class Game {
    private String GameName;
    private List<Player> players;

    public Game(String GameName, List<Player> players) {
        this.GameName = GameName;
        this.players = players;
    }

    public String getGamename() { return GameName; }

    public List<Player> getPlayers() { return players; }
}
