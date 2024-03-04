package Proj2;

public class MinChar {
    public static char minChar(String str) {

        char min = str.charAt(0);

        for (int i = 0; i < str.length(); i++) {
            if ((int) str.charAt(i) < (int) min) {
                min = str.charAt(i);
            }
        }
        return min;
    }
}
