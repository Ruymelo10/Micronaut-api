package example.micronaut.parser;

public class Player {
    private String id, name;
    private PlayerKD kd;

    public Player(String id, String name, PlayerKD kd) {
        this.id = id;
        this.name = name;
        this.kd = kd;
    }
    public String toString() {
        return "nome: " + this.name + ", playerKD: " + this.kd;
    }

    public String getName(){ return name; }

    public String getId(){ return id; }

    public PlayerKD getKd() { return kd; }

    public void setId(String id) { this.id = id; }

}
