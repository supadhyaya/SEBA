package models;

import models.CourseRatingAnswer.RatingAnswerType;
import play.db.jpa.Model;

public class CourseRatingQuestion extends Model {

	private String questionText;
	private long courseId;
	private RatingAnswerType answerType;
	
	
	public CourseRatingQuestion (String questionText, long courseId, RatingAnswerType answerType){
		
		super();
		this.questionText = questionText;
		this.courseId = courseId;
		this.answerType = answerType;
		
		create();
		
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public RatingAnswerType getAnswerType() {
		return answerType;
	}
	public void setAnswerType(RatingAnswerType answerType) {
		this.answerType = answerType;
	}
}
