package com.supermarket.main;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class Database {
	//private final Session session;
	
	public Database() {
		//this.session = new Configuration().configure().buildSessionFactory().getCurrentSession();
	}

	public void startCommit() {
		//this.session.beginTransaction();
	}
	
	public void saveObject(Object o) {
		//this.session.save(o);
	}
	
	public void doCommit() {
		//this.session.getTransaction().commit();
	}

	public void destroy() {
		//this.session.close();
	}
}
