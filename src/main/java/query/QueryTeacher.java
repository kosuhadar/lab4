	package query;

import java.util.Map;

import controller.Controller;
import model.Lesson;
import model.Teacher;

public class QueryTeacher {
	
	public static String queryCreate() {
		return null;
	}
	
	public static String queryGetAll() {
		return "select tch.id, tch.name, tch.degree, tch.rating,"
		+ " tch.lesson.name AS lsnname, tch.lesson.id AS idlsn"
		+ " FROM model.Teacher tch"
		+ " ORDER BY idlsn, tch.name"; 
	}
	
	public static String queryGetForLesson(int idLesson){
		return String.format("SELECT tch.id, tch.name, tch.degree, tch.rating"
				+ " FROM model.Teacher tch"
				+ " WHERE tch.lesson.id = %d"
				+ " ORDER BY tch.name", idLesson);
	}
	
	public static Teacher createObject(Map<String,Object>map) {
		Teacher tch = new Teacher();
		tch.setName((String)map.get("name"));
		tch.setDegree((String)map.get("degree"));
		tch.setRating((int)map.get("rating"));
		int idLesson = (int) map.get("idLesson");
		Lesson lesson = (Lesson) Controller.getObjectById("Lesson", idLesson);
		tch.setLesson(lesson);
		return tch;
	}
	
	public static Object editObject(Object obj, Map<String, Object>map) {
		Teacher tch = (Teacher)obj;
		tch.setName((String)map.get("name"));
		tch.setDegree((String)map.get("degree"));
		tch.setRating((int)map.get("rating"));
		int idLesson = (int) map.get("idLesson");
		Lesson lesson = (Lesson) Controller.getObjectById("Lesson", idLesson);
		tch.setLesson(lesson);
		return tch;
	}
	
}
	