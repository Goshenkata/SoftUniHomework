package advanced.stacksandqueues;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<String> stack = new ArrayDeque<>();
        String input = scanner.nextLine();
        while (!input.equals("Home")) {
            if (input.equals("back")) {
                if (!stack.isEmpty())stack.pop();
                if (stack.isEmpty()) {
                    System.out.println("no previous URLs");
                } else {
                    System.out.println(stack.peek());
                }
            } else {
                stack.push(input);
                System.out.println(input);
            }
            input = scanner.nextLine();
        }
    }
}
