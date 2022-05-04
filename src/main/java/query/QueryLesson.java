package query;

import java.util.Map;

import model.Lesson;

public class QueryLesson {
	
	public static String queryCreate() {
		return null;
	}
	
	public static String queryGetAll() {
		return "select lsn.id, lsn.name, lsn.credits, lsn.haslabs "
		+"FROM model.Lesson lsn "
		+ "ORDER BY lsn.name"; 
	}
	
	public static Lesson createObject(Map<String,Object>map) {
		Lesson lsn = new Lesson();
		lsn.setName((String)map.get("name"));
		lsn.setCredits((int)map.get("credits"));
		lsn.setHaslabs((int)map.get("hasLabs"));
		return lsn;
	}
	
	public static Object editObject(Object obj, Map<String, Object>map) {
		Lesson lsn = (Lesson)obj;
		lsn.setName((String)map.get("name"));
		lsn.setCredits((int)map.get("credits"));
		lsn.setHaslabs((int)map.get("hasLabs"));
		return lsn;
	}
}
