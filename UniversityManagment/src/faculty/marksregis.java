package faculty;
import java.sql.*;
import java.util.Scanner;
public class marksregis {
     public static void mar(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Student ID:");
        String sid = in.nextLine();
        if (checksid(sid)==false) {
            System.out.println("Student ID does not exist.");
            return;  
        }
        System.out.println("Enter Course Name: ");
        String cname = in.nextLine();
        if(checkcourse(cname)==false)
        {
            System.out.println("Course does not exist.");
            System.err.println("All the course in the university are:");
            showAllCourses();
            return;
        }
        System.out.println("Enter Marks: ");
        int marks = in.nextInt();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO marks (marks) VALUES (?, ?,?)")) {
            pstmt.setString(1, sid);
            pstmt.setString(2, cname);
            pstmt.setInt(3, marks);
            int status = pstmt.executeUpdate();
            if (status > 0) {
                System.out.println("Record is inserted");
            } else {
                System.out.println("Record insertion failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred");
        }
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
    
}
