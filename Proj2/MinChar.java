package Proj2;

public class MinChar {
    public static char minChar(String str) {

        char min = getMin(str, str.charAt(0), 0);

        return min;
    }

    private static char getMin(String min, char currentMin, int index) {
        if (index < min.length()) {
            if ((int) min.charAt(index) < (int) currentMin) {
                return getMin(min, min.charAt(index), index + 1);
            } else {
                return getMin(min, currentMin, index + 1);
            }
        } else {
            return currentMin;
        }
    }
}
