package faculty;
import java.sql.*;
import java.util.Scanner;
public class marksregis {
     public static void mar(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter Course Name: ");
        String cnamprintf
        if(checkcourse(cname)==false)
        {
            System.out.println("Course does not exist.");
            System.err.println("All the course in the university are:");
            showAllCourses();
            
            mar();
        }
        System.out.println("Enter Student ID:");
        int sid = in.nextInt();
        if (checksid(sid)==false) {
            System.out.println("Student ID does not exist.");
            System.out.println("Ask The Admin For Course Registration. ");
            mar() ;
        }
        
        System.out.println("Enter Marks: ");
        int marks = in.nextInt();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
                PreparedStatement pstmt = con.prepareStatement("UPDATE marks SET marks = ? WHERE student_id = ? AND course_name = ? ;"
                        )) {
            pstmt.setInt(1, marks);
            pstmt.setInt(2, sid);
            pstmt.setString(3, cname);
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
        System.out.println("Do you want to continue? (Y/N)");
        String ls=in.nextLine();
        if(ls.equalsIgnoreCase("Y")){
            mar();
        }
        else{
            System.out.println("Thank You");
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
    private static boolean checksid(int sid) {
        boolean exists = false;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
                PreparedStatement pstmt = con.prepareStatement("SELECT 1 FROM marks WHERE student_id = ?")) {
            pstmt.setInt(1, sid);

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
                PreparedStatement pstmt = con.prepareStatement("SELECT 1 FROM marks WHERE course_name= ?")) {
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
