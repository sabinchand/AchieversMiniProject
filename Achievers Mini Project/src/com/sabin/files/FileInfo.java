package com.sabin.files;

import java.io.*;
import java.util.ArrayList;

import com.sabin.student.Student;

public class FileInfo {

	File file = new File("./src/com/sabin/files/studentDetails.txt");
	File tempFile = new File("./src/com/sabin/files/temporaryFile.txt");

	// CREATING A NEW FILE IF IT DOESN'T EXIST
	public void createFile() {
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// GETTING ID OF EACH STUDENT FROM THE FILE
	public int getId() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String lastLine = null;
			while (br.ready()) {
				lastLine = br.readLine();
			}
			br.close();
			if (lastLine != null && !lastLine.isEmpty()) {
				String[] eachWordofLastLine = lastLine.split("-");
				int Id = Integer.parseInt(eachWordofLastLine[0]);
				return Id + 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	// WRITING DATA TO THE FILE
	public void writingDataToFile(Student student) {
		try {
			FileWriter fw = new FileWriter(file, true);
			String data = student.getId() + "-" + student.getName() + "-" + student.getAge();
			fw.write(data + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// RETRIEVING DATA FROM THE FILE
	public ArrayList<Student> retrievingDataFromFile() {
		ArrayList<Student> students = new ArrayList<>(); // This line must be written here only. Else, every time we perform retrieve, data will be added to the previous one.
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (br.ready()) {
				Student student = new Student();
				String eachLine = br.readLine();
				String[] eachWord = eachLine.split("-");
				student.setId(Integer.parseInt(eachWord[0]));
				student.setName(eachWord[1]);
				student.setAge(Integer.parseInt(eachWord[2]));
				students.add(student);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	//UPDATING DATA OF THE FILE
	public void updateDataOfTheFile(Student student) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
				while(br.ready()) {
					String eachLine = br.readLine();
					String[] eachWord = eachLine.split("-");
					if(student.getId()!=Integer.parseInt(eachWord[0])) {
						pw.println(eachLine);
						pw.flush();
					}else {
						pw.println(student.getId() + "-" + student.getName() + "-" + student.getAge());
						pw.flush();
					}
				}
				pw.close(); br.close();
				//Delete the original file
	            if (!file.delete()) {
	                System.out.println("Could not delete file");
	                return;
	            }
	            //Rename the new file to the filename the original file had.
	            if (!tempFile.renameTo(file)) {
	                System.out.println("Could not rename file");
	            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//DELETING DATA FROM THE FILE
	public void deletingDataFromTheFile(int idToDelete) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
				while(br.ready()) {
					String eachLine = br.readLine();
					String[] eachWord = eachLine.split("-");
					if(idToDelete != Integer.parseInt(eachWord[0])) {
						pw.println(eachLine);
						pw.flush();
					}
				}
				pw.close(); br.close();
				//Delete the original file
	            if (!file.delete()) {
	                System.out.println("Could not delete file");
	                return;
	            }
	            //Rename the new file to the filename the original file had.
	            if (!tempFile.renameTo(file)) {
	                System.out.println("Could not rename file");
	            }
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//CHECKING INPUT ID IN FILE BEFORE PROCEEDING FURTHER
	public boolean checkingId(int id) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			boolean containsId = false;
			while(br.ready()) {
				String eachLine = br.readLine();
				String[] eachWord = eachLine.split("-");
				if(id == Integer.parseInt(eachWord[0])) {
					containsId = true;
					br.close();
					return containsId;
				}
			} 
			br.close();
		}
		catch (IOException e) {
            e.printStackTrace();
		}
		System.out.println("Id not found..");
		return false;
	}
}
