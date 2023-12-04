package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class changegrades {
    public void change_student_details(String a){
         
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";

        String studentId = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String query = "SELECT student_id FROM students WHERE students_name = '"+a+"'";
            Statement Statement = connection.prepareStatement(query);

            ResultSet resultSet = Statement.executeQuery(query);

            if (resultSet.next()) {
                studentId = resultSet.getString("student_id");
            }

            
            // Change Details here 
            Scanner in = new Scanner(System.in);
            System.out.println("1. Change Address : ");
            System.out.println("2. Change Email : ");
            System.out.println("3. Change Phone No. : ");
            System.out.println("Enter your choice : ");
            String choice = in.nextLine();
            switch(choice){
                case "1": 
                    System.out.println("Enter New Address : ");
                    in.nextLine();
                    String new_add = in.nextLine();
                    System.out.println(new_add);
                    String query1 = "UPDATE `university`.`students` SET `students_address` = '"+new_add+"' WHERE (`student_id` = '"+studentId+"');";
                    Statement stm1 = connection.createStatement();
                    try{
                    stm1.executeUpdate(query1);
                    }catch(Exception e){
                    e.printStackTrace();
                    }
                    System.out.println("Update Sucessful");
                break;

                case "2":
                    System.out.println("Enter New Email : ");
                    in.nextLine();
                    String new_email = in.nextLine();
                    String query2 = "UPDATE `university`.`students` SET `students_email` = '"+new_email+"' WHERE (`student_id` = '"+studentId+"');";
                    Statement stm2 = connection.createStatement();
                    try{
                    stm2.executeUpdate(query2);
                    }catch(Exception e){
                    e.printStackTrace();
                    }
                    System.out.println("Update Sucessful");
                break;

                case "3":
                    System.out.println("Enter New Phone No. : ");
                    in.nextLine();
                    String new_no = in.nextLine();
                    String query3 = "UPDATE `university`.`students` SET `phone_no` = '"+new_no+"' WHERE (`student_id` = '"+studentId+"');";
                    Statement stm3 = connection.createStatement();
                    try{
                    stm3.execute(query3);
                    }catch(Exception e){
                    e.printStackTrace();
                    }
                    System.out.println("Update Sucessful");
                break;
                
                default:
                    System.out.println("Invalid Choice");
            }
        }catch(Exception e){
                e.printStackTrace();
            }
    }
}
