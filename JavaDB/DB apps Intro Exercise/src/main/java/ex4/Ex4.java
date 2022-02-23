package ex4;

import db.Database;
import entities.Minion;
import entities.Town;
import entities.Villain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(Database.CONNECTION_STRING, Database.USER, Database.PASSWORD);
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        String[] info = scanner.nextLine().split(":* ");
        Minion minion = new Minion();
        Optional<Town> town = database.getTownByName(info[3]);
        //if the town isn't in the database insert it
        if (town.isEmpty()) {
            Town newTown = new Town();
            newTown.setName(info[3]).setCountry(null).setId(null);
            database.insertTown(newTown);
            //load it again, so we can get access to the new id
            town = database.getTownByName(newTown.getName());
        }
        minion.setName(info[1])
                .setAge(Integer.parseInt(info[2]))
                .setTown_id(town.
                        //towns which aren't in the database must be inserted
                        orElseThrow(() -> new UnsupportedOperationException("error inserting town which is not in the database"))
                        .getId());
        database.insertMinion(minion);

        String villainName = scanner.nextLine().split(": ")[1];
        //if the minion isn't in the database insert it
        Optional<Villain> villain = database.getVillainByName(villainName);
        if (villain.isEmpty()) {
            Villain newVillain = new Villain();
            newVillain.setId(null).setEvillnesFactor("evil").setName(villainName);
            database.insertVillain(newVillain);
            villain = database.getVillainByName(villainName);
        }

        database.insertMinionVillain(
                villain
                        .orElseThrow(() -> new SQLDataException())
                        .getId(),
                minion.getId());

        database.close();
    }
}
