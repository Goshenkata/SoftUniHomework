package ex2;

import db.Database;

import java.sql.*;
import java.util.Properties;

public class Ex2VillainsNames {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        Connection connection = DriverManager.getConnection(Database.CONNECTION_STRING,Database.USER,Database.PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT \n" +
                "    `name`, COUNT(DISTINCT mv.minion_id) AS count_minions\n" +
                "FROM\n" +
                "    villains AS v\n" +
                "        JOIN\n" +
                "    minions_villains AS mv ON v.id = mv.villain_id\n" +
                "GROUP BY v.id\n" +
                "HAVING count_minions > 15\n" +
                "ORDER BY count_minions DESC;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt("count_minions"));
        }
    }
}
