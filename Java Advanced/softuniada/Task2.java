import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        int cols = size + 5;
        int rows = cols + size;
        char[][] rocket = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rocket[i][j] = '_';
            }
        }
        int midPoint = cols / 2;
        int row = drawTop(rocket, midPoint);
        row = drawFairing(rocket, row, midPoint);
        int to = row + size;
        while (row < to ) {
            drawBody(rocket,row, midPoint);
            row++;
        }
        row = drawEng(rocket,row,midPoint);
        drawPlume(rocket, row, midPoint, rows);
        printRocket(rocket);
    }

    private static int drawPlume(char[][] rocket, int row, int midPoint, int rows) {
        int dots = 0;
        for (int i = row; i < rows ; i++) {
            rocket[row][midPoint] = '!';
            for (int j = 0; j < dots; j++) {
                rocket[row][midPoint - j - 1] = '.';
                rocket[row][midPoint + j + 1] = '.';
            }
            rocket[row][ midPoint - dots - 1] = '/';
            rocket[row][ midPoint + dots + 1] = '\\';
            rocket[row][ midPoint - dots - 2] = '/';
            rocket[row][ midPoint + dots + 2] = '\\';
            dots++;
            row++;
        }
        return row;
    }

    public static int drawFairing(char[][] rocket, int row, int midPoint) {
        int dots = 0;
        while (rocket[row - 1][0] != '/') {
            drawBody(rocket, row, midPoint);
            for (int j = 0; j < dots; j++) {
                rocket[row][midPoint - j - 2] = '.';
                rocket[row][midPoint + j + 2] = '.';
            }
            rocket[row][ midPoint - dots - 2] = '/';
            rocket[row][ midPoint + dots + 2] = '\\';
            dots++;
            row++;
        }
        dots-=2;
        drawBody(rocket, row, midPoint);
        for (int i = 0; i < dots; i++) {
            rocket[row][midPoint - i - 2] = '.';
            rocket[row][midPoint + i + 2] = '.';
        }
        rocket[row][ midPoint - dots - 2] = '/';
        rocket[row][ midPoint + dots + 2] = '\\';
        row++;
        return row;
    }

    public static int drawBody(char[][] rocket, int row, int midPoint) {
        rocket[row][midPoint] = '|';
        rocket[row][midPoint - 1] = '|';
        rocket[row][midPoint + 1] = '|';
        return row;
    }

    public static int drawEng(char[][] rocket, int row, int midPoint) {
        rocket[row][midPoint] = '~';
        rocket[row][midPoint - 1] = '~';
        rocket[row][midPoint + 1] = '~';
        return row + 1;
    }

    public static int drawTop(char[][] rocket, int midPoint) {
        rocket[0][midPoint] = '^';
        rocket[1][midPoint] = '|';
        rocket[1][midPoint-1] = '/';
        rocket[1][midPoint+1] = '\\';
        return 2;
    }

    public static void printRocket(char[][] rocket) {
        for (char[] chars : rocket) {
            StringBuilder row = new StringBuilder();
            for (char aChar : chars) {
                row.append(aChar);
            }
            System.out.println(row);
        }
    }
}
