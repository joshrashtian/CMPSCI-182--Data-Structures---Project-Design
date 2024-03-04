package Proj2;

import Universal.Colors;
import Universal.Log;
import Universal.UserInput;

public class Main {
    public static void main(String[] args) {
        UserInput input = new UserInput();
        Log.log(Colors.RESET() + "1 ) What two numbers would you like to get the difference of?" + Colors.ORANGE());
        int firstNumber = input.getInt(0, 100);
        int secondNumber = input.getInt(0, 100);

        Log.log(Colors.GREEN() + ExampleSum.sumInt(firstNumber, secondNumber));

        Log.log(Colors.RESET() + "2 ) Enter a string to get minimum value: ");
        String word = input.getString(0, 30);
        Log.log(Colors.GREEN() + MinChar.minChar(word));

        Log.log(Colors.RESET() + "3 ) Enter an amount of money + days to calcuate: ");
        double initial = input.getDouble(0, 500);
        int days = input.getInt(0, 365);
        Log.log(Colors.GREEN() + "$" + JobEarnings.CalcJobEarnings(initial, days));
    }
}
