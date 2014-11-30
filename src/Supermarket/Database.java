package Supermarket;
/*
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
*/

public class Database {
	//private SessionFactory sessionFactory;
	//private Session session;

	public Database() {
		//this.sessionFactory = new Configuration().configure().buildSessionFactory();
		//this.session = sessionFactory.openSession();
	}
	
	public void initCommit() {
		//session.beginTransaction();
	}
	
	public void saveObject(Object o) {
		//session.save(o);
	}
	
	public void doCommit() {
		//session.getTransaction().commit();
	}
	
	public void close() {
		//session.close();
	}
}
