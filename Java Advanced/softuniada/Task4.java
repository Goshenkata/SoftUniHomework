import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Arrays.stream(scanner.nextLine()
                        .split(" "))
                .sorted((s1, s2) -> {
                    if (s1.equals(s2)) {
                        return 0;
                    }
                    int minLenght = Math.min(s1.length(), s2.length());
                    for (int i = 0; i < minLenght; i++) {
                        int compare = Character.compare(s2.charAt(i), s1.charAt(i));
                        if (compare != 0) {
                            return compare;
                        }
                    }
                    return 0;
                }).collect(Collectors.joining("")));
    }
}
