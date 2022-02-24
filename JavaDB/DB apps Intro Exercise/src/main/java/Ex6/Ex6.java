package Ex6;

import db.Database;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Scanner;

public class Ex6 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        Connection connection = DriverManager
                .getConnection(Database.CONNECTION_STRING,
                        Database.USER,
                        Database.PASSWORD);

        PreparedStatement  preparedStatement= connection.prepareStatement("select name from villains where id = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        String villainName = null;
        connection.setAutoCommit(false);
        if (resultSet.next()) {
            villainName = resultSet.getString("name");

            preparedStatement = connection.prepareStatement("delete from minions_villains where villain_id = ?");
            preparedStatement.setInt(1, id);
            int minionsFreed = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from villains where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.printf("%s was deleted%n", villainName);


            System.out.printf("%d minions released%n", minionsFreed);
        } else {
            System.out.println("No such villain was found");
        }
        connection.commit();
        connection.close();
    }
}
