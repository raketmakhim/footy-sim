package objects;

import java.util.List;
import java.util.stream.Collectors;

public class Team {
    public String teamName;
    public List<Player> startingEleven;
    public byte power;
    public int points;
    public byte win;
    public byte draw;
    public byte lose;

    public Team(){
        points = 0;
    }

    public Team(String teamName, List<Player> startingEleven){
        this.teamName = teamName;
        this.startingEleven = startingEleven;
        this.power = calculateTeamPower();
    }

    public byte calculateTeamPower(){
        return (byte) Math.round(startingEleven.stream().collect(Collectors.averagingInt(Player::getPower)));
    }

    public void win(){
        points+=3;
        win++;
    }

    public void draw(){
        points++;
        draw++;
    }

    public void loss(){
        lose++;
    }

    public int getPoints(){
        return points;
    }


}
