

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class updatepassword {

    public static void updpswd(String[] args) {
        try {
            // Replace these values with your actual database information
            String databaseUrl = "jdbc:mysql://localhost:3306/university";
            String username = "root";
            String password = "root";
             
            // Establish the database connection
            try (Connection connection = DriverManager.getConnection(databaseUrl, username, password)) {
                // Update user password
                updatePassword(connection);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updatePassword(Connection connection) throws SQLException {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user User email to update password: ");
        String email = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.next();

        String updateQuery = "UPDATE `university`.`user` SET `passsword` = ? WHERE (`useremail` = ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password Updated");
            } else {
                System.out.println("Email " + email + " not found");
            }
        }
    }

    // public static String stud (String b)
    // { 
    //     String jdbcUrl = "jdbc:mysql://localhost:3306/university";
    //     String username = "root";
    //     String password = "root";
    //     String a="" ;

    //     try {
    //         Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
    //         Scanner scanner = new Scanner(System.in);

    //         System.out.print("Reverify useremail: ");
    //         String enteredUseremail = scanner.nextLine();

    //         System.out.print("Enter New password: ");
    //         String enteredPassword = scanner.nextLine();


           
    //             // Check the database for the entered username and password
    //             String query = "Update `university`.`user` SET `passsword`= ? WHERE designation = ?  AND useremail = ? ;";
    //             try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    //                 preparedStatement.setString(1, enteredPassword);
    //                 preparedStatement.setString(2, b);
    //                 preparedStatement.setString(3, enteredUseremail);
                   

    //                 ResultSet resultSet = preparedStatement.executeQuery();

    //                 if (resultSet.next()) {
    //                     String storedDesignation = resultSet.getString("designation");

    //                     if (storedDesignation.equalsIgnoreCase(b)) {
    //                         //System.out.println("Login successful!");
    //                         String query1 = "SELECT username FROM user WHERE useremail = ?";
    //                         try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
    //                             preparedStatement1.setString(1, enteredUseremail);
    //                             ResultSet resultSet1 = preparedStatement1.executeQuery();
    //                             if (resultSet1.next()) {
    //                                 a = resultSet1.getString("username");
    //                             }
    //                         }
    //                     catch (SQLException e) {
    //                         e.printStackTrace();
    //                         }
    //                     } else {
    //                         System.out.println("Designation mismatch. Please enter the correct designation.");
                        
    //                     }
    //                 } else {
    //                     System.out.println("Invalid useremail or password");
    //                 }
    //             }

    //         connection.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }

    //     return a;
        

    // }
}

