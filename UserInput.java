import java.util.Scanner;

import Proj1.Colors;

public class UserInput {
    public static int getInt(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int value;

        while (true) {
            value = scanner.nextInt();
            if (value < min || value > max) {
                System.out.println(Colors.ORANGE() + "Please enter a number between " + min + " and " + max);
            } else {
                scanner.close();
                return value;
            }
        }
    }

    public static char getChar(char min, char max) {
        Scanner scanner = new Scanner(System.in);
        char input = scanner.next().charAt(0);
        scanner.close();
        return input;
    }

    public static double getDouble(double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;

        while (true) {
            value = scanner.nextDouble();
            if (value < min || value > max) {
                System.out.println(Colors.ORANGE() + "Please enter a number between " + min + " and " + max);
            } else {
                scanner.close();
                return value;
            }
        }
    }

    public static String getString(int lengthMin, int lengthMax) {
        Scanner scanner = new Scanner(System.in);
        String value;

        while (true) {
            value = scanner.next();
            if (value.length() < lengthMin || value.length() > lengthMax) {
                System.out
                        .println(Colors.ORANGE() + "Please enter a string between " + lengthMin + " and " + lengthMax);
            } else {
                scanner.close();
                return value;
            }
        }
    }
}