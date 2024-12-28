package objects;

import java.util.List;
import java.util.stream.Collectors;

public class Team {
    public String teamName;
    public List<Player> startingEleven;
    public byte power;

    public Team(){

    }

    public Team(String teamName, List<Player> startingEleven){
        this.teamName = teamName;
        this.startingEleven = startingEleven;
        this.power = calculateTeamPower();
    }

    public byte calculateTeamPower(){
        return (byte) Math.round(startingEleven.stream().collect(Collectors.averagingInt(Player::getPower)));
    }

}
