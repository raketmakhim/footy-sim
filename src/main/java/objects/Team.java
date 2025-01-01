package objects;

import constants.PowerConstants;
import enums.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Team {
    public String teamName;
    public List<Player> startingEleven;
    public byte power;
    public byte defensivePower;
    public byte offensivePower;
    public int points;
    public byte win;
    public byte draw;
    public byte lose;
    public int goalsScored;
    public int goalsConceded;

    public Team(){
        points = 0;
    }

    public Team(String teamName, List<Player> startingEleven){
        this.teamName = teamName;
        this.startingEleven = startingEleven;
        this.power = calculateTeamPower();
        this.defensivePower = calculateTotalDefensivePower();
        this.offensivePower = calculateTotalOffensivePower();
    }

    public byte calculateTeamPower(){
        return (byte) Math.round(startingEleven.stream().collect(Collectors.averagingInt(Player::getPower)));
    }

    public byte calculateTotalDefensivePower(){
        return (byte) ((PowerConstants.calculateDefensivePower(startingEleven)*6 +
                        PowerConstants.calculateMidfieldPower(startingEleven)*3 +
                        PowerConstants.calculateOffensivePower(startingEleven))/10);
    }

    public byte calculateTotalOffensivePower(){
        return (byte) ((PowerConstants.calculateDefensivePower(startingEleven) +
                PowerConstants.calculateMidfieldPower(startingEleven)*3 +
                PowerConstants.calculateOffensivePower(startingEleven)*6)/10);
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
