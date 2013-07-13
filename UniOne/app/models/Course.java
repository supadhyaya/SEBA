package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Course extends Model {
	private long courseId;
	private String courseName;
	private int courseRating;
	private int universityid;
	@OneToMany
	List<Question> questions;
	
	public long getCourseId() {
		return courseId;
	}
	public long getUniversityId() {
		return universityid;
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
	
	public static List<Course> findByCourseName(String courseName) throws Exception {
        List<Course> courses = new ArrayList<Course>();
        
        try{
        	courses = Course.find("courseName like ?", "%"+courseName+"%").fetch();
        	System.out.println(courses.get(0).courseName);
        } catch(Exception exception) {
        	throw exception;
        }
        return courses;
	}
	
	public static List<Course> findCourseNameById(int courseId) throws Exception {
        List<Course> course;
        
        try{
        	course = Course.find("courseId", courseId).first();
        } catch(Exception exception) {
        	throw exception;
        }
        return course;
	}
	
	
	
	public static List<Question> findAllQuestions(long courseId) throws Exception {
		List<Question> questions = new ArrayList<Question>();
		try {
			questions = Question.find("courseId", courseId).fetch();
		} catch(Exception exception) {
			throw exception;
		}
		return questions;
	}
	
	public static List<Course> findAllCourses() throws Exception {
        List<Course> courses = new ArrayList<Course>();
        try{
        	courses = Course.findAll();
        } catch(Exception exception) {
        	throw exception;
        }
        return courses;
	}
	
}
