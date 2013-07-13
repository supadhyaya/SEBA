package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Course extends Model {
	private long courseId;
	private String courseName;
	private int courseRating;
	
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseRating() {
		return courseRating;
	}
	public void setCourseRating(int courseRating) {
		this.courseRating = courseRating;
	}
	
	
}
