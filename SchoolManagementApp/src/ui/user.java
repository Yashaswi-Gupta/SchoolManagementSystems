package ui;

import java.util.Scanner;

import utilities.*;
import student.*;

public class user implements uiInterface{
    StudentActivities sa;
    public  user(){
        sa = new StudentActivities();
    }
    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        return Utilities.authenticate(username, password);

    }

    public void Menu(int choice) {
        System.out.println("Welcome, User!");
        // Admin-specific functionalities can be added here.
        sa.activities(choice);
    }
}
