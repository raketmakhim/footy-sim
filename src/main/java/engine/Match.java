package engine;

import constants.MatchConstants;
import objects.MatchOutcomes;
import objects.Player;
import objects.Team;

import java.util.stream.Collectors;

public class Match {
    public Team homeTeam;
    public Team awayTeam;

    public Match(Team homeTeam, Team awayTeam){
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
    }

    public Match(){
    }

    public byte getDrawThreshold(){
        return MatchConstants.calculateDrawThreshold(homeTeam.power, awayTeam.power);
    }

    public byte getWinOrLoseThreshold(){

        System.out.println("Home Team: " + homeTeam.teamName + ", Power: " + homeTeam.power);
        System.out.println("Away Team: " + awayTeam.teamName + ", Power: " + awayTeam.power);


        return MatchConstants.calculateWinOrLoseThreshold(homeTeam.power, awayTeam.power);
    }

    public MatchOutcomes getMatchOutcome(byte randomNumber) {
        System.out.println("Random Number: " + randomNumber);

        byte drawThreshold = getDrawThreshold();
        byte winOrLoseThreshold = getWinOrLoseThreshold();
        int winThreshold = MatchConstants.calculateWinThreshold(drawThreshold, winOrLoseThreshold);

        System.out.println("Draw threshold:" + drawThreshold);
        System.out.println("Win/Lose threshold:" + winOrLoseThreshold);
        System.out.println("Win threshold:" + winThreshold);

        if (randomNumber < drawThreshold) {
            return MatchOutcomes.DRAW;
        }

        if (randomNumber < winThreshold){
            if (homeTeam.power >= awayTeam.power){
                return MatchOutcomes.HOME_WIN;
            }
        } else {
            if (homeTeam.power < awayTeam.power){
                return MatchOutcomes.HOME_WIN;
            }
        }

        return MatchOutcomes.AWAY_WIN;
    }
}
