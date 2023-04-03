import java.util.Arrays;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] pascal = {1};
        for (int i = 0; i < n; i++) {
            int[] curRow = new int[pascal.length + 1];
            curRow[0] = 1;
            curRow[curRow.length - 1] = 1;
            for (int j = 0; j <  pascal.length - 1; j++) {
                curRow[j + 1] = pascal[j] + pascal[j + 1];
            }
            pascal = curRow;
        }
        print(pascal);
    }
    static void print(int[] arr) {
        Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
