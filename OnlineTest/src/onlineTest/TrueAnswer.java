package onlineTest;

public class TrueAnswer implements Answer {

	int examNum, questionNum;
	boolean givAnswer;

	/*
	 * adds an answer to an exam
	 * 
	 * Can get answer
	 */
	public TrueAnswer(int examNum, int questionNum, boolean answer) {
		this.examNum = examNum;
		this.questionNum = questionNum;
		this.givAnswer = answer;
	}
	
	public boolean getTrueGivAnswer() {
		return givAnswer;
	}
}
