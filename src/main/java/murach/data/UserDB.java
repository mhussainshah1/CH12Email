package murach.data;

import murach.business.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB {
    public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();

        String query = """ 
                INSERT INTO User (Email, FirstName, LastName)
                VALUES (?, ?, ?)
                """;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();

        String query = """
                UPDATE User SET 
                FirstName = ?, 
                LastName = ?
                WHERE Email = ?
                """;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int delete(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();

        String query = """
                DELETE FROM User
                WHERE Email = ?
                """;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setString(1, user.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();

        String query = """
                SELECT Email
                FROM User
                WHERE Email = ?
                """;
        ResultSet rs = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User selectUser(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        ResultSet rs = null;
        String query = """
                SELECT *
                FROM User
                WHERE Email = ?
                """;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
            }
            return user;
        } catch (SQLException e) {
           e.printStackTrace();
            return null;
        }
    }
}