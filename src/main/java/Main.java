import engine.Match;
import enums.MatchOutcomes;
import objects.League;
import objects.Team;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        Team homeTeam = new Team();
//        Team awayTeam = new Team();
//
//        homeTeam.power = 90;
//        awayTeam.power = 90;
//        Match derbyDay = new Match(homeTeam, awayTeam);
//
//        int games = 1000;
//
//        int homeWin = 0, awayWin = 0, draw = 0;
//
//        while (games > 1){
//            MatchOutcomes result = derbyDay.getMatchOutcome((byte) (Math.random()*100));
//            System.out.println(result);
//            if (result == MatchOutcomes.DRAW) {
//                draw++;
//            } else if (result == MatchOutcomes.HOME_WIN){
//                homeWin++;
//            } else if (result == MatchOutcomes.AWAY_WIN){
//                awayWin++;
//            }
//            games--;
//        }
//
//        System.out.println("Home win: " + homeWin + " Away win: " + awayWin + " Draw: " + draw);


        League premierLeague = new League();

        premierLeague.addTeam(new Team(), "Manchester United", (byte) 80);
        premierLeague.addTeam(new Team(), "Liverpool", (byte) 90);
        premierLeague.addTeam(new Team(), "Manchester City", (byte) 97);
        premierLeague.addTeam(new Team(), "Arsenal", (byte) 87);
        premierLeague.addTeam(new Team(), "Chelsea", (byte) 86);
        premierLeague.addTeam(new Team(), "Nottingham Forest", (byte) 82);
        premierLeague.addTeam(new Team(), "Newcastle", (byte) 84);
        premierLeague.addTeam(new Team(), "Bournemouth", (byte) 81);
        premierLeague.addTeam(new Team(), "Fulham", (byte) 80);
        premierLeague.addTeam(new Team(), "Aston Villa", (byte) 79);
        premierLeague.addTeam(new Team(), "Brighton", (byte) 80);
        premierLeague.addTeam(new Team(), "Brentford", (byte) 78);
        premierLeague.addTeam(new Team(), "Tottneham", (byte) 79);
        premierLeague.addTeam(new Team(), "West Ham", (byte) 74);
        premierLeague.addTeam(new Team(), "Everton", (byte) 72);
        premierLeague.addTeam(new Team(), "Crystal Palace", (byte) 70);
        premierLeague.addTeam(new Team(), "Wolves", (byte) 72);
        premierLeague.addTeam(new Team(), "Leicester City", (byte) 73);
        premierLeague.addTeam(new Team(), "Ipswich Town", (byte) 67);
        premierLeague.addTeam(new Team(), "Southampton", (byte) 65);

        premierLeague.playLeague();

        premierLeague.displayTable();

    }
}
