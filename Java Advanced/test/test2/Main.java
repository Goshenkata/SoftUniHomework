import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] mgOfCaffData = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int[] energyDrinksData = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int caffeineTaken = 0;
        Stack<Integer> mgOfCaff = new Stack<>();
        Queue<Integer> energyDrinks = new ArrayDeque<>();
        for (int mg : mgOfCaffData) {
            mgOfCaff.push(mg);
        }
        for (int ed : energyDrinksData) {
            energyDrinks.add(ed);
        }
        while (!energyDrinks.isEmpty() && !mgOfCaff.empty()) {
            int caffeine = energyDrinks.peek() * mgOfCaff.peek();
            if (caffeineTaken + caffeine <= 300) {
                caffeineTaken += caffeine;
                energyDrinks.poll();
                mgOfCaff.pop();
            } else {
                energyDrinks.add(energyDrinks.poll());
                mgOfCaff.pop();
                caffeineTaken -= 30;
                if (caffeineTaken < 0) {
                    caffeineTaken = 0;
                }
            }
        }
        if (energyDrinks.isEmpty()) {
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        } else {
            System.out.printf("Drinks left: %s%n", energyDrinks.stream().map(i -> i + "").collect(Collectors.joining(", ")));
        }
        System.out.printf("Stamat is going to sleep with %d mg caffeine.", caffeineTaken);
    }
}
