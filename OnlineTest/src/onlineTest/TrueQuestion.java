package onlineTest;

public class TrueQuestion implements Question {
	
	
	//should answer be separate object?
	
	int questionNum, examNum;
	double points;
	String text;
	boolean expAnswer;
	
	
	/*
	 * adds a question to exam
	 * 
	 * can get correct answer
	 */
	public TrueQuestion(int examNum, int questionNum, String text, double points, boolean answer) {
		this.examNum = examNum;
		this.questionNum = questionNum;
		this.text = text;
		this.points = points;
		this.expAnswer = answer;
		
		//may turn out this doesn't need an examNum or questionNum
	}
	
	public boolean getExpAnswer() {
		return expAnswer;
	}
	
	public double getPoints() {
		return points;
	}
	
	public String getText() {
		return text;
	}
	

}
