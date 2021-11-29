package com.sabin.student;

import java.util.ArrayList;
import java.util.Scanner;
import com.sabin.files.FileInfo;

public class StudentInfo {
	static Scanner sc = new Scanner(System.in);
	FileInfo fileInfo = new FileInfo();
	ArrayList<Student> students = new ArrayList<>();

	// TO INPUT STUDENT DETAILS
	public void inputStudentDetails() {
		while (true) {
			Student student = new Student(); // This line should be written here only to make new object each time when
												// 'while' loop runs.
			System.out.print("Enter the name: ");
			String name = sc.nextLine();
			student.setName(name);

			System.out.print("Enter the age: ");
			int age = sc.nextInt();
			sc.nextLine();
			student.setAge(age);

			fileInfo.createFile();
			int id = fileInfo.getId();
			student.setId(id);

			fileInfo.writingDataToFile(student); // Sending student to write in the file

			System.out.println("Do you want to add more student's details? (Y/N)");
			String option = sc.nextLine();
			if (!option.equalsIgnoreCase("Y")) {
				break;
			}
		}
	}

	// TO DISPLAY STUDENT DETAILS BY RETRIEVING FROM THE FILE
	public void displayAllStudentsDetails() {
		students = fileInfo.retrievingDataFromFile();
		System.out.printf("%-10s%-20s%-20s\n", "Id", "Name", "Age");
		for (Student std : students) {
			System.out.printf("%-10s%-20s%-20d\n", std.getId(), std.getName(), std.getAge());
		}
	}

	//TO UPDATE/EDIT STUDENT'S DETAILS FROM FILE
	public void updateStudentDetails() {
		displayAllStudentsDetails();
		System.out.print("Please select the student's id you want to update: ");
		Student student = new Student();
		int idToUpdate = sc.nextInt(); sc.nextLine();
		//Checking if the id is present or not
		boolean containsId = fileInfo.checkingId(idToUpdate);
		//Proceeding only if id is present
		if(containsId) {
			student.setId(idToUpdate);

			System.out.print("Enter the name: ");
			String name = sc.nextLine();
			student.setName(name);

			System.out.print("Enter the age: ");
			int age = sc.nextInt();
			student.setAge(age);
			
			fileInfo.updateDataOfTheFile(student);
		}
	}
	
	//TO DELETE STUDENT'S DETAILS FROM THE FILE
	public void deleteStudentDetails() {
		System.out.println();
		displayAllStudentsDetails();
		System.out.print("Please select the student's id you want to delete: ");
		int idToDelete = sc.nextInt(); sc.nextLine();
		//Checking if the id is present or not
		boolean containsId = fileInfo.checkingId(idToDelete);
		//Proceeding only if id is present
		if(containsId) {
			fileInfo.deletingDataFromTheFile(idToDelete);
		}
	}
}