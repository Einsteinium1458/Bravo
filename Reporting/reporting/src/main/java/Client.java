import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ejb.*;
import entity.Lifecyclestate;
import entity.Publication;

public class Client {
	
	public void doStuff() throws NamingException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("reporting");
		EntityManager em = emf.createEntityManager();
		
		Lifecyclestate life = em.find(Lifecyclestate.class, 3);
		System.out.println("Accepted: " + life.getAccepted() + " Submitted: " + life.getSubmitted() + " In Rivision: " + life.getInRevision());
		
		entity.Person p = em.find(entity.Person.class, 2);
		System.out.println(p.getFirstNames());
		Query query = em.createQuery("SELECT p.publication FROM Publicationauthor AS p WHERE p.personID = :personID");
		query.setParameter("personID", p.getPersonID());
		List<Publication> list = (List<Publication>) query.getResultList();
		for(Publication pub: list) 
			System.out.println(pub.getPublicationTypeID());
	}
}
