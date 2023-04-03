import java.util.*;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> licensePlates = new ArrayDeque<>();
        Stack<Integer> cars = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        List<Integer> licensePlatesList = Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).toList();
        List<Integer> carsList = Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).toList();

        licensePlates.addAll(licensePlatesList);
        cars.addAll(carsList);

        int days = 0;
        int registered = 0;
        while (licensePlatesList.isEmpty() || carsList.isEmpty()) {
            int numCars = cars.pop();
            int numLicensePlates = licensePlates.poll();
            if (numLicensePlates * 2 == numCars) {
                registered = numCars;
            } else if (numLicensePlates * 2 > numCars) {
                int lpSurplus = numLicensePlates * 2 - numCars;
            }
            days++;
        }
    }

}