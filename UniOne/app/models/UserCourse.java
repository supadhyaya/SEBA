package models;

import play.db.jpa.Model;

public class UserCourse extends Model {

	private long userId;
	private long courseId;
	
	public UserCourse(long courseId, long userId) {
		
		super();
		this.courseId = courseId;
		this.userId = userId;
		
		create();
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getCourseId() {
		return courseId;
	}
	
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	
	
}
