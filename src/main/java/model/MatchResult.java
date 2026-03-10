package model;

import enums.MatchOutcomes;

public class MatchResult {
    private final String homeTeamName;
    private final String awayTeamName;
    private final int homeGoals;
    private final int awayGoals;
    private final MatchOutcomes outcome;

    public MatchResult(String homeTeamName, String awayTeamName,
                       int homeGoals, int awayGoals, MatchOutcomes outcome) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.outcome = outcome;
    }

    public String getHomeTeamName() { return homeTeamName; }
    public String getAwayTeamName() { return awayTeamName; }
    public int getHomeGoals()       { return homeGoals; }
    public int getAwayGoals()       { return awayGoals; }
    public MatchOutcomes getOutcome() { return outcome; }

    public String toDisplayString() {
        return String.format("%-22s %d - %d  %s", homeTeamName, homeGoals, awayGoals, awayTeamName);
    }
}
