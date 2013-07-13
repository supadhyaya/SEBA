package models;

import models.CourseRatingAnswer.RatingAnswerType;
import play.db.jpa.Model;

public class CourseRating extends Model {

	private long courseId;
	private long userId;
	private long questionId;
	private long answerId;

	public CourseRating (long courseId, long userId, long questionId, String answer, RatingAnswerType answerType) {
		super();
		this.courseId = courseId;
		this.userId = userId;
		this.questionId = questionId;
		
		CourseRatingAnswer courseRatingAnswer = new CourseRatingAnswer(questionId, answerType, answer);
		this.answerId = courseRatingAnswer.id;
		
		create();
	}
	
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}
	
	
}
