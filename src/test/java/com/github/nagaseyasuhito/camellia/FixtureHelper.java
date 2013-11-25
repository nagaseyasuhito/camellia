package com.github.nagaseyasuhito.camellia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.github.nagaseyasuhito.camellia.entity.User;

@Transactional
public class FixtureHelper {

	@PersistenceContext
	private EntityManager entityManager;

	public User createUser(String name, String password) {
		User user = new User(name, password);
		this.entityManager.persist(user);

		return user;
	}
}
