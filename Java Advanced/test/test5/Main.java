import java.util.*;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> textiles = new ArrayDeque<>();
        Stack<Integer> medicaments = new Stack<>();
        Map<String, Integer> items = new HashMap<>();
        items.put("Patch", 0);
        items.put("Bandage", 0);
        items.put("MedKit", 0);
        Scanner scanner = new Scanner(System.in);
        String[] stringTex = scanner.nextLine().split(" ");
        String[] stringMed = scanner.nextLine().split(" ");
        for (String tex : stringTex) {
            textiles.add(Integer.parseInt(tex));
        }
        for (String s : stringMed) {
            medicaments.add(Integer.parseInt(s));
        }

        while (!medicaments.empty() && !textiles.isEmpty()) {
            int sum = medicaments.peek() + textiles.peek();
            switch (sum) {
                case 30:
                    items.put("Patch", items.get("Patch") + 1);
                    medicaments.pop();
                    textiles.poll();
                    break;
                case 40:
                    items.put("Bandage", items.get("Bandage") + 1);
                    medicaments.pop();
                    textiles.poll();
                    break;
                case 100:
                    items.put("MedKit", items.get("MedKit") + 1);
                    medicaments.pop();
                    textiles.poll();
                    break;
                default:
                    if (sum > 100) {
                        items.put("MedKit", items.get("MedKit") + 1);
                        medicaments.pop();
                        textiles.poll();
                        medicaments.push(medicaments.pop() + (sum - 100));
                    } else {
                        textiles.poll();
                        medicaments.push(medicaments.pop() + 10);
                    }
                    break;
            }
        }
        if (medicaments.empty() && textiles.isEmpty()) {
            System.out.println("Textiles and medicaments are both empty.");
        } else {
            if (textiles.isEmpty()) {
                System.out.println("Textiles are empty.");
            }
            if (medicaments.empty()) {
                System.out.println("Medicaments are empty.");
            }
        }

        Comparator<Map.Entry<String,Integer>> byValueDesc = Map.Entry.comparingByValue(Comparator.reverseOrder());
        Comparator<Map.Entry<String,Integer>> byKeyAsc = Map.Entry.comparingByKey();

        items.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .sorted(byValueDesc.thenComparing(byKeyAsc))
                .forEach(e -> System.out.printf("%s - %d%n", e.getKey(), e.getValue()));

        if (!medicaments.empty()) {
            ArrayList<String> s = new ArrayList<>();
            for (Integer medicament : medicaments) {
                s.add(medicament + "");
            }
            System.out.println("Medicaments left: " + String.join(", ", s));
        }
        if (!textiles.isEmpty()) {
            ArrayList<String> s = new ArrayList<>();
            for (Integer t : textiles) {
                s.add(t + "");
            }
            System.out.println("Textiles left: " + String.join(", ", s));
        }
    }
}
