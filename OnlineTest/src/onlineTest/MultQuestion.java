package onlineTest;

public class MultQuestion implements Question {

	int examId, questionNumber;
	String text;
	double points;
	String[] answer;
	
	/*
	 * does the format even really matter?
	 */
	
	public MultQuestion(int examId, int questionNumber, String text,
			double points, String[] answer) {
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.answer = answer;
	}
	
	public String[] getExpAnswer() {
		return answer;
	}
	
	public double getPoints() {
		return points;
	}
	
	public String getText() {
		return text;
	}
	
}
