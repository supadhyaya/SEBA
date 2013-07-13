package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Question extends Model {
	private long questionId;
	private String questionText;
	private Date questionPostedOn;
	
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public Date getQuestionPostedOn() {
		return questionPostedOn;
	}
	public void setQuestionPostedOn(Date questionPostedOn) {
		this.questionPostedOn = questionPostedOn;
	}
	
}
