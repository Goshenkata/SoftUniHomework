import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Pattern pattern = Pattern.compile("(\\(\\))+");
        Matcher matcher = pattern.matcher(s);
        int max = Integer.MIN_VALUE;
        while (matcher.find()) {
            String group = matcher.group();
            if (group.length() > max) {
                max = group.length();
            }
        }
        System.out.println(max);
    }
}
