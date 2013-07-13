package models;

import models.CourseRatingAnswer.RatingAnswerType;

public class CourseQuestionnaire {

	private long questionId;
	private String answer;
	private RatingAnswerType answerType;
	
	public long getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public RatingAnswerType getAnswerType() {
		return answerType;
	}
	
	public void setAnswerType(RatingAnswerType answerType) {
		this.answerType = answerType;
	}

	
}
