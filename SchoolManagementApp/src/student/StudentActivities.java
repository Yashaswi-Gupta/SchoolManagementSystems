package student;

import databaseconnection.ConnectionProvider;
import utilities.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import  service.*;
import student.*;

public class StudentActivities implements  studentInterface{
    apiService as;
    public StudentActivities(){
       as = new apiService();
    }
    public void activities(int choice){
        Scanner br = new Scanner(System.in);
        System.out.println("WELCOME TO STUDENT MANAGEMENT APP");
        while(true){
            if(choice == 1){
                System.out.println("PRESS 1 TO ADD STUDENT.");
                System.out.println("PRESS 2 TO DELETE STUDENT.");
                System.out.println("PRESS 3 TO DISPLAY STUDENT.");
                System.out.println("PRESS 4 TO UPDATE STUDENT.");
                System.out.println("PRESS 5 TO FEE UPDATE FOR STUDENT.");
                System.out.println("PRESS 6 TO COURSES UPDATE FOR STUDENT.");
                System.out.println("PRESS 8 TO REGISTER A STUDENT");
//                System.out.println("PRESS 9 TO COURSES UPDATE FOR STUDENT.");

            }else {System.out.println("PRESS 1 TO ADD YOUR DATA.");
                System.out.println("PRESS 7 TO DISPLAY YOUR DATA.");
            }

            System.out.println("PRESS 9 TO EXIT APP...");
            // int c = Integer.parseInt(br.readLine());
            int c = br.nextInt();
            if(c==1){
                // add student
                System.out.println("Enter the id : ");
                int id = br.nextInt();
                br.nextLine();
                System.out.println("Enter the name : ");
                String sname=br.nextLine();
                System.out.println("Enter the mobile number : ");
                String mobileNumebr = br.nextLine();

                // object of student
//                Student ss = new Student(id,sname,mobileNumebr);
                student ss  = new student(id,sname,mobileNumebr);
                boolean answer = as.insertSt(ss);

                if(answer){
                    System.out.println("Successfully student data added...\n");
                }
                else{
                    System.out.println("Something went wrong....\n");
                }
            }

            else if(c==2) {
                //delete student
                System.out.println("Enter the id : ");
                int a = br.nextInt();
                int answer = as.deleteSt(a);
                if(answer>0){
                    System.out.println(a + " id successfully deleted...");
                }
                else{
                    System.out.println("This id doesn't exist...\n");
                }
            }
            else if(c==3){
                // to display student

                as.display();
            }
            else if(c==4){
                System.out.println("Enter the id : ");
                int id=br.nextInt();
                br.nextLine();
                System.out.println("Enter the name : ");
                String sname = br.nextLine();
                System.out.println("Enter the mobile number :");
                String mobileNo = br.nextLine();
                student s = new student(id,sname,mobileNo);
                int update = as.updateStudent(s);
                if(update>0){
                    System.out.println(id + " id updated successfully...");
                }
                else{
                    System.out.println("id " + id + " Doesn't exist.!!! \n");
                }
            }
            else if(c==5){
                System.out.println("Enter the id : ");
                int id=br.nextInt();
                br.nextLine();
                System.out.println("Enter the fee : ");
                int fee = br.nextInt();
//                Student s = new Student(id,sname,mobileNo);
                int update = as.feeUpdateStudent(id, fee);
                if(update>0){
                    System.out.println(id + " id updated successfully...");
                }
                else{
                    System.out.println("id " + id + " Doesn't exist.!!! \n");
                }
            }
            else if(c==6){
                System.out.println("Enter the id : ");
                int id=br.nextInt();
                br.nextLine();
//                System.out.println("Enter the courses : ");
                ArrayList<String> courses = new ArrayList<>();
                System.out.println("Enter course names. Type 'q' to quit.");
                String input;

                while (true) {
                    System.out.print("Enter course: ");
                    input = br.nextLine();

                    if (input.equalsIgnoreCase("q")) {
                        break;
                    }

                    courses.add(input);
                }

                int update = as.courseUpdateStudent(id, courses);
                if(update>0){
                    System.out.println(id + " id updated successfully...");
                }
                else{
                    System.out.println("id " + id + " Doesn't exist.!!! \n");
                }
            }
            else if(c==7){
                // to display student
                System.out.println("Enter the id : ");
                int id=br.nextInt();
                br.nextLine();
                as.displayById(id);
            }
            else if(c==8) {
                // register student
                System.out.println("Enter the username: ");
                br.nextLine();
                String usernames = br.nextLine();
//                br.nextLine();
                System.out.println("Enter the password: ");
                String password = br.nextLine();
//                System.out.println(usernames);
//                System.out.println(password);
                String hashedPassword = Utilities.hashPassword(password);

                boolean success = registerAdmin(usernames, hashedPassword);
                if (success) {
                    System.out.println("Admin registered successfully.");
                } else {
                    System.out.println("Failed to register admin.");
                }
            }
            else if(c==9) {
                // exit
                break;
            }
            else {
                System.out.println("Invalid Input !!!");
            }
        }
    }

    public boolean registerAdmin(String usernames, String hashedPassword) {
        try {
            Connection con = ConnectionProvider.CreateConnection();
            String query = "INSERT INTO admin (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, usernames);
            pstmt.setString(2, hashedPassword);
            int rowsInserted = pstmt.executeUpdate();
            pstmt.close();
            con.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
