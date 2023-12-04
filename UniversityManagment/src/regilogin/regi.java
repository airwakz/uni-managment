package regilogin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class regi {

    public static void uregi() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String username = in.nextLine();
        if (username.length() < 0) {
            System.out.println("Username cannot be empty");
            return;
        }
        System.out.println("Enter your designation");
        String designation = in.nextLine();
        if (designation.length() < 0) {
            System.out.println("Designation cannot be empty");
            return;
        }
        System.out.println("Enter your email: ");
        String useremail = in.nextLine();
                if (useremail.length() < 0) {
            System.out.println("Username cannot be empty");
            return;
        }
                 if (checkUsernameExists(useremail)) {
            System.out.println("Useremail already exists. Please choose a different one.");
            return;
        }

        System.out.println("Enter your password: ");
        String password = in.nextLine();
        if (password.length() < 0) {
            System.out.println("Password cannot be empty");
            return;
        }
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
            PreparedStatement pstmt = null;

            String query = "Insert into user(passsword,username,designation,useremail)" + "VALUES(?,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, password);
            pstmt.setString(2, username);
            pstmt.setString(3, designation);
            pstmt.setString(4, useremail);
            int status = pstmt.executeUpdate();

            if (status > 0) {
                System.out.println("Record is inserted");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }
    }

    private static boolean checkUsernameExists(String useremail) {
        boolean exists = false;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
             PreparedStatement pstmt = con.prepareStatement("SELECT 1 FROM user WHERE useremail = ?")) {
            pstmt.setString(1, useremail);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
}
