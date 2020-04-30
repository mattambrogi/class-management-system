package onlineTest;

import java.util.HashMap;

public class Exam {

	int examNum;
	int questionNum;  //idt I want this here
	String title;
	boolean answer;   //idt I want this here
	
	
	HashMap<Integer, Question> examKey = new HashMap<>(); //question has answer

	// set of questions
	// set of answers
	// add each to hashmap

	// hashmap of questionId and answers

	//initalize exam with Id and name
	

	public Exam(int examId, String title) {
		examNum = examId;
		this.title = title;
	}
	
	
	//create question object and add to key
/*	public void addTrueQuestion(int questionNum, String text, double points, boolean answer) {
		TrueQuestion question = new TrueQuestion(examNum, questionNum, text, points, answer);
		//what is even the point of a question object?
		examKey.put(question.questionNum, question.expAnswer);
	}*/
	
	public HashMap<Integer, Question> getKey(){
		return examKey;
	}
	
	
	
	//don't think this need to be specific
	public void addQuestion(int questionNum, Question q) { //maybe question object doesn't need number
		examKey.put(questionNum, q);
}
	public String getTitle() {
		return title;
	}
	
	
	

}
