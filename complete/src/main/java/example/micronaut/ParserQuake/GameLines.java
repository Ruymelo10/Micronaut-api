package example.micronaut.ParserQuake;

import java.util.ArrayList;
import java.util.List;

public class GameLines {
    private String name;
    private List<String> lines;

    public GameLines() {
        this.lines = new ArrayList<>();
    }

    public String getName() { return name; }

    public List<String> getLines() { return lines; }

    public void setName(String name) {this.name = name; }

    public  void  addLine(String line){lines.add(line);}

}
