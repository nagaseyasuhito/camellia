package com.github.nagaseyasuhito.camellia.dao;

import javax.persistence.NoResultException;

import mockit.Deencapsulation;

import org.junit.Before;
import org.junit.Test;

import com.github.nagaseyasuhito.camellia.FixtureHelper;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

public class UserDaoTest extends BaseDaoTest {
	private FixtureHelper fixtureHelper = new FixtureHelper();

	private UserDao userDao = new UserDao();

	@Before
	public void before() throws Throwable {
		Deencapsulation.setField(this.fixtureHelper, this.getEntityManager());
		Deencapsulation.setField(this.userDao, this.getEntityManager());
	}

	@Test
	public void findByNameSuccess() {
		this.fixtureHelper.createUser("user", "password");

		assertThat(this.userDao.findByName("user").getName(), is("user"));
	}

	@Test(expected = NoResultException.class)
	public void findByNameFailureByUnknownName() {
		this.userDao.findByName("user");
	}
}
