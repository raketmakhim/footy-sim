package engine;

import enums.MatchOutcomes;
import objects.Team;

import static service.GoalsCalculator.calculateGoalScored;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private byte homeTeamGoals;
    private byte awayTeamGoals;
    private MatchOutcomesGenerator matchOutcomesGenerator;

    public Match(Team homeTeam, Team awayTeam, MatchOutcomesGenerator matchOutcomesGenerator){
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
        this.matchOutcomesGenerator = matchOutcomesGenerator;
    }

    public Match(){
    }

    public MatchOutcomes getMatchOutcome() {
        MatchOutcomes outcome = matchOutcomesGenerator.determineOutcome(this.homeTeam, this.awayTeam);
        calculateGoalScored(this, outcome);
        addGoalsToTeams(homeTeam, homeTeamGoals, awayTeamGoals);
        addGoalsToTeams(awayTeam, awayTeamGoals, homeTeamGoals);
        return outcome;
    }

    private void addGoalsToTeams(Team team, byte goalsScored, byte goalsConceded){
        team.addGoalsScored(goalsScored);
        team.addGoalsConceded(goalsConceded);
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public byte getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(byte homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public byte getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(byte awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }
}
