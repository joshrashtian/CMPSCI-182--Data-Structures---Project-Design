package Proj1;

import Universal.Log;

public class TestAppointment {
    public static void main(String[] args) {
        Appointment Test = Appointment.createAppointment();

        Test.setDay(31);

        Log.num(Test.getHour());

        Log.log(Test.toString());
    }
}
