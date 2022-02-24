package ex3;

import db.Database;
import entities.Minion;
import entities.Villain;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Ex3 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(Database.CONNECTION_STRING, Database.USER, Database.PASSWORD);
        Database database = new Database(connection);

        Scanner scanner = new Scanner(System.in);
        int villain_id = Integer.parseInt(scanner.nextLine());
        Optional<Villain> villain = database.getVillainById(villain_id);
        if (villain.isEmpty()) {
            System.out.printf("No villain with ID %d exists in the database.%n", villain_id);
        } else {
            System.out.printf("Villain: %s%n", villain.get().getName());
            List<Minion> minions = database.getMinionsForVilain(villain_id);
            for (int i = 0; i < minions.size(); i++) {
                System.out.printf("%d. %s %d%n", i+1, minions.get(i).getName(), minions.get(i).getAge());
            }
        }

        database.close();
        connection.close();
    }
}
