package engine;

import enums.MatchOutcomes;
import objects.Team;
import probability.ProbabilityCalculator;
import utils.RandomNumberGenerator;

public class MatchOutcomesGenerator {

    private final ProbabilityCalculator probabilityCalculator;
    private final RandomNumberGenerator rng;

    public MatchOutcomesGenerator(ProbabilityCalculator probabilityCalculator, RandomNumberGenerator rng) {
        this.probabilityCalculator = probabilityCalculator;
        this.rng = rng;
    }

    public MatchOutcomes determineOutcome(Team homeTeam, Team awayTeam) {
        int randomNumber = rng.generate();

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
