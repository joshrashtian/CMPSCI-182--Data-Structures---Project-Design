package Proj2;

public class JobEarnings {
    public static String CalcJobEarnings(double value, int days) {
        double result = RecurseTotal(value, days);
        return String.format("%.2f", result);
    }

    private static double RecurseTotal(double value, int day) {
        if (day > 0) {
            return RecurseTotal(value * 2, day - 1);
        } else {
            return value;
        }
    }
}
