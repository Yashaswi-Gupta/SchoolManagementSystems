package ui;

import java.io.Console;
import java.util.Scanner;

import utilities.*;
import student.*;


public class admin implements uiInterface{
    StudentActivities sa;
    public  admin(){
       sa = new StudentActivities();
    }
    public boolean login() {
        Console console = System.console();

        String username;
        String password;

//        if(console != null){

//        System.out.print(console);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Admin Username: ");
        username = scanner.nextLine();
//            username = console.readLine();
        System.out.print("Enter Admin Password: ");
//            char[] passwordArray = console.readPassword();
//            password = new String(passwordArray);
//        }
//        else{
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("No console available. Enter details using scanner.");
//            System.out.print("Enter Admin Username: ");
//            username = scanner.nextLine();
//            System.out.print("Enter Admin Password: ");
        password = scanner.nextLine();
//        }


        return Utilities.authenticate(username, password);

    }

    public void Menu(int choice) {
        if(choice == 1){
            System.out.println("Welcome, Admin!");

        }
        else{
            System.out.println("Welcome, User!");
        }
        sa.activities(choice);

    }

}