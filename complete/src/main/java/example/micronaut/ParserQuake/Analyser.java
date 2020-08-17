package example.micronaut.ParserQuake;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class Analyser {
    public static void analyser(List<String> lines, Map<String, Player> players){
        for(String line : lines){
            Matcher PlayerRegister = RegExp.getMatcherForSentence(line, "ClientUserinfoChanged");
            if (PlayerRegister.matches()){
                analyserRegister(PlayerRegister, players);
            }
            Matcher Kill = RegExp.getMatcherForSentence(line, "Kill");
            if(Kill.matches()){
                analyserKill(Kill, players);
            }
        }
    }
    public static void analyserRegister(Matcher PlayerRegister, Map<String,Player> players){
        String player = PlayerRegister.group(3).trim();
        String playerID = null;
        String playerName = null;
        if (player.length()>0){
            playerID = player.substring(0,1);
            int nameStart = player.indexOf("n\\");
            int nameEnd = player.indexOf("\\t\\");

            if (nameStart == 0 || nameEnd == 0) {
                playerName = "";
            }else{
                playerName = player.substring(nameStart + 2, nameEnd);
            }
        }else {
            playerID="";
            playerName = "";
        }
        if (players.containsKey(playerID)){
            Player old = players.get(playerID);
            if(!old.getName().equals(playerName)){
                players.remove(playerID);
                old.setId("1000" + old.getId());
                players.put(old.getId(),old);
                players.put(playerID, new Player (playerID, playerName, new PlayerKD()));
            }
        }else {
            players.put(playerID, new Player(playerID, playerName, new PlayerKD()));
        }
    }

    private static void analyserKill (Matcher Kill, Map<String, Player> players) {
        String playerKD = Kill.group(3).trim();
        Matcher matcherKD = RegExp.createPatternForSetence("([0-9]*)\\s([0-9]*)\\s([0-9]*)(.*)", playerKD);
        if (!matcherKD.matches()){
            return;
        }
        String idKiller = matcherKD.group(1);
        String idKilled = matcherKD.group(2);
        String type = matcherKD.group(3);


        if (!idKiller.equals(idKilled)){
            Player killer = players.get(idKiller);
            if(!(killer==null)){
                killer.getKd().Kill();
                killer.getKd().ValidKill();
            }
        }

        Player killed = players.get(idKilled);
        if(!(killed==null)){
            killed.getKd().Death();
            killed.getKd().incDeathType(DeathType.getType(Integer.valueOf(type)));
        }
        if(idKiller.equals("1022")){
            Player KilledWorld = players.get(idKilled);
            if(!(KilledWorld == null)){
                KilledWorld.getKd().WorldKill();
            }
        }
    }


}
