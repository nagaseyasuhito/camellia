package com.github.nagaseyasuhito.camellia.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

import com.google.common.collect.Maps;

public class BaseDaoTest {
	private EntityManagerFactory entityManagerFactory;

	private EntityManager entityManager;

	@Before
	public void baseBefore() {
		// overriding original persistence.xml
		Map<String, String> properties = Maps.newHashMap();
		properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
		properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
		properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:;DB_CLOSE_DELAY=-1");
		properties.put("javax.persistence.jdbc.user", "");
		properties.put("javax.persistence.jdbc.password", "");
		properties.put("javax.persistence.jtaDataSource", "");

		properties.put("eclipselink.logging.level", "WARNING");
		properties.put("eclipselink.logging.level" + ".sql", "FINE");
		properties.put("eclipselink.ddl-generation", "create-tables");
		// properties.put("eclipselink.logging.parameters", "true");
		// properties.put("eclipselink.ddl-generation.output-mode", "both");

		this.entityManagerFactory = Persistence.createEntityManagerFactory("camellia", properties);
		this.entityManager = this.entityManagerFactory.createEntityManager();

		this.entityManager.getTransaction().begin();
	}

	@After
	public void baseAfter() {
		this.entityManager.getTransaction().commit();

		if (this.entityManager != null && this.entityManager.isOpen()) {
			this.entityManager.close();
		}
		if (this.entityManagerFactory != null && this.entityManagerFactory.isOpen()) {
			this.entityManagerFactory.close();
		}
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}
}
