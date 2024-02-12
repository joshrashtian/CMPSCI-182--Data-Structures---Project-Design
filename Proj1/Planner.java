package Proj1;

import java.util.Calendar;

import Universal.Colors;
import Universal.Log;
import Universal.UserInput;

public class Planner {
    private Appointment[] calendar = new Appointment[20];

    UserInput input = new UserInput();

    Planner() {
    }

    Planner(Appointment first) {
        this.calendar[0] = first;
    }

    Planner(Appointment[] calendar) {
        for (int i = 0; i < calendar.length; i++) {
            this.calendar[i] = calendar[i];
        }
    }

    public int compareAppointment(Appointment a, Appointment b) {
        int result;
        if (a.getDay() != b.getDay()) {
            if (a.getDay() > b.getDay()) {
                result = 0;
            } else {
                result = 1;
            }
        } else if (a.getHour() != b.getHour()) {
            if (a.getHour() > b.getHour()) {
                result = 0;
            } else {
                result = 1;
            }
        } else if (a.getMinute() != b.getMinute()) {
            if (a.getMinute() > b.getMinute()) {
                result = 0;
            } else {
                result = 1;
            }
        } else
            result = 2;
        /* 0 is A is bigger, 1 is B is bigger. */
        return result;
    }

    private void insertAppointment(Appointment a) {
        for (int i = 0; i < this.calendar.length; i++) {
            if (compareAppointment(a, calendar[i]) == 0) {
                Log.log("Here" + i);
                for (int j = i; j < this.calendar.length; j++) {
                    try {
                        calendar[j] = calendar[j + 1];
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
                calendar[i] = a;
                return;
            } else {
                calendar[i] = calendar[i];
            }
            ;
        }
    }

    private void addAppointment() {
        Appointment New = Appointment.createAppointment();
        insertAppointment(New);
    }

    private void deleteAppointment() {
        Log.log(Colors.RED() + "Please enter the index of the appointment you would like to delete.");
        Log.log(Colors.ORANGE() + "Use 0 to cancel.");
        for (int i = 0; i < this.calendar.length; i++) {
            Log.log(Colors.RED() + (i + 1) + " / " + calendar[i].toString());
        }
        while (true) {
            int selection = input.getInt(0, calendar.length);
            if (selection == 0) {
                return;
            }
        }
    }

    private void listAppointment() {
        for (int i = 0; i < this.calendar.length; i++) {
            if (calendar[i] != null) {
                Log.log(calendar[i].toString());
            }
        }
    }

    public void run() {
        while (true) {
            Log.log(Colors.GREEN() + "A)dd Appointment | D)elete Appointment | L)ist Appointment | E)xit"
                    + Colors.RESET());
            char selection = input.getChar(0, 1);

            switch (selection) {
                case 'A':
                    this.addAppointment();
                    break;
                case 'D':
                    this.deleteAppointment();
                    break;
                case 'L':
                    this.listAppointment();
                    break;
                case 'E':
                    System.exit(0);
                    break;
                default:
                    System.out.println(Colors.ORANGE() + "Please enter a valid selection." + Colors.RESET());
                    break;

            }
        }
    }
}
