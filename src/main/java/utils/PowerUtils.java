package utils;

public class PowerUtils {
    public static byte getAbsolutePower(byte power1, byte power2){
        return (byte) Math.abs(power1 - power2);
    }
}
