package constants;

public class MatchConstants {


    public static byte calculateDrawThreshold(byte power1, byte power2){

        byte absPower = (byte) Math.abs(power1 - power2);

        if (absPower < 42) {
            return (byte) (25-0.5*absPower);
        }

        return 4;
    }

    public static byte calculateWinOrLoseThreshold(byte power1, byte power2){

        //Power difference of the two teams (only want positive value)
        byte absPower = (byte) Math.abs(power1-power2);

        if (absPower <= 5){
            //if difference is <= 5: P(Win) = 2*P+50
            return (byte) (4*absPower + 50);
        } else if (absPower <= 42) {
            //if difference is > 5 and <= 24: P(Win) = P+50
            return (byte) (absPower + 70);
        }

        //Other wise P(Win) = 97
        return 98;
    }

    public static int calculateWinThreshold(byte drawThreshold, byte winOrLoseThreshold){
        return drawThreshold + (100-drawThreshold)*winOrLoseThreshold/100;
    }
}
