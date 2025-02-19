package objects;

import java.util.Comparator;
import java.util.List;

public class Table {

    public static void sortTeams(List<Team> teams) {
        teams.sort(Comparator
                .comparingInt(Team::getPoints).reversed()
        );
    }

    public static void displayTable(League league) {
        List<Team> teams = league.getTeams();
        sortTeams(teams);

        // Print header with proper spacing
        System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-10s %-10s%n", "Position", "Team", "Points", "Win", "Draw", "Loss", "Goals For", "Goals Against");

        // Print each team's data with proper formatting
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.printf("%-10d %-20s %-10d %-10d %-10d %-10d %-10d %-10d %n", i + 1, team.getTeamName(), team.getPoints(), team.getWins(), team.getDraws(), team.getLosses(), team.getGoalsScored(), team.getGoalsConceded());
        }
    }
}
