package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Lesson;

public class TestDelByName {
	public static void main(String[] args) {
		EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("JpaKosukha");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("Lesson.findAll");
		List<Lesson> list = q.getResultList();
		for (Lesson ls : list) System.out.println(ls);
		String sq = "DELETE FROM Lesson lsn WHERE lsn.name = ?1";
		Query qDel = em.createQuery(sq);
		qDel.setParameter(1, "SomeLesson");
		em.getTransaction().begin();
			qDel.executeUpdate();
		em.getTransaction().commit();
		list = q.getResultList();
		for (Lesson lssn : list) System.out.println("\t"+ lssn);
	}
}
