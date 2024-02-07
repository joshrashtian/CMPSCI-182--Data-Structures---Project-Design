public class Appointment {
    private String month;
    private int day;
    private int hour;
    private String message;
    private int minute;

    Appointment () {

    }

    Appointment (int newDay, int newHour, String newMsg, String newMonth, int newMin) {
        this.day = newDay;
        this.hour = newHour;
        this.message = newMsg;
        if(newMonth.length() > 2) { System.out.println(Colors.ORANGE() + "WARN " + Colors.RESET() + "Months have a max length of 3. The month has been adapted to 3 characters."); }
        this.month = newMonth.substring(0, 2);
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

    public void setMinute(int min)  {
        this.minute = min;
        System.out.println("Minute Set!");
    }

    public void setMessage (String msg) {
        this.message = msg;
        System.out.println("Message Set!");
    }

    public void setMonth(String month) {
        if(newMonth.length() > 2) { System.out.println(Colors.ORANGE() + "WARN " + Colors.RESET() + "Months have a max length of 3. The month has been adapted to 3 characters."); }
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


}