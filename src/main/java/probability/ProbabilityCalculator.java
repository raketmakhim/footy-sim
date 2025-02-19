package probability;

public interface ProbabilityCalculator {
    byte calculateDrawThreshold(byte power1, byte power2);
    byte calculateWinOrLoseThreshold(byte power1, byte power2);
    int calculateWinThreshold(byte drawThreshold, byte winOrLoseThreshold);
}
