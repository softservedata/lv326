package com.softserve.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	private static final String PERSISTENT_UNIT_NAME = "JavaLv326";
	private static final EntityManagerFactory emf;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEm() {
		return emf.createEntityManager();
	}

}
