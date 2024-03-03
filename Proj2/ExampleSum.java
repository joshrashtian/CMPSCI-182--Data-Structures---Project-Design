package Proj2;

public class ExampleSum {
    public static int sumInt(int start, int end) {
        /*
         * Start: 2, End: 5
         * 5 + 4 + 3 + 2
         * Expected Result: 14
         */
        if (end > start) {
            return (end + (sumInt(start, end - 1)));
        } else {
            return end;
        }
    }
}
