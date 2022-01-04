package com.sabin.Main;

import java.util.Scanner;
import com.sabin.student.*;

public class Main {
	static Scanner sc = new Scanner(System.in);
	StudentInfo studentInfo = new StudentInfo();
	
	//MAIN METHOD
	public static void main(String[] args) {
		new Main().mainMenu();
	}
	
	// MAIN MENU
	public void mainMenu() {
		while (true) {
			System.out
					.println("\nPlease enter one of the options: \n"
							+ "1. Input Student details and save in the file\n"
							+ "2. Display all Students details from the file\n"
							+ "3. Update the details of a Student\n"
							+ "4. Delete the Student's detials\n" + "5. Exit");

			int option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			case 1:
				studentInfo.inputStudentDetails();
				break;
			case 2:
				studentInfo.displayAllStudentsDetails();
				break;
			case 3:
				studentInfo.updateStudentDetails();
				break;
			case 4:
				studentInfo.deleteStudentDetails();
				break;
			case 5:
				System.exit(0);
			default:
				System.out.println("Please enter a valid option (1,2,3)");
			}
		}
	}	
}

//ok1
