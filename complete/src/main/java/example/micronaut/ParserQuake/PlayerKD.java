package example.micronaut.ParserQuake;

import java.util.HashMap;
import java.util.Map;
public class PlayerKD {
    private Integer ValidKills;
    private Integer TotalDeaths;
    private Map<DeathType, Integer> deathTypeIntegerM;

    public PlayerKD() {
        ValidKills = 0;
        TotalDeaths = 0;
        deathTypeIntegerM = new HashMap<>();
    }

    public Map<DeathType, Integer> getDeathTypeIntegerM() { return deathTypeIntegerM; }

    public Integer getTotalDeaths() { return TotalDeaths; }

    public Integer getValidKills() { return ValidKills; }

    public void ValidKill(){ ValidKills++; }

    public void Kill(){
    }

    public void Death(){ TotalDeaths++; }

    public void WorldKill(){ ValidKills--; }

    public void incDeathType(DeathType dt){
        if (!deathTypeIntegerM.containsKey(dt)) {
            deathTypeIntegerM.put(dt,1);
        }
        else {
            Integer x = deathTypeIntegerM.get(dt);
            deathTypeIntegerM.put(dt, x + 1);
        }
    }


}
