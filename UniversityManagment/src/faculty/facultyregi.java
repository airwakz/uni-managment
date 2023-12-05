package faculty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;

public
 
class facultyregi {
    
    public static void fac(String a) {
        Scanner in = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
           
            System.out.print("Enter Your Email Address: ");
            String email = in.nextLine();

            System.out.print("Enter Your Specialization: ");
            String spec = in.nextLine();

            System.out.print("Enter Your Address: ");
            String address = in.nextLine();

            System.out.print("Enter Your Phone Number: ");
            String phone = in.nextLine();

            System.out.print("Enter Your Adhar: ");
            int adhar = in.nextInt();

            String query = "INSERT INTO facregi (name,email,spec,address,Phone,adhar) VALUES (?,?,?,?,?,?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, a);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, spec);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, phone);
                preparedStatement.setInt(6, adhar);

                int status = preparedStatement.executeUpdate();
                if (status > 0) {
                    System.out.println("Record is inserted");
                } else {
                    System.out.println("Record insertion failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred");
        }
    }
    
}
