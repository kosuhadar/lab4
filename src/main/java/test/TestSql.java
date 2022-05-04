package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Teacher;

public class TestSql {
	public static void main(String[] args) {
		EntityManagerFactory emf =
		Persistence.createEntityManagerFactory("JpaKosukha");
		EntityManager em = emf.createEntityManager();
		String sql = "SELECT * FROM TEACHER  WHERE TEACHER.RATING >= 0";
		Query qSql = em.createNativeQuery(sql);
		em.getTransaction().begin();
		List<Object[]> list = qSql.getResultList();
		em.getTransaction().commit();
		//Output list 
		for (Object[] arr : list) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + "\t");
			}
			System.out.println();
		}

	}
}
