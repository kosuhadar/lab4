package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Lesson;
import model.Teacher;

public class TestNamedQuery {
	public static void main(String[] args) {
		EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("JpaKosukha");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("Lesson.findAll");
		List<Lesson> list = q.getResultList();
		for (Lesson lsn : list) {
			System.out.println(lsn);
			for(Teacher tch : lsn.getTeachers()) {
				System.out.println("\t"+tch);
			}
		}
	}
}
