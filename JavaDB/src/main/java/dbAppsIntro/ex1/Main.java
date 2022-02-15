package dbAppsIntro.ex1;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni",properties );
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT concat(first_name, ' ', last_name) as full_name from employees where salary > ?");
            Scanner scanner = new Scanner(System.in);
            ps.setDouble(1, Double.parseDouble(scanner.nextLine()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("full_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
