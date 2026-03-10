package service;

import engine.Match;
import enums.MatchOutcomes;
import objects.Team;
import utils.RandomNumberGenerator;

public class GoalsCalculator {

    private final RandomNumberGenerator rng;

    public GoalsCalculator(RandomNumberGenerator rng) {
        this.rng = rng;
    }

    public void calculateGoalScored(Match match, MatchOutcomes outcome){
        switch (outcome) {
            case HOME_WIN -> {
                calculateGoalsScored(match, match.getHomeTeam(), match.getAwayTeam(), outcome);
            }
            case AWAY_WIN -> {
                calculateGoalsScored(match, match.getAwayTeam(), match.getHomeTeam(), outcome);
            }
            default -> {
                calculateDrawGoals(match);
            }
        }

    }

    private void calculateDrawGoals(Match match){
        int randomNumber = rng.generate();
        if (randomNumber < 30) {
            setTeamGoals(match, (byte) 0, (byte) 0);
        } else if (randomNumber < 50){
            setTeamGoals(match, (byte) 1, (byte) 1);
        } else if (randomNumber < 75){
            setTeamGoals(match, (byte) 2, (byte) 2);
        } else if (randomNumber < 85){
            setTeamGoals(match, (byte) 3, (byte) 3);
        } else if (randomNumber < 95){
            setTeamGoals(match, (byte) 4, (byte) 4);
        } else {
            setTeamGoals(match, (byte) 5, (byte) 5);
        }
    }

    private void calculateGoalsScored(Match match, Team winningTeam, Team losingTeam, MatchOutcomes outcome){
        int randomNumber = rng.generate();
        byte powerDifference = (byte) Math.abs(winningTeam.getOffensivePower() - losingTeam.getDefensivePower());
        int goalCriteria = (randomNumber * powerDifference);

        byte winningTeamGoal = 0;
        byte losingTeamGoal = 0;

        if (goalCriteria < 4000){
            winningTeamGoal = (byte) (1 + Math.round(goalCriteria / 300.0));
        } else {
            winningTeamGoal = (byte) (3 + rng.generate() * 6 / 100);
        }

        if (winningTeamGoal > 1){
            losingTeamGoal = (byte) ((winningTeamGoal - 1) * rng.generate() / 100.0);
        }

        if (outcome == MatchOutcomes.HOME_WIN){
            setTeamGoals(match, winningTeamGoal, losingTeamGoal);
        } else {
            setTeamGoals(match, losingTeamGoal, winningTeamGoal);
        }
    }

    private void setTeamGoals(Match match, byte homeGoals, byte awayGoals){
        match.setHomeTeamGoals(homeGoals);
        match.setAwayTeamGoals(awayGoals);
    }
}
