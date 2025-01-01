package objects;

import engine.Match;
import enums.MatchOutcomes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class League {
    private final List<Team> teams;

    private Match leagueMatch;

    public League() {
        teams = new ArrayList<>();
    }

    public void addTeam(Team team, String name, byte power, byte offensivePower, byte defensivePower) {
        team.teamName = name;
        team.power = power;
        team.offensivePower = offensivePower;
        team.defensivePower = defensivePower;
        teams.add(team);
    }

    public void playLeague(){
        leagueMatch = new Match();
        for (int i = 0; i < teams.size()-1; i++){
            for (int j = i+1; j < teams.size(); j++){
                playLeagueMatch(teams.get(i), teams.get(j));
                playLeagueMatch(teams.get(j), teams.get(i));
            }
        }
    }

    public void playLeagueMatch(Team homeTeam, Team awayTeam){
        leagueMatch.homeTeam = homeTeam;
        leagueMatch.awayTeam = awayTeam;
        MatchOutcomes result = leagueMatch.getMatchOutcome();

        switch (result) {
            case DRAW -> {
                homeTeam.draw();
                awayTeam.draw();
            }
            case HOME_WIN -> {
                homeTeam.win();
                awayTeam.loss();
            }
            case AWAY_WIN -> {
                homeTeam.loss();
                awayTeam.win();
            }
        }
    }

    public void sortTeams() {
        teams.sort(Comparator
                .comparingInt(Team::getPoints).reversed()
        );
    }

    public void displayTable() {
        sortTeams();

        // Print header with proper spacing
        System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-10s %-10s%n", "Position", "Team", "Points", "Win", "Draw", "Loss", "Goals For", "Goals Against");

        // Print each team's data with proper formatting
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.printf("%-10d %-20s %-10d %-10d %-10d %-10d %-10d %-10d %n", i + 1, team.teamName, team.points, team.win, team.draw, team.lose, team.goalsScored, team.goalsConceded);
        }
    }
}
