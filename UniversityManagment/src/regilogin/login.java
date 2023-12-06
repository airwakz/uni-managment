package regilogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class login {



    public static String adm (String b)
    { 
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";
        String a="" ;
        a=b;

        try {

            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();


           
                // Check the database for the entered username and password
                String query = "SELECT * FROM user WHERE username = ? AND passsword = ? ";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, enteredUsername);
                    preparedStatement.setString(2, enteredPassword);
                   

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        String storedDesignation = resultSet.getString("designation");

                        if (storedDesignation.equalsIgnoreCase(b)) {
                            System.out.println("Login successful!");
                            a = enteredUsername;
                        } else {
                            System.out.println("Designation mismatch. Please enter the correct designation.");
                            adm("A");
                        
                        }
                    } else {
                        System.out.println("Invalid username or password");
                        adm("A");
                        
                    }
                }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
        

    }
    public static int fac (String b)
    { 
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";
        int a =0;
        

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter useremail: ");
            String enteredUseremail = scanner.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();


           
                // Check the database for the entered username and password
                String query = "SELECT * FROM user WHERE useremail= ? AND passsword = ? ";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, enteredUseremail);
                    preparedStatement.setString(2, enteredPassword);
                   

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        String storedDesignation = resultSet.getString("designation");
                    
                        if (storedDesignation.equalsIgnoreCase(b)) {
                            System.out.println("Login successful!");
                    
                            String query1 = "SELECT id FROM user WHERE useremail = ?";
                            try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
                                preparedStatement1.setString(1, enteredUseremail);
                    
                                ResultSet resultSet1 = preparedStatement1.executeQuery();
                                if (resultSet1.next()) {
                                    a = resultSet1.getInt("id");
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Designation mismatch. Please enter the correct designation.");
                            fac("F");
                        }
                    } else {
                        System.out.println("Invalid useremail or password");
                        fac("F");
                    }
                }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
        

    }
     public static int stud (String b)
    {
       
        
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";
        int a = 0;

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter useremail: ");
            String enteredUseremail = scanner.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();


           
                // Check the database for the entered username and password
                String query = "SELECT * FROM user WHERE useremail= ? AND passsword = ? ";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, enteredUseremail);
                    preparedStatement.setString(2, enteredPassword);
                   

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        String storedDesignation = resultSet.getString("designation");

                        if (storedDesignation.equalsIgnoreCase(b)) {
                            System.out.println("Login successful!");
                            String query1 = "SELECT id FROM user WHERE useremail = ?";
                            try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
                                preparedStatement1.setString(1, enteredUseremail);
                                ResultSet resultSet1 = preparedStatement1.executeQuery();
                                if (resultSet1.next()) {
                                    a = resultSet1.getInt("id");
                                }
                            }
                        catch (SQLException e) {
                            e.printStackTrace();
                            }
                        } else {
                            System.out.println("Designation mismatch. Please enter the correct designation.");
                            stud("S");
                        }
                    } else {
                        System.out.println("Invalid useremail or password");
                        stud("S");
                    }
                }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
        

    }
    
        public static void LoadDriver(String[] args) {
            try {
                // The newInstance() call is a work around for some
                // broken Java implementations
    
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception ex) {
                // handle the error
            }
        }
    
}