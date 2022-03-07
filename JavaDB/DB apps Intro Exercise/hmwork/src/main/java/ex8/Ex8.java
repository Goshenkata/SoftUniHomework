package ex8;

import db.Database;

import java.awt.dnd.DragGestureEvent;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Ex8 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager
                .getConnection(Database.CONNECTION_STRING, Database.USER, Database.PASSWORD);

        int[] ids = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "update minions set name = lower(name),age = age + 1 where id = ?");
        for (int id : ids) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        preparedStatement = connection.prepareStatement("select name, age from  minions");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString("name"), resultSet.getInt("age"));
        }

    }
}
