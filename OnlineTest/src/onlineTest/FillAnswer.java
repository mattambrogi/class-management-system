package onlineTest;

public class FillAnswer implements Answer {

	int examNum, questionNum;
	String[] givAnswer;

	/*
	 * adds an answer to an exam
	 * 
	 * Can get answer
	 */
	public FillAnswer(int examNum, int questionNum, String[] answer) {
		this.examNum = examNum;
		this.questionNum = questionNum;
		this.givAnswer = answer;
	}
	
	public String[] getGivAnswer() {
		return givAnswer;
	}
}
