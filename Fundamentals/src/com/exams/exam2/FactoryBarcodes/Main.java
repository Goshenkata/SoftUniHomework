package com.exams.exam2.FactoryBarcodes;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Pattern pattern = Pattern.compile("^@#+(?<barcode>[A-Z][A-Za-z0-9]{4,}[A-Z])@#+$");
            String barcode = scanner.nextLine();
            Matcher matcher = pattern.matcher(barcode);
            if (matcher.find()) {
                boolean hasDigits = false;
                StringBuilder productGroup = new StringBuilder();
                for (char k : matcher.group("barcode").toCharArray()){
                    if (Character.isDigit(k)) {
                        hasDigits = true;
                        productGroup.append(k);
                    }
                }
                if (hasDigits) {
                    System.out.printf("Product group: %s%n", productGroup);
                } else {
                    System.out.println("Product group: 00");
                }
            } else System.out.println("Invalid barcode");
        }
    }
}
