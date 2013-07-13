package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Answer extends Model {
	private long answerId;
	private String answerText;
	private int answerNumberOfLikes;
	private Date answerPostedOn;
	
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
	public int getAnswerNumberOfLikes() {
		return answerNumberOfLikes;
	}
	public void setAnswerNumberOfLikes(int answerNumberOfLikes) {
		this.answerNumberOfLikes = answerNumberOfLikes;
	}
	public Date getAnswerPostedOn() {
		return answerPostedOn;
	}
	public void setAnswerPostedOn(Date answerPostedOn) {
		this.answerPostedOn = answerPostedOn;
	}
	
}
