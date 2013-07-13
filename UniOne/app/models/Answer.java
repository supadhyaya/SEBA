package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Answer extends Model {
	private long answerId;
	private String answerText;
	private long answerNumberOfLikes;
	private Date answerPostedOn;
	private long questionId;
	
	public Answer(String answerText, Date posetedOn, long questionId) {
		super();
		this.answerText = answerText;
		this.answerPostedOn = posetedOn;
		this.questionId = questionId;
		this.answerNumberOfLikes = 0;
		create();
	}
	
	public long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public long getAnswerNumberOfLikes() {
		return answerNumberOfLikes;
	}
	public void setAnswerNumberOfLikes(long answerNumberOfLikes) {
		this.answerNumberOfLikes = answerNumberOfLikes;
	}
	public Date getAnswerPostedOn() {
		return answerPostedOn;
	}
	public void setAnswerPostedOn(Date answerPostedOn) {
		this.answerPostedOn = answerPostedOn;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	
	public static Answer findByAnswerId(long ansId) {
	    return find("answerId", ansId).first();
    }
}
