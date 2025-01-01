import engine.Match;
import enums.MatchOutcomes;
import objects.League;
import objects.Team;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        playPremierLeague();

    }

    private static void playGames(int games){
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        homeTeam.power = 90;
        awayTeam.power = 90;
        Match derbyDay = new Match(homeTeam, awayTeam);

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

    private static void playPremierLeague(){
        League premierLeague = new League();

        premierLeague.addTeam(new Team(), "Manchester United", (byte) 80, (byte) 82, (byte) 75);
        premierLeague.addTeam(new Team(), "Liverpool", (byte) 90, (byte)90, (byte)90);
        premierLeague.addTeam(new Team(), "Manchester City", (byte) 91, (byte)92, (byte)90);
        premierLeague.addTeam(new Team(), "Arsenal", (byte) 87, (byte)86, (byte)88);
        premierLeague.addTeam(new Team(), "Chelsea", (byte) 86, (byte)84, (byte)88);
        premierLeague.addTeam(new Team(), "Nottingham Forest", (byte) 82, (byte)82, (byte)84);
        premierLeague.addTeam(new Team(), "Newcastle", (byte) 84, (byte)88, (byte)80);
        premierLeague.addTeam(new Team(), "Bournemouth", (byte) 81, (byte)80, (byte)82);
        premierLeague.addTeam(new Team(), "Fulham", (byte) 80, (byte)80, (byte)80);
        premierLeague.addTeam(new Team(), "Aston Villa", (byte) 79, (byte)77, (byte)78);
        premierLeague.addTeam(new Team(), "Brighton", (byte) 80, (byte)80, (byte)80);
        premierLeague.addTeam(new Team(), "Brentford", (byte) 78, (byte)77, (byte)80);
        premierLeague.addTeam(new Team(), "Tottneham", (byte) 79, (byte)79, (byte)80);
        premierLeague.addTeam(new Team(), "West Ham", (byte) 74, (byte)78, (byte)70);
        premierLeague.addTeam(new Team(), "Everton", (byte) 72, (byte)70, (byte) 77);
        premierLeague.addTeam(new Team(), "Crystal Palace", (byte) 70, (byte)70, (byte)70);
        premierLeague.addTeam(new Team(), "Wolves", (byte) 72, (byte)72, (byte)72);
        premierLeague.addTeam(new Team(), "Leicester City", (byte) 73, (byte)75, (byte)70);
        premierLeague.addTeam(new Team(), "Ipswich Town", (byte) 67, (byte)70, (byte)65);
        premierLeague.addTeam(new Team(), "Southampton", (byte) 65, (byte)67, (byte) 60);

        premierLeague.playLeague();

        premierLeague.displayTable();
    }
}
