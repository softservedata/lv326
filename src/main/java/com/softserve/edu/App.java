package com.softserve.edu;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App client = new App();

		try {
			Honey honey = new Honey();
			honey.setName("favourite white");
			honey.setTaste("very sweet");
			honey = client.createHoney(honey);
			//
			System.out.println("primary key is " + honey.getId());
			client.listHoney();
			//
			honey.setName("greece forest++2");
			honey.setTaste("very good++2");
			client.updateHoney(honey);
			client.listHoney();
			//
		} catch (HibernateException e) {
			// problem with Hibernate happened
			e.printStackTrace();
		}
    }

	private Honey createHoney(Honey honey) {

		// [laliluna] get the session from the factory
		Session session = HibernateSessionFactory.currentSession();

		// [laliluna] always start a transaction before doing something
		// (even reading) from the database
		Transaction tx = session.beginTransaction();

		// [laliluna] save it to the database, Hibernate returns your object
		// with the primary key field updated!
		Long id = (Long) session.save(honey);
		honey.setId(id);
		

		// [laliluna] commit your transaction or nothing is wrote to the db
		tx.commit();

		// [laliluna] clean up (close the session)
		HibernateSessionFactory.closeSession();

		return honey;
	}

	private void updateHoney(Honey honey) {

		// [laliluna] get the session from the factory
		Session session = HibernateSessionFactory.currentSession();

		// [laliluna] always start a transaction before doing something
		// (even reading) from the database
		Transaction tx = session.beginTransaction();

		// [laliluna] load object from the database
		Honey dbHoney = (Honey) session.get(Honey.class, honey.getId());

		// [laliluna] honey is null when no object was found
		if (honey != null) {
			dbHoney.setName(honey.getName());
			dbHoney.setTaste(honey.getTaste());
		}
		session.flush();
		// [laliluna] commit your transaction or nothing is wrote to the db
		tx.commit();

		// [laliluna] clean up (close the session)
		HibernateSessionFactory.closeSession();
	}

	private void listHoney() {

		// [laliluna] get the session from the factory
		Session session = HibernateSessionFactory.currentSession();

		// [laliluna] always start a transaction before doing something
		// (even reading) from the database
		Transaction tx = session.beginTransaction();

		List<?> honeys =  session.createQuery("from Honey").list();

		for (Iterator<?> iter = honeys.iterator(); iter.hasNext();) {
			Honey honey = (Honey) iter.next();
			System.out.println("Id " + honey.getId() + " Name " + honey.getName());
		}
		// [laliluna] commit your transaction or nothing is wrote to the db
		tx.commit();
		// [laliluna] clean up (close the session)
		HibernateSessionFactory.closeSession();
	}

}
