package probability;

import utils.PowerUtils;

public class LeagueMatchProbabilityCalculator implements ProbabilityCalculator {

    @Override
    public byte calculateDrawThreshold(byte power1, byte power2) {
        byte absPower = PowerUtils.getAbsolutePower(power1, power2);
        return (absPower < 42) ? (byte) (25 - 0.5 * absPower) : 4;
    }

    @Override
    public byte calculateWinOrLoseThreshold(byte power1, byte power2) {
        byte absPower = PowerUtils.getAbsolutePower(power1, power2);

        if (absPower <= 5) {
            return (byte) (4 * absPower + 50);
        } else if (absPower <= 42) {
            return (byte) (absPower + 70);
        }
        return 98;
    }

    @Override
    public int calculateWinThreshold(byte drawThreshold, byte winOrLoseThreshold) {
        return drawThreshold + (100 - drawThreshold) * winOrLoseThreshold / 100;
    }
}
