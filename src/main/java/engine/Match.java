package engine;

import constants.MatchConstants;
import enums.MatchOutcomes;
import objects.Team;

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
        int highPowerThreshold = MatchConstants.calculateWinThreshold(drawThreshold, winOrLoseThreshold);

        System.out.println("Draw threshold:" + drawThreshold);
        System.out.println("Win/Lose threshold:" + winOrLoseThreshold);
        System.out.println("Win threshold:" + highPowerThreshold);

        if (randomNumber < drawThreshold) {
            return MatchOutcomes.DRAW;
        }

        boolean isHigherPowerWinsAndHomeTeamHasHigherPower = randomNumber < highPowerThreshold && homeTeam.power >= awayTeam.power;
        boolean isHigherPowerLosesAndHomeTeamHasLowerPower = randomNumber > highPowerThreshold && homeTeam.power < awayTeam.power;

        if (isHigherPowerWinsAndHomeTeamHasHigherPower || isHigherPowerLosesAndHomeTeamHasLowerPower) {
            return MatchOutcomes.HOME_WIN;
        }

        return MatchOutcomes.AWAY_WIN;
    }


}
