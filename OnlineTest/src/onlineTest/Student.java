package onlineTest;

import java.util.HashMap;

public class Student {

	/*
	 * Name
	 * Exams
	 * 
	 */
	String studentName;
	HashMap<Integer, HashMap<Integer, Answer>> answers = new HashMap<>(); 
	//grades map
	HashMap<Integer, HashMap<Integer, Double>> allPoints = new HashMap<>(); 

	
	public Student(String studentName) {
		this.studentName = studentName;
	}
	
	public void addAnswer(int examId, int questionId, Answer answer) {
	/*
	 * want map <examId,<questionId, Answer>>
	 * check if map has examID, if so, app to map
	 * If not, create map, put in
	 * 
	 * Check if instance of?
	 */
		HashMap<Integer, Answer> qAndA;
		if(!(answers.containsKey(examId))) {
			qAndA = new HashMap<>();
			qAndA.put(questionId, answer);
			answers.put(examId, qAndA);
		}else {
			qAndA = answers.get(examId);
			qAndA.put(questionId, answer);     
			//answers.put(examId, qAndA);  already in bigger map
		}
	
	}
	
	public HashMap<Integer, Answer> getAnswers(int examId){
		for (int exam : answers.keySet()) {
			if(exam == examId) {
				return answers.get(exam);
			}
		}
		return null; //questionable
	}
	
	
	//map with each exam, question, and points
	public void addPoints(int examId, int questionId, Double points) {
		HashMap<Integer, Double> qPoints;
		if(!(allPoints.containsKey(examId))) {
			qPoints = new HashMap<>();
			qPoints.put(questionId, points);
			allPoints.put(examId, qPoints);
		}else {
			qPoints = allPoints.get(examId);
			qPoints.put(questionId, points);     
			//answers.put(examId, qAndA);  already in bigger map
		}
	}
	
	public String getStudentName() {
		return studentName;
	}
	
}
