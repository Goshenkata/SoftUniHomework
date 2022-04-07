package softuni.exam.util;

import java.time.LocalDate;

public class UtilClass {
    public static LocalDate toLocalDate(String date) {
        String[] dateParts = date.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return LocalDate.of(year, month, day);
    }
}
