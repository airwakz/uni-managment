package faculty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;

public
 
class facultyregi {
    
    public static void fac(int a) {
        Scanner in = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
            System.out.print("Enter Your Name:");
            String name =in.nextLine();
           
            System.out.print("Enter Your Email Address: ");
            String email = in.nextLine();

            System.out.print("Enter Your Specialization: ");
            String spec = in.nextLine();

            System.out.print("Enter Your Address: ");
            String address = in.nextLine();

            System.out.print("Enter Your Phone Number: ");
            String phoneNumber = in.nextLine();
            while ((phoneNumber.length() != 10)||(phoneNumber.matches("\\d+")==false)) {
                System.out.println("Phone number must be 10 digits. Please provide a valid phone number.");
                System.out.print("Enter Your Phone Number: ");
                phoneNumber=in.nextLine();
                
            }

            System.out.print("Enter Your Adhar: ");
            String adhar = in.nextLine();
            while ((adhar.length() != 12)||(adhar.matches("\\d+")==false)) {
                System.out.println("Phone number must be 10 digits. Please provide a valid phone number.");
                System.out.print("Enter Your Adhar: ");
                 adhar = in.nextLine();
            }

            String query = "INSERT INTO facregi (fac_id,name,email,spec,address,Phone,adhar) VALUES (?,?,?,?,?,?,?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, a);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, spec);
                preparedStatement.setString(5, address);
                preparedStatement.setString(6, phoneNumber);
                preparedStatement.setString(7,adhar);

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
