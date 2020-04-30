package tests;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import onlineTest.Answer;
import onlineTest.Exam;
import onlineTest.FillAnswer;
import onlineTest.MultAnswer;
import onlineTest.Question;
import onlineTest.Student;
import onlineTest.SystemManager;
import onlineTest.TrueQuestion;

public class StudentTests {
	SystemManager manager = new SystemManager();
	
	@Test
	public void addExamTest() {
		manager.addExam(1, "Calc Test 1");
		manager.addExam(2, "Thermo");
		manager.addExam(3, "Calc Test 2");
		String exams = "";
		for(Integer iD : manager.allExamsTwo.keySet()) {
			exams = exams + "Exam Id: " + iD + " Name: " +
					manager.allExamsTwo.get(iD).getTitle() + "\n";
		}
		//System.out.println(exams);
	}


	@Test
	public void addTrueFalseTest() {
		manager.addExam(4, "Test");
		manager.addTrueFalseQuestion(4, 1, "does this work?", 10.00, true);
		manager.addTrueFalseQuestion(4, 2, "does this not work?", 10.00, false);
		HashMap<Integer, Question> examKey = manager.allExamsTwo.get(4).getKey();
		String questions = "";
		for (Integer iD : examKey.keySet()) {
			boolean answer = true;
			Question q = examKey.get(iD);
			if(q instanceof TrueQuestion) {
				answer = ((TrueQuestion) q).getExpAnswer();
			}
			questions = questions + q.getPoints() + " " + String.valueOf(answer) + "\n";
		}
		//System.out.println(questions);
		
		
		//I may need to go back and change it so that they all have a getAnswer method
		//get text method
		
		//that way any type of answer can give me text, points, answers
	}
	
	@Test
	public void getKeyTest() {
		//make exam, add questions, get key
		//Exam exam = new Exam(1, "Math Exam"); have to add exam
		manager.addExam(1, "Math Exam");
		manager.addTrueFalseQuestion(1, 1, "True or nah?", 1, true);
		manager.addTrueFalseQuestion(1, 2, "Also or nah?", 1, true);
		manager.addTrueFalseQuestion(1, 3, "What about this?", 1, false);
		String key = manager.getKey(1);
		//System.out.println(key);
		/*
		 * should be good
		 */
	}
	
	@Test
	public void getExamScoreTest() {
		String studentName;
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		manager.addTrueFalseQuestion(10, 1, "Abstract classes cannot have constructors.", 2, false);
		manager.addTrueFalseQuestion(10, 2, "The equals method returns a boolean.", 4, true);
		manager.addTrueFalseQuestion(10, 3, "Identifiers can start with numbers.", 3, false);
		answer.append(manager.getKey(10));
		
		studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		/*
		 * Test add answer
		 */
		manager.answerTrueFalseQuestion(studentName, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName, 10, 2, false);
		manager.answerTrueFalseQuestion(studentName, 10, 3, false);
		/*
		 * Test exam score
		 */
		answer.append("\nExam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		
		//System.out.println(answer);
		
	}
	
	@Test
	public void multipleChoice() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		
		double points;
		String questionText = "Which of the following are valid ids?\n";
		questionText += "A: house   B: 674   C: age   D: &";
		points = 3;
		manager.addMultipleChoiceQuestion(10, 1, questionText, points, new String[]{"A","C"});
		
		String studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		manager.answerMultipleChoiceQuestion(studentName, 10, 1, new String[]{"A", "C"});
		answer.append("Report for " + studentName + "\n" + manager.getGradingReport(studentName, 10));
		//System.out.println(answer);
		
		HashMap<Integer, Answer> examAnswers = new HashMap<>();
		for(Student s: manager.students) {
			if(s.getStudentName().equals(studentName)) {
				//now grab answers
				//curr = s;
				examAnswers = s.getAnswers(10);
			}
		}
		for(Integer i : examAnswers.keySet()) {
			Answer a = examAnswers.get(i);
//			if( a instanceof MultAnswer) {
//				System.out.println("boiii");
//			}
			MultAnswer m = (MultAnswer) a;
			String[] things = m.getGivAnswer();
			for(String s : things) {
				//System.out.println(s);
			}
		}
	}
	
	@Test
	public void fillQuestionTest() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		double points;
		
		String questionText = "Name two types of initialization blocks.";
		points = 4;
		manager.addFillInTheBlanksQuestion(10, 1, questionText, points, new String[]{"static","non-static"});
	
		questionText = "Name the first three types of primitive types discussed in class.";
		points = 6;
		manager.addFillInTheBlanksQuestion(10, 2, questionText, points, new String[]{"int","double","boolean"});
		
		String studentName = "Sanders,Laura";
		manager.addStudent(studentName);
		manager.answerFillInTheBlanksQuestion(studentName, 10, 1, new String[]{"static"});
		manager.answerFillInTheBlanksQuestion(studentName, 10, 2, new String[]{"int", "boolean"});		
		answer.append("\nReport for " + studentName + "\n" + manager.getGradingReport(studentName, 10));
		
		//System.out.println(answer);
		
		//trying to find issue, let's look at answers
		HashMap<Integer, Answer> examAnswers = new HashMap<>();
		for(Student s: manager.students) {
			if(s.getStudentName().equals(studentName)) {
				//now grab answers
				//curr = s;
				examAnswers = s.getAnswers(10);
			}
		}
		for(Integer i : examAnswers.keySet()) {
			Answer a = examAnswers.get(i);
//			if( a instanceof MultAnswer) {
//				System.out.println("boiii");
//			}
			FillAnswer f = (FillAnswer) a;
			String[] things = f.getGivAnswer();
			//System.out.print("Question " + i +": ");
			for(String s : things) {
				//System.out.println(s);
			}
		}
		
		
	}
	
	
	
}
	

