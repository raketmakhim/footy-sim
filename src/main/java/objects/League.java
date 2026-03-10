package objects;

import engine.Match;
import engine.MatchOutcomesGenerator;
import enums.MatchOutcomes;
import probability.LeagueMatchProbabilityCalculator;
import service.GoalsCalculator;
import utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class League {
    private final List<Team> teams;

    private Match leagueMatch;
    private final MatchOutcomesGenerator leagueMatchOutcomeGenerator;
    private final GoalsCalculator goalsCalculator;

    public League() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        this.leagueMatchOutcomeGenerator = new MatchOutcomesGenerator(new LeagueMatchProbabilityCalculator(), rng);
        this.goalsCalculator = new GoalsCalculator(rng);
        this.teams = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void playLeague(){
        for (int i = 0; i < teams.size()-1; i++){
            for (int j = i+1; j < teams.size(); j++){
                playLeagueMatch(teams.get(i), teams.get(j));
                playLeagueMatch(teams.get(j), teams.get(i));
            }
        }
    }

    public void playLeagueMatch(Team homeTeam, Team awayTeam){
        leagueMatch = new Match(homeTeam, awayTeam, leagueMatchOutcomeGenerator, goalsCalculator);
        leagueMatch.setHomeTeam(homeTeam);
        leagueMatch.setAwayTeam(awayTeam);
        MatchOutcomes result = leagueMatch.getMatchOutcome();

        switch (result) {
            case DRAW -> {
                homeTeam.recordDraw();
                awayTeam.recordDraw();
            }
            case HOME_WIN -> {
                homeTeam.recordWin();
                awayTeam.recordLoss();
            }
            case AWAY_WIN -> {
                homeTeam.recordLoss();
                awayTeam.recordWin();
            }
        }
    }

    public List<Team> getTeams() {
        return teams;
    }
}
