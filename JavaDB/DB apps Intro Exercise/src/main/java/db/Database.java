package db;

import entities.Minion;
import entities.Town;
import entities.Villain;
import jdk.jshell.spi.SPIResolutionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {

    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/minions_db";
    public static final String USER = "root";
    public static final String PASSWORD = "${mysqlpass}";

    Connection connection;

    public Database(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void close() throws SQLException {
        connection.close();
    }

    public Optional<Villain> getVillainById(int villain_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from villains where `id` = ?;");
        preparedStatement.setInt(1, villain_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Villain villain = new Villain();
            villain.setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setEvillnesFactor(resultSet.getString("evilness_factor"));
            return Optional.of(villain);
        }
        return Optional.empty();
    }

    public List<Minion> getMinionsForVilain(int villain_id) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select m.* from villains as v \n" +
                        "join minions_villains as mv on v.id = mv.villain_id\n" +
                        "join minions as m on mv.minion_id = m.id\n" +
                        "where v.id = ?\n" +
                        "group by m.id;");
        preparedStatement.setInt(1, villain_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Minion> minions = new ArrayList<>();
        while (resultSet.next()) {
            Minion minion = new Minion();
            minion.setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setAge(resultSet.getInt("age"))
                    .setTown_id(resultSet.getInt("town_id"));
            minions.add(minion);
        }
        return minions;

    }

    public Optional<Town> getTownByName(String name) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from towns where name like ?"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Town town = new Town();
            town.setId(resultSet.getInt("id"));
            town.setName(resultSet.getString("name"));
            town.setCountry(resultSet.getString("country_name"));
            return Optional.of(town);
        }
        return Optional.empty();
    }

    public void insertTown(Town newTown) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_STRING,USER,PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into towns (name, country) values (?, ?);");
        preparedStatement.setString(1,newTown.getName());
        preparedStatement.setString(2, newTown.getCountry());
        preparedStatement.executeQuery();
    }

    public Optional<Villain> getVillainByName(String villainName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from villains where name like ?");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Villain villain = new Villain();
            villain.setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setEvillnesFactor(resultSet.getString("evilness_factor"));
            return Optional.of(villain);
        }
        return Optional.empty();
    }

    public void insertVillain(Villain villain) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into villains (name,evilness_factor) values (?, ?)"
        );
        preparedStatement.setString(1,villain.getName());
        preparedStatement.setString(2,villain.getEvillnesFactor());
        preparedStatement.executeQuery();
    }

    public void insertMinion(Minion minion) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into minions (name, age, town_id) values (?, ?, ?)"
        );
        preparedStatement.setString(1,minion.getName());
        preparedStatement.setInt(2, minion.getAge());
        preparedStatement.setInt(3, minion.getTown_id());
        preparedStatement.executeQuery();
    }

    public void insertMinionVillain(int villainId, int minionId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into minions_villains (minion_id, villain_id) values  (?, ?)"
        );
        preparedStatement.setInt(1,minionId);
        preparedStatement.setInt(2, villainId);
        preparedStatement.executeQuery();
    }
}
