package dbAppsIntro.ex2;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    String user = scanner.nextLine();
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", "root", "");

        PreparedStatement ps = conn.prepareStatement("" +
                "SELECT \n" +
                "    user_name,concat(u.first_name, ' ', u.last_name) as full_name, COUNT(ug.game_id) AS games_count\n" +
                "FROM\n" +
                "    users AS u\n" +
                "        JOIN\n" +
                "    users_games AS ug ON u.id = ug.user_id\n" +
                "WHERE\n" +
                "    user_name LIKE ?;");

        ps.setString(1,user);
        ResultSet rs = ps.executeQuery();
        rs.next();
        if (rs.getString("user_name") == null) {
            System.out.println("No such user exists");
        } else {
            String fullName = rs.getString("full_name");
            int numGames = rs.getInt("games_count");
            System.out.printf("User: %s%n%s has played %d games", user, fullName, numGames);
        }
    }

}
