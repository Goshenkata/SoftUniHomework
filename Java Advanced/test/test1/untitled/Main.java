import java.util.*;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> food = new Stack<>();
        Queue<Integer> stamina = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        String[] dataFood = scanner.nextLine().split(", ");
        String[] dataStamina = scanner.nextLine().split(", ");
        for (int i = 0; i < 7; i++) {
            food.push(Integer.parseInt(dataFood[i]));
            stamina.add(Integer.parseInt(dataStamina[i]));
        }
        int[] peakHeights = {80,90,100,60,70};
        int peakNum = 0;
        while (!food.empty() && !stamina.isEmpty() && peakNum < 5) {
            int sum = food.pop() + stamina.remove();
            if (sum >= peakHeights[peakNum]) {
                peakNum++;
            }
        }
        if (peakNum == 5) {
            System.out.println("Alex did it! He climbed all top five Pirin peaks in one week -> @FIVEinAWEEK");
            System.out.println("Conquered peaks:");
            System.out.println("Vihren\n" +
                    "Kutelo\n" +
                    "Banski Suhodol\n" +
                    "Polezhan\n" +
                    "Kamenitza");
        } else {
            System.out.println("Alex failed! He has to organize his journey better next time -> @PIRINWINS");
        }
    }
}
