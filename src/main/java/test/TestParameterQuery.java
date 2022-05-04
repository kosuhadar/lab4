package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Teacher;

public class TestParameterQuery {
	public static void main(String[] args) {
		EntityManagerFactory emf =
		Persistence.createEntityManagerFactory("JpaKosukha");
		EntityManager em = emf.createEntityManager();
		String sq = "select tch from Teacher tch where tch.rating =?1";
		Query q = em.createQuery(sq);
		q.setParameter(1, 5);
		List<Teacher> list = q.getResultList();
		for (Teacher tch : list) {
			System.out.println(tch);
		}
	}

}
