package logic;

public class PriceCalculator {

    static double exponent = 1.3;

    public static int calculate(int ownedNumber) {
        return (int)Math.round(Math.pow(ownedNumber, exponent));
    }

    public static int calculateNext(int ownedNumber) {
        return (int)Math.round(Math.pow(ownedNumber + 1, exponent));
    }
}
