package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Answer;
import models.Course;
import models.Question;

import play.mvc.Controller;

public class CourseController extends Controller {

	// function to get the Searched Courses
	public static void getSearchedCourses(int universityId, String query) {
	
		List<Course> courses = new ArrayList<Course>();
		
		try {
			courses = Course.findByCourseName(query);
		} catch (Exception e) {
			e.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Something went wrong while searching...\"}");
		}
		
		System.out.println(courses.toString());
		
		renderJSON(courses);
	}
	
	public static void getCourseNameById(int id) {
		
		List<Course> result = null;
		try {
			result = Course.findCourseNameById(id);
		} catch (Exception e) {
			renderJSON("{\"error\": true, \"message\": \"Something went wrong while searching...\"}");
		}
		
		renderJSON(result);
	}
	
	// function to get all the courses
	public static void getAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		try {
			courses = Course.findAllCourses();
		} catch (Exception e) {
			e.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Something went wrong retrieving the courses...\"}");
		}
		
		renderJSON(courses);
	}
	
	// function to get all the questions related to the course
	public static void getAllAnswers(long questionId) {
		List<Answer> answers = new ArrayList<Answer>();
		
		try {
			answers = Question.findAllAnswers(questionId);
		} catch(Exception e) {
			e.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Something went wrong while finding the Questions related to this course...\"}");
		}
		
		renderJSON(answers);
	}
	
	// function to get all the questions related to the course
	public static void getAllQuestions(long id) {
		List<Question> questions = new ArrayList<Question>();
		
		try {
		questions = Course.findAllQuestions(id);
		} catch(Exception e) {
			e.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Something went wrong while finding the Questions related to this course...\"}");
		}
		
		renderJSON(questions);
	}
	
	// function to rate the question and increase its likes count.
	public static void rateQuestion(long questionId) {
		Question question = null;
		try {
			question = Question.findByQuesionId(questionId);
			long likes = question.getNumberOfLikes();
			likes++;
			question.setNumberOfLikes(likes);
			question.save();
			} catch (Exception e) {
			e.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Invalide question id\"}");
		
		}
		renderJSON(question);
		
	}
	
	
	// function to rate the question and increase its likes count.
	public static void rateAnswer(long answerId) {
		Answer answer = null;
		try {
			answer = Answer.findByAnswerId(answerId);
			long likes = answer.getAnswerNumberOfLikes();
			likes++;
			answer.setAnswerNumberOfLikes(likes);
			answer.save();
		} catch (Exception e) {
			e.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Invalide answer id\"}");
		
		}
		renderJSON(answer);
	}
	
	// function to post question
	public static void postQuestion(String questionText, long courseId) {
	
		Question question = null;
		try {
			Date postedOn = new Date();
			question = new Question(questionText, postedOn, courseId); 
			
		} catch (Exception e) {
			e.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Something went wrong while inserting the question\"}");
		
		}
		renderJSON(question);

	}
	
	// function to post answer
	public static void postAnswer(String answerText, long questionId) {
		
		Answer answer = null;
		try {
			Date postedOn = new Date();
			answer = new Answer(answerText, postedOn, questionId); 
			
		} catch (Exception e) {
			e.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Something went wrong while inserting the ansswer\"}");
		
		}
		renderJSON(answer);
	}
}
