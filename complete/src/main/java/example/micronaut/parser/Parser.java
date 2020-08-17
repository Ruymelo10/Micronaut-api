package example.micronaut.parser;

import example.micronaut.GameRepository;
import example.micronaut.GameResponse;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.Files;
import java.util.Map.Entry;
@Singleton
public class Parser{
    @Value("${log-file}")
    String file;
    @Inject
    GameRepository gameRepository;
    @EventListener
       public void onStartup (StartupEvent event){
           List<String> lines;
           try {
               lines= Files.readAllLines(Paths.get(file));
               ParseGame game = new ParseGame(lines);
               List<Game> games = game.ParseGames();
               for(int i = 0; i<games.size();i++){
                   List<String> players = new ArrayList<>();
                   Map<String, Integer> totalkillsplayer = new HashMap<>();
                   int Id = i;
                   int totalkills = totalkills(games.get(i));
                   for (Player player: games.get(i).getPlayers()){
                       players.add(player.getName());
                   }
                   for (Player player: games.get(i).getPlayers()){
                       totalkillsplayer.put(player.getName(),player.getKd().getValidKills());
                   }
                   GameResponse gameResponse = new GameResponse(totalkills,players,totalkillsplayer);
                   gameRepository.insert(Id,gameResponse);
               }
               printGames(games);
       }
           catch (IOException e){
               System.out.println("Erro");
           }
       }

       public void printGames(List<Game> games){
           System.out.println("Ranking: {");
           Map<String, Integer> totalkillsplayer = new HashMap<>();
           List<Player> players = new ArrayList<>();
           for (Game game : games){
               for(Player p : game.getPlayers()){
                   players.add(p);
               }
           }

           for (Player p : players){
               String name = p.getName();
               Integer killsgame = p.getKd().getValidKills();
               if(!totalkillsplayer.containsKey(name)){
                   totalkillsplayer.put(name, 0);
               }
               totalkillsplayer.put(name, totalkillsplayer.get(name) + killsgame);
           }

           for (Entry<String, Integer> Player : totalkillsplayer.entrySet()) {
               System.out.println(" " + Player.getKey() + ": " + Player.getValue());
           }
           System.out.println("}");
           System.out.println("");

           for (Game game : games){
               System.out.println(game.getGamename()+" {");
               int totalkills = totalkills(game);
               System.out.println("Total Kills: "+ totalkills);
               System.out.print("players: [");
               for (Player player: game.getPlayers()){
                   System.out.print("'" + player.getName() + "', ");
               }
               System.out.print("]");
               System.out.println("");
               System.out.println("Kills:{ ");
               for (Player player: game.getPlayers()){
                   System.out.println(player.getName()+" : "+player.getKd().getValidKills());
               }
               System.out.println(" }");
               System.out.println("}");
           }


       }
            public Integer totalkills(Game game){
                int totalkills = 0;
                for(Player player: game.getPlayers()){
                    totalkills = totalkills + player.getKd().getTotalDeaths();
                }
                return totalkills;
            }


}
