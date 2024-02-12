package Proj1;

import java.util.Scanner;

import Universal.*;

public class Appointment {
    private String month;
    private int day;
    private int hour;
    private String message;
    private int minute;

    Appointment() {

    }

    Appointment(int newDay, int newHour, String newMsg, String newMonth, int newMin) {
        this.day = newDay;
        this.hour = newHour;
        this.message = newMsg;
        if (newMonth.length() > 3) {
            System.out.println(Colors.ORANGE() + "WARN " + Colors.RESET()
                    + "Months have a max length of 3. The month has been adapted to 3 characters.");
        }
        this.month = newMonth.substring(0, 3);
        this.minute = newMin;
    }

    public int getHour() {
        return hour;
    }

    public String getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public String getMessage() {
        return this.message;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int min) {
        this.minute = min;
        System.out.println("Minute Set!");
    }

    public void setMessage(String msg) {
        this.message = msg;
        System.out.println("Message Set!");
    }

    public void setMonth(String newMonth) {
        if (newMonth.length() > 2) {
            System.out.println(Colors.ORANGE() + "WARN " + Colors.RESET()
                    + "Months have a max length of 3. The month has been adapted to 3 characters.");
        }
        this.month = month.substring(0, 3);
        System.out.println("Month Set!");
    }

    public void setDay(int newDay) {
        this.day = newDay;
        System.out.println("Day Set!");
    }

    public void setHour(int newHour) {
        this.hour = newHour;
        System.out.println("Hour Set!");
    }

    public String toString() {
        return Colors.ORANGE() + this.month.toUpperCase() + " " + this.day + " | " + this.hour + ":" + this.minute
                + " | " + Colors.RESET() + this.message;
    }

    public static Appointment createAppointment() {
        UserInput userInput = new UserInput();
        System.out.println(Colors.ORANGE() + "Enter Month: " + Colors.RESET());
        String month = userInput.getString(0, 3);
        System.out.println(Colors.ORANGE() + "Enter Day: " + Colors.RESET());
        int day = userInput.getInt(0, 31);
        System.out.println(Colors.ORANGE() + "Enter Hour: " + Colors.RESET());
        int hour = userInput.getInt(0, 23);
        System.out.println(Colors.ORANGE() + "Enter Minute: " + Colors.RESET());
        int minute = userInput.getInt(0, 59);
        System.out.println(Colors.ORANGE() + "Enter Message: " + Colors.RESET());
        String message = userInput.getString(0, 40);
        return new Appointment(day, hour, message, month, minute);
    }
}