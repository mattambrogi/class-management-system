package onlineTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SystemManager implements Manager {

	// mapping exam to map of question and answers (created in exam)
	HashMap<Integer, HashMap<Integer, Answer>> allExams = new HashMap<>(); // don't think I use this

	// maps Id to exam object
	public HashMap<Integer, Exam> allExamsTwo = new HashMap<>();
	// list of all students
	public ArrayList<Student> students = new ArrayList<>(); // list of all students
	// instance variables for cutoffs
	String[] letters;
	double[] cutoff;

	/*
	 * Add new exam
	 */
	public boolean addExam(int examId, String title) {
		// create object and add exam to map
		if (allExamsTwo.containsKey(examId)) {
			return false;
		} else {
			Exam exam = new Exam(examId, title);
			allExamsTwo.put(examId, exam);
		}
		return true;

	}

	/*
	 * Add new true false question
	 */
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		// create object
		TrueQuestion newQuestion = new TrueQuestion(examId, questionNumber, text, points, answer);
		// add question to exam's map of numbers to questions
		allExamsTwo.get(examId).addQuestion(questionNumber, newQuestion);

	}

	/*
	 * Add new multiple choice question
	 */
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		// create object
		MultQuestion newQuestion = new MultQuestion(examId, questionNumber, text, points, answer);
		// add question to exam's map of numbers to questions
		allExamsTwo.get(examId).addQuestion(questionNumber, newQuestion);

	}

	/*
	 * Add new fill in the blank question
	 */
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
		// create object
		FillQuestion fillQuestion = new FillQuestion(examId, questionNumber, text, points, answer);
		// add question to exam's map of numbers to questions
		allExamsTwo.get(examId).addQuestion(questionNumber, fillQuestion);

	}

	/*
	 * Get exam get as a string
	 */
	public String getKey(int examId) {

		if (!(allExamsTwo.containsKey(examId))) {
			return "Exam not found";
		}

		String key = "";
		for (int iD : allExamsTwo.keySet()) {
			// find object for this key
			if (iD == examId) {
				// get exam objet
				Exam exam = allExamsTwo.get(iD);
				// get exam key
				HashMap<Integer, Question> examInfo = exam.examKey;
				for (Integer qId : examInfo.keySet()) {
					// handle True/Flase Question case
					if (examInfo.get(qId) instanceof TrueQuestion) {
						TrueQuestion tQuestion = (TrueQuestion) examInfo.get(qId); // will it ever be instance of if I
																					// cast when add
						boolean correct = tQuestion.getExpAnswer();
						String correctAnswer = String.valueOf(correct);
						correctAnswer = correctAnswer.substring(0, 1).toUpperCase() + correctAnswer.substring(1);
						String points = String.valueOf(tQuestion.points);
						String text = tQuestion.text;
						key = key + "Question Text: " + text + "\n" + "Points: " + points + "\n" + "Correct Answer: "
								+ correctAnswer + "\n";
					}
					// Handle multiple choice case
					if (examInfo.get(qId) instanceof MultQuestion) {
						MultQuestion mQuestion = (MultQuestion) examInfo.get(qId);
						String[] correct = mQuestion.getExpAnswer();
						String correctAnswer = "[";
						// String[] correctSort = Arrays.sort(correct, String.CASE_INSENSITIVE_ORDER);
						ArrayList<String> correctList = new ArrayList<String>(Arrays.asList(correct));
						correctList.sort(String.CASE_INSENSITIVE_ORDER);
						for (String s : correctList) {
							correctAnswer = correctAnswer + s + ",";
						}
						correctAnswer = correctAnswer.substring(0, correctAnswer.length() - 1) + "]";
						String points = String.valueOf(mQuestion.points);
						String text = mQuestion.text;
						key = key + "Question Text: " + text + "\n" + "Points: " + points + "\n" + "Correct Answer: "
								+ correctAnswer + "\n";

					}
					// Handle fill in the blanks case
					if (examInfo.get(qId) instanceof FillQuestion) {
						FillQuestion fQuestion = (FillQuestion) examInfo.get(qId);
						String[] correct = fQuestion.getExpAnswer();
						String correctAnswer = "[";
						ArrayList<String> correctList = new ArrayList<String>(Arrays.asList(correct));
						correctList.sort(String.CASE_INSENSITIVE_ORDER);
						for (String s : correctList) {
							correctAnswer = correctAnswer + s + ",";
						}
						correctAnswer = correctAnswer.substring(0, correctAnswer.length() - 1) + "]";
						String points = String.valueOf(fQuestion.points);
						String text = fQuestion.text;
						key = key + "Question Text: " + text + "\n" + "Points: " + points + "\n" + "Correct Answer: "
								+ correctAnswer + "\n";
					}
				}
			}
		}

		return key;
	}

	/*
	 * Add new student
	 */
	public boolean addStudent(String name) {
		Student student = new Student(name);
		// return false if student exists
		for (Student s : students) {
			if (name.equals(s.studentName)) {
				return false;
			}
		}
		students.add(student);
		return true;

	}

	/*
	 * Add answer for true false question
	 */
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		// create answer
		TrueAnswer tAnswer = new TrueAnswer(examId, questionNumber, answer);
		/*
		 * Find student and add answer to student map of exams to question Ids and
		 * answers
		 */
		for (Student s : students) {
			if (studentName.equals(s.studentName)) {
				s.addAnswer(examId, questionNumber, tAnswer);
			}
		}
	}

	/*
	 * Add answer for multiple choice question
	 */
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		MultAnswer mAnswer = new MultAnswer(examId, questionNumber, answer);
		/*
		 * Find student and add answer to student map of exams to question Ids and
		 * answers
		 */
		for (Student s : students) {
			if (studentName.equals(s.studentName)) {
				s.addAnswer(examId, questionNumber, mAnswer);
			}
		}
	}

	/*
	 * Add answer for fill in the blanks question
	 */
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		FillAnswer fillAnswer = new FillAnswer(examId, questionNumber, answer);
		/*
		 * Find student and add answer to student map of exams to question Ids and
		 * answers
		 */
		for (Student s : students) {
			if (studentName.equals(s.studentName)) {
				s.addAnswer(examId, questionNumber, fillAnswer);
			}
		}
	}

	/*
	 * Get score for a given student and exam
	 */
	public double getExamScore(String studentName, int examId) {

		HashMap<Integer, Answer> examAnswers = new HashMap<>();
		HashMap<Integer, Question> examKey = new HashMap<>();
		Student curr = new Student(studentName);
		// find exam in map
		for (int iD : allExamsTwo.keySet()) {
			if (iD == examId) {
				// find student in list
				for (Student s : students) {
					if (s.studentName.equals(studentName)) {
						// get map of questionIds and answers
						curr = s;
						examAnswers = s.getAnswers(iD);
					}
				}

				// get exam key map of questions ids and question objects
				examKey = allExamsTwo.get(iD).examKey;
			}
		}

		double points = 0;
		// grade each question in exam
		for (int question : examKey.keySet()) {
			double questionPoints = grade(question, examKey, examAnswers);
			// add points to student points map
			curr.addPoints(examId, question, questionPoints);
			// add points to total
			points = points + questionPoints;
		}

		return points;
	}

	/*
	 * Helper method to grade question
	 */
	private double grade(int question, HashMap<Integer, Question> examKey, HashMap<Integer, Answer> examAnswers) {
		// Create question objects
		TrueQuestion tQuestion;
		MultQuestion multQuestion;
		FillQuestion fillQuestion;
		double points = 0.0;

		/*
		 * True False Case
		 */
		if (examKey.get(question) instanceof TrueQuestion) {
			// cast
			tQuestion = (TrueQuestion) examKey.get(question);
			// use map to get correct answer
			boolean correct = tQuestion.getExpAnswer();
			// if answer is not correct type, get no points
			if (!(examAnswers.get(question) instanceof TrueAnswer)) {
				points = 0;
				return points;
			}
			// use answer map to get answer
			TrueAnswer tAnswer = (TrueAnswer) examAnswers.get(question);
			boolean answer = tAnswer.getTrueGivAnswer();
			// compare answers, if not the same, no points
			if (answer != correct) {
				points = 0.0;
				return points;
			}
			// otherwise, get full points
			points = tQuestion.getPoints(); // need to figure out default
		}

		/*
		 * Multiple Choice Case
		 */
		if (examKey.get(question) instanceof MultQuestion) {
			multQuestion = (MultQuestion) examKey.get(question);
			// get correct answer
			String[] correct = multQuestion.getExpAnswer();
			// get given answer
			MultAnswer mAnswer = (MultAnswer) examAnswers.get(question);
			String[] answerString = mAnswer.getGivAnswer();
			// if not equal, get no points
			if (!(Arrays.equals(answerString, correct))) {
				points = 0.0;
				return points;
			}
			// otherwise, get full points
			points = multQuestion.getPoints();
		}

		/*
		 * Fill in the Blanks Question Case
		 */
		if (examKey.get(question) instanceof FillQuestion) {
			fillQuestion = (FillQuestion) examKey.get(question);
			// get correct answer
			String[] correct = fillQuestion.getExpAnswer();
			// get given answer
			FillAnswer fillAnswer = (FillAnswer) examAnswers.get(question);
			String[] answerString = fillAnswer.getGivAnswer();

			// if same, get all points
			if ((Arrays.equals(answerString, correct))) {
				fillQuestion.getPoints();
			}
			// otherwise, check each
			int count = 0;
			for (String s : answerString) {
				for (String s2 : correct) {
					if (s.equals(s2)) {
						count++;
					}
				}
			}

			points = ((double) count / (double) correct.length) * fillQuestion.getPoints();
			return points;

		}

		return points;
	}

	/*
	 * Grading report for given student and exam
	 */
	public String getGradingReport(String studentName, int examId) {
		// update exam grade
		getExamScore(studentName, examId);
		// create objects
		Student student = new Student(studentName);
		Exam exam = new Exam(0, null);
		// find student
		for (Student s : students) {
			if (s.studentName.equals(studentName)) {
				student = s;
			}
		}
		// find exam
		for (int iD : allExamsTwo.keySet()) {
			if (iD == examId) {
				exam = allExamsTwo.get(iD);
			}
		}

		// map of student points for each question(created during grading)
		HashMap<Integer, Double> questionPoints = student.allPoints.get(examId);

		String report = "";
		double totalEarned = 0.0;
		double totalPoss = 0.0;
		// for each, get points recieved and possible
		for (Integer q : questionPoints.keySet()) {
			int qNum = q;
			double points = questionPoints.get(q);
			totalEarned = totalEarned + points;
			double possible = exam.examKey.get(q).getPoints();
			totalPoss = totalPoss + possible;
			report = report + "Question #" + qNum + " " + points + " points out of " + possible + "\n";
		}
		// end with final score out of total possible points
		report = report + "Final Score: " + totalEarned + " out of " + totalPoss;

		return report;
	}

	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		// String[]{"A","B","C","D","F"}, new double[] {90,80,70,60,0}.
		letters = letterGrades;
		cutoff = cutoffs;

	}

	public double getCourseNumericGrade(String studentName) {
		double total = 0;
		int count = 0;
		// find student and get each exam score
		for (Student s : students) {
			if (studentName.equals(s.studentName)) {
				for (Integer i : s.answers.keySet()) {
					total = total + getExamScore(studentName, i);
					count++;
				}
			}
		}
		double grade;
		grade = total / ((double) count);
		// return average exam score
		return grade;
	}

	public String getCourseLetterGrade(String studentName) {
		// get course grade
		double grade = getCourseNumericGrade(studentName);
		String letterGrade = null;
		// assign numeric grade to corresponding letter
		for (int i = 0; i < cutoff.length; i++) {
			if (grade >= cutoff[i]) {
				letterGrade = letters[i];
				return letterGrade;
			}
		}
		return letterGrade;

	}

	public String getCourseGrades() {
		return null;
	}

	/*
	 * return max score on exam
	 */
	public double getMaxScore(int examId) {
		Exam exam = new Exam(0, null);
		ArrayList<Double> grades = new ArrayList<>();
		// find exam
		for (int iD : allExamsTwo.keySet()) {
			if (iD == examId) {
				exam = allExamsTwo.get(iD);
			}
		}
		// for each student, if they've taken the exam, add score to list
		for (Student s : students) {
			if (s.answers.containsKey(examId)) {
				grades.add(getExamScore(s.studentName, examId));
			}
		}
		// find max in list
		double max = 0.0;
		for (Double score : grades) {
			if (score > max) {
				max = score;
			}
		}
		return max;

	}

	/*
	 * return min exam score Same logic as max
	 */
	public double getMinScore(int examId) {
		Exam exam = new Exam(0, null);
		ArrayList<Double> grades = new ArrayList<>();
		for (int iD : allExamsTwo.keySet()) {
			if (iD == examId) {
				exam = allExamsTwo.get(iD);
			}
		}
		for (Student s : students) {
			if (s.answers.containsKey(examId)) {
				grades.add(getExamScore(s.studentName, examId));
			}
		}
		double min = 100.00;
		for (Double score : grades) {
			if (score < min) {
				min = score;
			}
		}
		return min;
	}

	/*
	 * Return average exam score Same logic as above but return average of all
	 * student scores
	 */
	public double getAverageScore(int examId) {
		Exam exam = new Exam(0, null);
		ArrayList<Double> grades = new ArrayList<>();
		for (int iD : allExamsTwo.keySet()) {
			if (iD == examId) {
				exam = allExamsTwo.get(iD);
			}
		}
		for (Student s : students) {
			if (s.answers.containsKey(examId)) {
				grades.add(getExamScore(s.studentName, examId));
			}
		}
		double avgScore;
		double total = 0;
		int count = 0;
		for (Double score : grades) {
			total = total + score;
			count++;
		}
		avgScore = total / ((double) count);
		return avgScore;
	}

	public void saveManager(Manager manager, String fileName) {

	}

	public Manager restoreManager(String fileName) {
		return null;
	}

}
