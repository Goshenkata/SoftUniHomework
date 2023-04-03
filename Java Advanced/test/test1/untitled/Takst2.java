import java.util.*;

public class Takst2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        char[][] battlefield = new char[n][n];
        int hitsTaken = 0;
        int cruisers = 0;
        int subRow = -1;
        int subCol = -1;
        for (int i = 0; i < n; i++) {
            char[] line = scanner.nextLine().toCharArray();
            for (int j = 0; j < n; j++) {
                battlefield[i][j] = line[j];
                if (line[j] == 'S') {
                    subRow = j;
                    subCol = i;
                }
            }
        }

        while (hitsTaken != 3 && cruisers != 3) {
            String direction = scanner.nextLine();
            int nextRow = subRow;
            int nextCol = subCol;
            switch (direction) {
                case "left":
                    nextCol--;
                    break;
                case "right":
                    nextCol++;
                    break;
                case "down":
                    nextRow++;
                    break;
                case "up":
                    nextRow--;
                    break;
            }
            char nextTile = battlefield[nextRow][nextCol];
            battlefield[subRow][subCol] = '-';
            battlefield[nextRow][nextCol] = 'S';
            subRow = nextRow;
            subCol = nextCol;
            switch (nextTile) {
                case '*':
                    hitsTaken++;
                    break;
                case 'C':
                    cruisers++;
                    break;
            }
        }
        if (cruisers== 3) {
            System.out.println("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!");
        } else {
            System.out.printf("Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!%n", subRow, subCol);
        }
        Print(battlefield);
    }

    static void Print(char[][] battlefield) {
        for (int i = 0; i < battlefield.length; i++) {
            for (int j = 0; j < battlefield.length; j++) {
                System.out.print(battlefield[i][j]);
            }
            System.out.println();
        }
    }
}