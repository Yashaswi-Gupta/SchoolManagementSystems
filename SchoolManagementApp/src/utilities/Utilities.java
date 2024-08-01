package utilities;

import databaseconnection.ConnectionProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilities {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found.");
        }
    }
    public static boolean verifyPassword(String enteredPassword, String storedHash) {
        String enteredHash = hashPassword(enteredPassword);
        return enteredHash.equals(storedHash);
    }

    public static boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;
        String query = "SELECT * FROM admin WHERE username = ?";

        try (Connection connection = ConnectionProvider.CreateConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String pswd = resultSet.getString("password");
                    boolean isVerified =  Utilities.verifyPassword(password, pswd);
                    if(isVerified) {
                        isAuthenticated = true;
                        System.out.println("Admin logged in successfully!");
                    }else System.out.println("Invalid credentials. Try again.");
                } else System.out.println("Invalid credentials. Try again.");
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        return isAuthenticated;
    }


}
