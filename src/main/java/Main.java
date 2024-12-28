import engine.Match;
import objects.MatchOutcomes;
import objects.Player;
import objects.Position;
import objects.Team;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Team homeTeam = new Team();
        Team awayTeam = new Team();

        homeTeam.power = 90;
        awayTeam.power = 45;
        Match derbyDay = new Match(homeTeam, awayTeam);

        int games = 1000;

        int homeWin = 0, awayWin = 0, draw = 0;

        while (games > 1){
            MatchOutcomes result = derbyDay.getMatchOutcome((byte) (Math.random()*100));
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
}
