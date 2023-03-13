import java.util.Locale;

public class SizeFormatter {

    private static String[] transitionArray = {"B", "KB", "MB", "GB", "TB", "PB"};

    public static void printBytes(Long bytes) {
        double formattedBytes = bytes;
        int transitionCounterFlag = 0;
        while (formattedBytes >= 1024) {
            transitionCounterFlag++;
            formattedBytes /= 1024;
        }
        System.out.println(String.format(Locale.US, "%.1f", (float) formattedBytes) + " " + transitionArray[transitionCounterFlag]);
    }
}
