package Proj1;

import Universal.Colors;
import Universal.Log;
import Universal.UserInput;

public class Planner {
    private Appointment[] calendar = new Appointment[20];
    int numElements = 0;
    UserInput input = new UserInput();

    Planner() {
    }

    Planner(Appointment first) {
        this.calendar[0] = first;
    }

    Planner(Appointment[] a) {
        for (int i = 0; i < a.length; i++) {
            insertAppointment(a[i]);
        }
        numElements = a.length;
    }

    // make a function that converst month in string form to int using a if
    // statment.

    // make a funciton that converst dates to numbers

    public int getMonth(Appointment a) {
        String[] months = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };

        int month = 0;
        for (int i = 0; i < months.length; i++) {
            if (a.getMonth().toUpperCase().equals(months[i].toUpperCase())) {
                month = i;
            }
        }
        return month;
    }

    public boolean compareAppointment(Appointment c, Appointment a) {

        int cNum = c.getMinute() + c.getHour() * 60 + (c.getDay() * 24 * 60) + (this.getMonth(c) * 24 * 60 * 31);
        int aNum = a.getMinute() + a.getHour() * 60 + (a.getDay() * 24 * 60) + (this.getMonth(a) * 24 * 60 * 31);

        if (cNum > aNum) {
            return false;
        }

        return true;
        /* 0 is A is bigger, 1 is B is bigger. */
    }

    private void insertAppointment(Appointment a) {

        for (int i = 0; i < this.calendar.length; i++) {
            if (this.calendar[i] == null) {
                this.calendar[i] = a;
                numElements++;
                return;
            }
            if (compareAppointment(this.calendar[i], a) == false) {
                for (int j = numElements; j >= i; j--) {
                    Log.log("log this " + i + " " + j);
                    this.calendar[j + 1] = this.calendar[j];
                }
                this.calendar[i] = a;
                numElements++;
                return;
            }
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
                numElements--;
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
