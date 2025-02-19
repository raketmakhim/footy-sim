package utils;

public class RandomNumberGenerator {
    public static byte generateRandomNumber() {
        return (byte) (Math.random() * 100);
    }
}
