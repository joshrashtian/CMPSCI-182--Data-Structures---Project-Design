package Universal;

public class Log {
    public static void log(Object message) {
        System.out.println(message);
    }

    public static void log(Object message, String color) {
        System.out.println(color + message + Colors.RESET());
    }

    public static void error(Object message) {
        System.out.println(Colors.RED() + message + Colors.RESET());
    }
}
