package faculty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class faccourse {
    
    public static void faccurs() {
        addCourse();
    }

    private static void addCourse() {
        Scanner in = new Scanner(System.in);   
        System.out.print("Enter Faculty ID:");
        String facid = in.nextLine();

        while (checkfacid(facid)==false) {
            System.out.println("Faculty ID does not exist.");
             System.out.print("Enter Faculty ID:");
             facid = in.nextLine();
        }

        System.out.println("Enter Course Name: ");
        String cname = in.nextLine();
        
    while(checkcourse(cname)==false)
        {
            System.out.println("Course does not exist.");
            System.err.println("All the course in the university are:");
            showAllCourses();
            System.out.println("Enter Course Name: ");
            cname = in.nextLine();
            

        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
                PreparedStatement pstmt = con.prepareStatement("UPDATE facregi SET course = ? WHERE fac_id = ? ; ")) {
            pstmt.setString(1, cname);
            pstmt.setString(2, facid);
            

            pstmt.executeUpdate();

            System.out.println("Course added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static boolean checkfacid(String facid) {
        boolean exists = false;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
                PreparedStatement pstmt = con.prepareStatement("SELECT 1 FROM facregi WHERE fac_id = ?")) {
            pstmt.setString(1, facid);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    private static boolean checkcourse(String cname) {
        boolean exists = false;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
                PreparedStatement pstmt = con.prepareStatement("SELECT 1 FROM course WHERE Course_name= ?")) {
            pstmt.setString(1, cname);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
    public static void showAllCourses() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT Course_name FROM course");
    
            while (rs.next()) {
                String courseName = rs.getString("Course_name");
                System.out.println(courseName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}