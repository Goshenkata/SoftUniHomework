package ex5;

import db.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();
        Connection connection = DriverManager.getConnection(Database.CONNECTION_STRING, Database.USER, Database.PASSWORD);

        PreparedStatement preparedStatement= connection.prepareStatement(
                "update towns set name = upper(name) where country like ?");
        preparedStatement.setString(1,country);
        int i = preparedStatement.executeUpdate();

        if (i>0) {
            List<String> towns = new ArrayList<>();
            PreparedStatement townNamesQuery = connection.prepareStatement(
                    "select name from towns where country like ?"
            );
            townNamesQuery.setString(1, country);
            ResultSet townNamesResult = townNamesQuery.executeQuery();
            while (townNamesResult.next()) {
                towns.add(townNamesResult.getString("name"));
            }
            System.out.printf("%s town names were affected.%n%s", towns.size(), towns);
        } else {
            System.out.println("No town names were affected.");
        }
    }
}
