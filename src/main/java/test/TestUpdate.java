package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Teacher;

public class TestUpdate {
	public static void main(String[] args) {
		EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("JpaKosukha");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("Teacher.findAll");
		List<Teacher> list = q.getResultList();
		for (Teacher tch : list) System.out.println(tch);
		em.getTransaction().begin();
		Teacher tch = em.find(Teacher.class, 1);
		tch.setName("NewName");
		em.getTransaction().commit();
		//Output graduates list after update 
		list = q.getResultList();
		for (Teacher tchr : list) System.out.println("\t"+ tchr);		
	}
}
