package com.softserve.edu;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    //private static final Configuration configuration = new Configuration();

    static {
        try {
            // Creates the session factory from hibernate.cfg.xml
            //configuration.configure();
//            sessionFactory = configuration
//                    .buildSessionFactory(new ServiceRegistryBuilder()
//                            .applySettings(configuration.getProperties())
//                            .buildServiceRegistry());
//			sessionFactory = configuration
//					.buildSessionFactory(new StandardServiceRegistryBuilder()
//							.applySettings(configuration.getProperties())
//							.build());
            // Hibernate 5.
			sessionFactory = new MetadataSources(
					new StandardServiceRegistryBuilder()
						.configure().build())
					.getMetadataBuilder().build()
						.getSessionFactoryBuilder().build();
			// StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
//                  .configure()
//                  .build();
//          Metadata metadata = new MetadataSources( standardRegistry )
//                  .getMetadataBuilder()
//                  .build();
//          sessionFactory = metadata.getSessionFactoryBuilder().build();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
