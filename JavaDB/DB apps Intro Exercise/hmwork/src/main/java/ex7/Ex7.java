package ex7;

import db.Database;

import javax.xml.crypto.Data;
import java.awt.dnd.DropTarget;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex7 {
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection(Database.CONNECTION_STRING,
                        Database.USER,Database.PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select name from minions");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
            names.add(resultSet.getString("name"));
        }
        int j =0;
        for (int i=0; i<(names.size())/2; i++) {
            System.out.println(names.get(i));
            System.out.println(names.get(names.size()-1-i));
            j+=2;
        }
        System.out.println(j);
        connection.close();
    }
}
