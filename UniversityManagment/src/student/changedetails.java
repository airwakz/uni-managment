package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class changedetails {
    public void change_student_details(int a){
         
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";

        int studentId = a;

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Change Details here 
            Scanner in = new Scanner(System.in);
            System.out.println("1. Change Address : ");
            System.out.println("2. Change Email : ");
            System.out.println("3. Change Phone No. : ");
            System.out.print("Enter your choice : ");
            String choice = in.nextLine();
            switch(choice){
                case "1": 
                    System.out.print("Enter New Address : ");
                    String new_add = in.nextLine();
                    String query1 = "UPDATE `university`.`students` SET `students_address` = '"+new_add+"' WHERE (`student_id` = '"+studentId+"');";
                    Statement stm1 = connection.createStatement();
                    try{
                    stm1.executeUpdate(query1);
                    }catch(Exception e){
                    e.printStackTrace();
                    change_student_details(a);
                    }
                    System.out.println("Update Sucessful");

                break;

                case "2":
                    System.out.print("Enter New Email : ");
                    String new_email = in.nextLine();
                    String query2 = "UPDATE `university`.`students` SET `students_email` = '"+new_email+"' WHERE (`student_id` = '"+studentId+"');";
                    Statement stm2 = connection.createStatement();
                    try{
                    stm2.executeUpdate(query2);
                    }catch(Exception e){
                    e.printStackTrace();
                    change_student_details(a);
                    }
                    System.out.println("Update Sucessful");
                break;

                case "3":
                    System.out.print("Enter New Phone No. : ");
                    String new_no = in.nextLine();
                    String query3 = "UPDATE `university`.`students` SET `phone_no` = '"+new_no+"' WHERE (`student_id` = '"+studentId+"');";
                    Statement stm3 = connection.createStatement();
                    try{
                    stm3.execute(query3);
                    }catch(Exception e){
                    e.printStackTrace();
                    change_student_details(a);
                    }
                    System.out.println("Update Sucessful");
                break;
                
                default:
                    System.out.println("Invalid Choice");
                    change_student_details(a);
            }
        }catch(Exception e){
                e.printStackTrace();
            }
    }

    public void change_faculty_details(int a){
         
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";

        int facId = a;

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Change Details here 
            Scanner in = new Scanner(System.in);
            System.out.println("1. Change Address : ");
            System.out.println("2. Change Email : ");
            System.out.println("3. Change Phone No. : ");
            System.out.print("Enter your choice : ");
            String choice = in.nextLine();
            switch(choice){
                case "1": 
                    System.out.print("Enter New Address : ");
                    String new_add = in.nextLine();
                    String query1 = "UPDATE `university`.`facregi` SET `address` = '"+new_add+"' WHERE (`fac_id` = '"+facId+"');";
                    Statement stm1 = connection.createStatement();
                    try{
                    stm1.executeUpdate(query1);
                    }catch(Exception e){
                    e.printStackTrace();
                    change_faculty_details(facId);
                    }
                    System.out.println("Update Sucessful");
                break;

                case "2":
                    System.out.print("Enter New Email : ");
                    String new_email = in.nextLine();
                    String query2 = "UPDATE `university`.`facregi` SET `email` = '"+new_email+"' WHERE (`fac_id` = '"+facId+"');";
                    Statement stm2 = connection.createStatement();
                    try{
                    stm2.executeUpdate(query2);
                    }catch(Exception e){
                    e.printStackTrace();
                    change_faculty_details(facId);
                    }
                    System.out.println("Update Sucessful");
                break;

                case "3":
                    System.out.print("Enter New Phone No. : ");
                    String new_no = in.nextLine();
                    String query3 = "UPDATE `university`.`facregi` SET `Phone` = '"+new_no+"' WHERE (`fac_id` = '"+facId+"');";
                    Statement stm3 = connection.createStatement();
                    try{
                    stm3.execute(query3);
                    }catch(Exception e){
                    e.printStackTrace();
                    change_faculty_details(facId);
                    }
                    System.out.println("Update Sucessful");
                break;
                
                default:
                    System.out.println("Invalid Choice");
                    change_faculty_details(facId);
            }
        }catch(Exception e){
                e.printStackTrace();
            }
    }
}
