package Universal;

import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);

    public int getInt(int min, int max) {
        int value;

        while (true) {
            value = scanner.nextInt();
            if (value < min || value > max) {
                System.out.println(Colors.ORANGE() + "Please enter a number between " + min + " and " + max);
            } else {

                return value;
            }
        }
    }

    public char getChar(char min, char max) {
        char input = scanner.next().charAt(0);

        return input;
    }

    public double getDouble(double min, double max) {
        double value;

        while (true) {
            value = scanner.nextDouble();
            if (value < min || value > max) {
                System.out.println(Colors.ORANGE() + "Please enter a number between " + min + " and " + max);
            } else {

                return value;
            }
        }
    }

    public String getString(int lengthMin, int lengthMax) {
        String value;

        while (true) {
            value = scanner.next();
            if (value.length() < lengthMin || value.length() > lengthMax) {
                System.out
                        .println(Colors.ORANGE() + "Please enter a string between " + lengthMin + " and " + lengthMax);
            } else {

                return value;
            }
        }
    }
}