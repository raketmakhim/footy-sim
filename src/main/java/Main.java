import engine.Match;
import engine.MatchOutcomesGenerator;
import enums.MatchOutcomes;
import objects.League;
import objects.Table;
import objects.Team;
import probability.LeagueMatchProbabilityCalculator;
import repository.TeamRepository;
import service.GoalsCalculator;
import utils.RandomNumberGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        playPremierLeague();
    }

    private static void playGames(int games){
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        homeTeam.setTeamName("Home Team");
        awayTeam.setTeamName("Away Team");

        homeTeam.setPower((byte) 90);
        awayTeam.setPower((byte) 90);

        RandomNumberGenerator rng = new RandomNumberGenerator();
        LeagueMatchProbabilityCalculator probabilityCalculator = new LeagueMatchProbabilityCalculator();
        MatchOutcomesGenerator outcomesGenerator = new MatchOutcomesGenerator(probabilityCalculator, rng);
        GoalsCalculator goalsCalculator = new GoalsCalculator(rng);
        Match derbyDay = new Match(homeTeam, awayTeam, outcomesGenerator, goalsCalculator);

        int homeWin = 0, awayWin = 0, draw = 0;

        while (games > 1){
            MatchOutcomes result = derbyDay.getMatchOutcome();
            System.out.println(result);
            if (result == MatchOutcomes.DRAW) {
                draw++;
            } else if (result == MatchOutcomes.HOME_WIN){
                homeWin++;
            } else if (result == MatchOutcomes.AWAY_WIN){
                awayWin++;
            }
            games--;
        }

        System.out.println("Home win: " + homeWin + " Away win: " + awayWin + " Draw: " + draw);
    }

    private static void playPremierLeague() {
        List<Team> teams = new TeamRepository().loadTeams();

        League premierLeague = new League();
        teams.forEach(premierLeague::addTeam);

        premierLeague.playLeague();
        Table.displayTable(premierLeague);
    }
}
