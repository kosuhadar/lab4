package query;

import java.util.Map;

import controller.Controller;
import model.Lesson;
import model.Student;
import model.Teacher;

public class QueryStudent {
	
	public static String queryCreate() {
		return null;
	}
	
	public static String queryGetAll() {
		return "select std.id, std.name, std.yearuni, std.avgrade,"
				+ " std.teacher.name AS tchname, std.teacher.id AS idteacher"
				+ " FROM model.Student std"
				+ " ORDER BY tchname, std.name";  
	}
	
	public static String queryGetForTeacher(int idTeacher) {
		return String.format("SELECT std.id, std.name, std.avgrade, std.yearuni"
				+ " FROM model.Student std"
				+ " WHERE std.teacher.id = %d"
				+ " ORDER BY std.name", idTeacher);
	}
	
	public static Student createObject(Map<String,Object>map){
		Student std = new Student();
		std.setName((String)map.get("name"));
		std.setAvgrade((int)map.get("avgrade"));
		std.setYearuni((int)map.get("year"));
		int idTeacher = (int) map.get("idTeacher");
		Teacher teacher = (Teacher) Controller.getObjectById("Teacher", idTeacher);
		std.setTeacher(teacher);
		return std;
	}
	
	public static Object editObject(Object obj, Map<String, Object>map) {
		Student std = (Student)obj;
		std.setName((String)map.get("name"));
		std.setAvgrade((int)map.get("avgrade"));
		std.setYearuni((int)map.get("year"));
		int idTeacher = (int) map.get("idTeacher");
		Teacher teacher = (Teacher) Controller.getObjectById("teacher", idTeacher);
		std.setTeacher(teacher);
		return std;
	}
}
