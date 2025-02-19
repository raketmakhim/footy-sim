package objects;

import java.util.List;

public class Team {
    private String teamName;
    private List<Player> startingEleven;
    private int points;
    private byte win;
    private byte draw;
    private byte lose;
    private int goalsScored;
    private int goalsConceded;
    private byte power;
    private byte defensivePower;
    private byte offensivePower;

    public Team(String teamName, List<Player> startingEleven) {
        this.teamName = teamName;
        this.startingEleven = List.copyOf(startingEleven); // Immutable copy
        this.points = 0;
    }

    public Team(String teamName, byte power, byte offensivePower, byte defensivePower) {
        this.teamName = teamName;
        this.power = power;
        this.offensivePower = offensivePower;
        this.defensivePower = defensivePower;
    }

    public void recordWin() {
        points += 3;
        win++;
    }

    public void recordDraw() {
        points++;
        draw++;
    }

    public void recordLoss() {
        lose++;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void addGoalsScored(int goalsScored) {
        this.goalsScored += goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void addGoalsConceded(int goalsConceded) {
        this.goalsConceded += goalsConceded;
    }

    public void setPower(byte power){
        this.power = power;
    }

    public Team(){
        this.points = 0;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Player> getStartingEleven() {
        return startingEleven; // Immutable list
    }

    public int getPoints() {
        return points;
    }

    public byte getWins() {
        return win;
    }

    public byte getDraws() {
        return draw;
    }

    public byte getLosses() {
        return lose;
    }

    public byte getPower() {
        return power;
    }

    public int getDefensivePower() {
        return defensivePower;
    }

    public void setDefensivePower(byte defensivePower) {
        this.defensivePower = defensivePower;
    }

    public int getOffensivePower() {
        return offensivePower;
    }

    public void setOffensivePower(byte offensivePower) {
        this.offensivePower = offensivePower;
    }
}
