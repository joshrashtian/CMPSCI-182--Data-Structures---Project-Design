package Proj1;

public class Main {
    public static void main(String[] args) {

        Appointment[] sample = {
                new Appointment(4, 17, "Quiz 1", "Mar", 30),
                new Appointment(1, 17, "Midterm", "Apr", 30),
                new Appointment(6, 17, "Quiz 2", "May", 30),
                new Appointment(3, 17, "Final", "June", 30),
        };

        Planner Calendar = new Planner(sample);

        Calendar.run();
    }
}
