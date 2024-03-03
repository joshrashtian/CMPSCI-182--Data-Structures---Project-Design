package Proj2;

import Universal.Log;
import Universal.UserInput;

public class Main {
    public static void main(String[] args) {
        UserInput input = new UserInput();
        Log.log("What two numbers would you like to get the difference of?");
        int firstNumber = input.getInt(0, 100);
        int secondNumber = input.getInt(0, 100);

        Log.log(ExampleSum.sumInt(firstNumber, secondNumber));
    }

}
