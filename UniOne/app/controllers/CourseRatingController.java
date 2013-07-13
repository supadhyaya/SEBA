package controllers;


import java.util.ArrayList;
import java.util.List;

import models.Answer;
import models.CourseQuestionnaire;
import models.CourseRating;
import models.CourseRatingAnswer.RatingAnswerType;
import models.CourseRatingQuestion;
import models.UserCourse;
import play.mvc.Controller;

public class CourseRatingController extends Controller {

	public static void addRatingQuestion(String questionText, long courseId, RatingAnswerType answerType) {
		
		CourseRatingQuestion ratingQuestion = null;
		try {
			
			ratingQuestion = new CourseRatingQuestion(questionText, courseId, answerType);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Unable to insert rating question\"}");
		}
		renderJSON(ratingQuestion);
	}
	
	
	public static void submitCouseRating(long courseId, List<CourseQuestionnaire>questionnaires) {
		
		List<CourseRating> courseRatings =  new ArrayList<CourseRating>();
		try {
			long userId = Long.parseLong(session.get("login"));
			for (CourseQuestionnaire questionnaire : questionnaires) {
				CourseRating courseRating = new CourseRating(courseId, userId, questionnaire.getQuestionId(), questionnaire.getAnswer(), questionnaire.getAnswerType());
			 	courseRatings.add(courseRating);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Invalide answer id\"}");
		}
		renderJSON(courseRatings);
	}
	
	public static void followCourse(long courseId, Boolean follow) {
		
		long userId = Long.parseLong(session.get("login"));
		UserCourse userCourse = null;
		try {
			
			
			if(follow) {
				
			}
			
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Unable to process follow course request\"}");
		}
	
		
	}
	
}
