package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Question extends Model {
	
	private long questionId;
	private String questionText;
	private Date questionPostedOn;
	private long numberOfLikes;
	private long courseId;
	
	public Question(String questionText, Date posetedOn, long courseId) {
		super();
		this.questionText = questionText;
		this.questionPostedOn = posetedOn;
		this.courseId = courseId;
		this.numberOfLikes = 0;
		create();
	}
	
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
	
	public long getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(long numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	
	public static List<Answer> findAllAnswers(long questionId) throws Exception {
		List<Answer> answers = new ArrayList<Answer>();
		try {
			answers = Answer.find("questionId", questionId).fetch();
		} catch(Exception e) {
			throw new Exception();
		}
		return answers;
	}
	
	public static Question findByQuesionId(long quesId) {
	    return find("questionId", quesId).first();
    }

	
	
}
