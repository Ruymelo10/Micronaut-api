package example.micronaut.parser;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ParseGame {
    private List<String> lines;

    public ParseGame(List<String> lines) {
        this.lines = lines;
    }

    public List<Game> ParseGames(){
        List<GameLines> gameLines = parserLinesAnalyser();
        List<Game> games = new ArrayList<>();
        for(GameLines gameline : gameLines){
            games.add(parserGameLine(gameline));
        }
        return games;
    }

    private List<GameLines> parserLinesAnalyser(){
        List<GameLines> games = new ArrayList<>();
        int totalgames=0;
        String LastLine = lines.get(lines.size()-1);
        GameLines game = new GameLines();

        for(String line: lines){
            boolean initGame = RegExp.IsPresent(line.trim(),"InitGame");
            if (initGame){
                if (totalgames>0){
                    games.add(game);
                    game = new GameLines();
                }
                totalgames++;
                game.setName("game: "+totalgames);
            }else {
                game.addLine(line);
            }
            if(line.equals(LastLine)){
                games.add(game);
            }
        }
        return games;
    }

    private Game parserGameLine(GameLines game){
        List<String> lines = game.getLines();
        String name = game.getName();
        Map<String, Player> mapPlayers = new HashMap<>();
        Analyser.analyser(lines, mapPlayers);
        List<Player> players = new ArrayList<>(mapPlayers.values());
        return new Game(name,players);
    }

}
