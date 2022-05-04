package query;

public class Query {
	public static String query1(int avgrade, int year) {
		return String.format("SELECT std.name, std.avgrade FROM model.Student std"
				+ " WHERE std.yearuni = %d AND std.avgrade > %d ORDER BY std.avgrade DESC", year, avgrade);
	}
	public static String query2(String degree, int lessonId) {
		return String.format("SELECT tch.name, tch.degree, tch.rating FROM model.Teacher tch"
				+ " WHERE tch.degree='%s' AND tch.lesson.id=%d", degree, lessonId);
	}
}
