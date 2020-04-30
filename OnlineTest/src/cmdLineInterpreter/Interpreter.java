package cmdLineInterpreter;

import java.util.HashMap;
import java.util.Scanner;

import onlineTest.Answer;
import onlineTest.Exam;
import onlineTest.Student;
import onlineTest.SystemManager;

/**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 */
public class Interpreter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SystemManager manager = new SystemManager();
		
		String menu = "Menu:\nAdd a student: Press 1\nAdd an exam: Press 2\n"
				+ "Add a true/false question: Press 3\nAnswer a true/false question: Press 4\n"
				+ "Get student exam score: Press 5\n";
		System.out.println(menu);
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNext()) {
			
			int number = scanner.nextInt();
			switch(number) {
			/*
			 * add student
			 */
			case 1:
				System.out.print("Enter student name (Last,First no spaces):");
				String name = scanner.next();
				
				// check for alphabetic or , found on stack exchange
				if (name.matches("[a-zA-Z,]+")) {
					manager.addStudent(name);
					System.out.println("Student " + name + " added." + "\n");
					System.out.println(menu);
					/*checking that added
					for(Student s: manager.students) {
						if (s.getStudentName().equals(name)) {
							System.out.println("found em");
						}
					}*/
				}
				break;
				
			case 2:
				/*
				 * add exam
				 */
				System.out.println("Enter Exam Id Number:");
				int iD = scanner.nextInt();
				System.out.println("Enter Exam Name:"); //cant have spaces in name right now
				name = scanner.next();
				if (name.matches("[a-zA-Z]+")) {
					manager.addExam(iD, name);
					System.out.println("Exam added"+ "\n");
					System.out.println(menu);

				}
				break;
				
			case 3:
				/*
				 * Add question
				 */
				System.out.println("Enter the following in one lowercase string without spaces: "
						+ "Exam Id, Question Number, Question Text, "
						+ "Points, Answer"); 
				String input = scanner.next();
				input = input + scanner.nextLine();
				String[] fields = input.split(","); 
				int examId = Integer.parseInt(fields[0]);
				int questionNum = Integer.parseInt(fields[1]);
				String text = fields[2];
				Double points = (double)Integer.parseInt(fields[3]);
				boolean answer = Boolean.parseBoolean(fields[4]);
				manager.addTrueFalseQuestion(examId, questionNum, text, points,answer);
				System.out.println("Question Added" + "\n");
				/*
				 * checking if question actually added
				 
				for (int i : manager.allExamsTwo.keySet()) {
					// find object for this key
					if (i == examId) {
						Exam exam = manager.allExamsTwo.get(i);
						for(int i2 : exam.getKey().keySet()) {
							if(i2==questionNum) {
								//System.out.println("found question");
							}
						}
					}
				} */
				break;
				
			case 4:
				/*
				 * Add answer
				 */
				System.out.println("Enter the following in one lowercase string: Student Name, Exam Id,"
						+ " Question Number, Answer");
				input = scanner.nextLine();
				/*
				 * because student name has comma, take in first two fields
				 */
				input = input + scanner.nextLine();
				fields = input.split(","); //means can't have comma in text
				String studentName = fields[0] + "," + fields[1];
				examId = Integer.parseInt(fields[2]);
				int questionNumber = Integer.parseInt(fields[3]);
				answer = Boolean.parseBoolean(fields[4]);
				manager.answerTrueFalseQuestion(studentName, examId, questionNumber, answer);
				System.out.println("Answer Added" + "\n");
				System.out.println(studentName);
				/*checking if answer added
				for (int i : manager.allExamsTwo.keySet()) {
					// find object for this key
					if (i == examId) {
						System.out.println("found exam");
						Exam exam = manager.allExamsTwo.get(i);
						for(Student s: manager.students) {
							if(s.getStudentName().equals(studentName)) {
								System.out.println("found student");
								HashMap<Integer, Answer> answers = s.getAnswers(i);
								for(Integer i3: answers.keySet()) {
									if(i3 == questionNumber) {
										System.out.println("found answer");
									}
								}
							}
						}
					}
				} */
				
				break;
				
			case 5:
				/*
				 * Grade exam
				 */
				double score = 0.0;
				System.out.println("Enter: Exam Id, Student Name (last,first)");
				input = scanner.next();
				input = input + scanner.nextLine();
				fields = input.split(",");
				examId = Integer.parseInt(fields[0]);
				studentName = fields[1] +","+ fields[2];
				System.out.println(examId + " "+ studentName);
				
				
				for(Student s : manager.students) {
					if(s.getStudentName().equals(studentName)){
						//System.out.print("found student");
						score = manager.getExamScore(studentName, examId);
					}
				}
				System.out.println("Score: " + Double.toString(score) + "\n");
				break;
		}
		
		
		
	}
}
}
