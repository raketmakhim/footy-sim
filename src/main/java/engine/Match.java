package engine;

import constants.MatchConstants;
import enums.MatchOutcomes;
import objects.Team;

import static constants.MatchConstants.getAbsolutePower;

public class Match {
    public Team homeTeam;
    public Team awayTeam;
    public byte homeTeamGoals;
    public byte awayTeamGoals;

    public Match(Team homeTeam, Team awayTeam){
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
    }

    public Match(){
    }

    public byte getDrawThreshold(){
        return MatchConstants.calculateDrawThreshold(homeTeam.power, awayTeam.power);
    }

    public byte getWinOrLoseThreshold(){

        System.out.println("Home Team: " + homeTeam.teamName + ", Power: " + homeTeam.power);
        System.out.println("Away Team: " + awayTeam.teamName + ", Power: " + awayTeam.power);

        return MatchConstants.calculateWinOrLoseThreshold(homeTeam.power, awayTeam.power);
    }

    public MatchOutcomes getMatchOutcome() {
        homeTeamGoals = 0; awayTeamGoals = 0;

        byte randomNumber = (byte) (Math.random()*100);
        System.out.println("Random Number: " + randomNumber);

        int highPowerThreshold = MatchConstants.calculateWinThreshold(getDrawThreshold(), getWinOrLoseThreshold());

        if (randomNumber < getDrawThreshold()) {
            calculateGoalScored(MatchOutcomes.DRAW);
            return MatchOutcomes.DRAW;
        }

        boolean isHigherPowerWinsAndHomeTeamHasHigherPower = randomNumber < highPowerThreshold && homeTeam.power >= awayTeam.power;
        boolean isHigherPowerLosesAndHomeTeamHasLowerPower = randomNumber > highPowerThreshold && homeTeam.power < awayTeam.power;

        if (isHigherPowerWinsAndHomeTeamHasHigherPower || isHigherPowerLosesAndHomeTeamHasLowerPower) {
            calculateGoalScored(MatchOutcomes.HOME_WIN);
            return MatchOutcomes.HOME_WIN;
        }

        calculateGoalScored(MatchOutcomes.AWAY_WIN);

        addGoalsToTeams(homeTeam, homeTeamGoals, awayTeamGoals);
        addGoalsToTeams(awayTeam, awayTeamGoals, homeTeamGoals);

        return MatchOutcomes.AWAY_WIN;
    }

    private void addGoalsToTeams(Team team, byte goalsScored, byte goalsConceded){
        team.goalsScored += goalsScored;
        team.goalsConceded += goalsConceded;
    }

    public void calculateGoalScored(MatchOutcomes outcome){
        switch (outcome) {
            case HOME_WIN -> {
                calculateGoalsScored(this.homeTeam, this.awayTeam, outcome);
            }
            case AWAY_WIN -> {
                calculateGoalsScored(this.awayTeam, this.homeTeam, outcome);
            }
            default -> {
                calculateDrawGoals();
            }
        }
        System.out.println(
                homeTeam.teamName + ": " + homeTeamGoals + awayTeam.teamName + ": " + awayTeamGoals
        );
    }

    private void calculateDrawGoals(){
        byte randomNumber = (byte) (Math.random()*100);
        if (randomNumber < 30) {
            homeTeamGoals = 0;
            awayTeamGoals = 0;
        } else if (randomNumber < 50){
            homeTeamGoals= 1;
            awayTeamGoals= 1;
        } else if (randomNumber < 70){
            homeTeamGoals = 2;
            awayTeamGoals = 2;
        } else if (randomNumber < 80){
            homeTeamGoals = 3;
            awayTeamGoals = 3;
        } else if (randomNumber < 90){
            homeTeamGoals = 4;
            awayTeamGoals = 4;
        } else {
            homeTeamGoals = 5;
            awayTeamGoals = 5;
        }
    }

    private void calculateGoalsScored(Team winningTeam, Team losingTeam, MatchOutcomes outcome){
        byte randomNumber = (byte) (Math.random()*100);
        byte powerDifference = (byte) Math.abs(winningTeam.offensivePower - losingTeam.defensivePower);
        int goalCriteria = (randomNumber * powerDifference);

        byte winningTeamGoal = 0;
        byte losingTeamGoal = 0;

        if (goalCriteria < 3000){
            winningTeamGoal = (byte) (1 + Math.round(goalCriteria/120));
        } else if (goalCriteria > 3000){
            winningTeamGoal = (byte) (5 + Math.random()*6);
        }

        if (winningTeamGoal > 1){
            losingTeamGoal = (byte) ((winningTeamGoal - 1) * Math.random());
        }

        if (outcome == MatchOutcomes.HOME_WIN){
            homeTeamGoals = winningTeamGoal;
            awayTeamGoals = losingTeamGoal;
        } else {
            awayTeamGoals = winningTeamGoal;
            homeTeamGoals = losingTeamGoal;
        }

    }




}
