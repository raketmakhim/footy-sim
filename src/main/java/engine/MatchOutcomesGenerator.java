package engine;

import enums.MatchOutcomes;
import objects.Team;
import probability.ProbabilityCalculator;
import utils.RandomNumberGenerator;

public class MatchOutcomesGenerator {

    private final ProbabilityCalculator probabilityCalculator;

    public MatchOutcomesGenerator(ProbabilityCalculator probabilityCalculator) {
        this.probabilityCalculator = probabilityCalculator;
    }

    public MatchOutcomes determineOutcome(Team homeTeam, Team awayTeam) {
        byte randomNumber = RandomNumberGenerator.generateRandomNumber();

        byte drawThreshold = probabilityCalculator.calculateDrawThreshold(homeTeam.getPower(), awayTeam.getPower());
        byte winThreshold = probabilityCalculator.calculateWinOrLoseThreshold(homeTeam.getPower(), awayTeam.getPower());
        int highPowerThreshold = probabilityCalculator.calculateWinThreshold(drawThreshold, winThreshold);

        if (randomNumber < drawThreshold) {
            return MatchOutcomes.DRAW;
        }

        boolean isHigherPowerWinsAndHomeTeamHasHigherPower = randomNumber < highPowerThreshold && homeTeam.getPower() >= awayTeam.getPower();
        boolean isHigherPowerLosesAndHomeTeamHasLowerPower = randomNumber > highPowerThreshold && homeTeam.getPower() < awayTeam.getPower();

        if (isHigherPowerWinsAndHomeTeamHasHigherPower || isHigherPowerLosesAndHomeTeamHasLowerPower) {
            return MatchOutcomes.HOME_WIN;
        }

        return MatchOutcomes.AWAY_WIN;
    }
}
