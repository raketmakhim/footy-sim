package constants;

public class MatchConstants {


    public static byte calculateDrawThreshold(byte power1, byte power2){
        return (byte) (25-0.5*Math.abs(power1 - power2));
    }

    public static byte calculateWinOrLoseThreshold(byte power1, byte power2){

        byte absPower = (byte) Math.abs(power1-power2);

        if (absPower <= 5){
            return (byte) (2*absPower + 50);
        } else if (absPower <= 42) {
            return (byte) (absPower + 55);
        }

        return 97;
    }

    public static int calculateWinThreshold(byte drawThreshold, byte winOrLoseThreshold){
        return drawThreshold + (100-drawThreshold)*winOrLoseThreshold/100;
    }
}
