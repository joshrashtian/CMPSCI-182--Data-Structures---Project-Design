package Proj1;

public class TestAppointment {
    public static void main(String[] args) {
        Appointment Test = new Appointment(30, 4, "Big Day for Science Class", "March", 30);
        Log.log(Test.toString());

        Test.setDay(31);

        Log.num(Test.getHour());
    }
}
