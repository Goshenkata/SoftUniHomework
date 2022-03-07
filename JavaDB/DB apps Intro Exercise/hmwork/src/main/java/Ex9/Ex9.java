package Ex9;

import db.Database;

import java.sql.*;
import java.util.Scanner;

public class Ex9 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        Connection connection = DriverManager
                .getConnection(Database.CONNECTION_STRING,
                        Database.USER,
                        Database.PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("call usp_get_older(?)");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement = connection
                .prepareStatement("select name, age from minions where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt("age"));
        }
    }
}
