package com.github.nagaseyasuhito.camellia.service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.github.nagaseyasuhito.camellia.dao.UserDao;
import com.github.nagaseyasuhito.camellia.entity.User;

@Transactional
public class UserService {
	@Inject
	private UserDao userDao;

	public User fetch(@NotNull String apikey) {
		return this.userDao.findByApikey(apikey);
	}

	public User create(@NotNull String name, @NotNull String password) {
		User user = new User(name, password);
		this.userDao.persist(user);

		return user;
	}

	public User show(@NotNull String name) {
		return this.userDao.findByName(name);
	}

	public User delete(@NotNull String name) {
		User user = this.userDao.findByName(name);
		this.userDao.remove(user);
		return user;
	}
}
