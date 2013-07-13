package models;

import play.db.jpa.Model;


public class CourseRatingAnswer extends Model {

	public enum RatingAnswerType {
		RADIO_BUTTON (0),
		TEXT (1);

	    private final int answerType;   

		RatingAnswerType(int answerType) {
	        this.answerType = answerType;
	    }

	    public int answerType() { 
	        return answerType; 
	    }

	}
	
	private RatingAnswerType answerType;
	private long questionId;
	private String anwser;

	CourseRatingAnswer(long questionId, RatingAnswerType answerType, String answer){
		super();
		this.questionId = questionId;
		this.answerType = answerType;
		this.anwser = answer;
		
		create();
		
		
	}
	
	public RatingAnswerType getAnswerType() {
		return answerType;
	}
	public void setAnswerType(RatingAnswerType answerType) {
		this.answerType = answerType;
	}

	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getAnwser() {
		return anwser;
	}

	public void setAnwser(String anwser) {
		this.anwser = anwser;
	}
	
}
