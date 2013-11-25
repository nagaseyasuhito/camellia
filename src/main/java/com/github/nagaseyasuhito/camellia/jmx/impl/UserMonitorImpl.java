package com.github.nagaseyasuhito.camellia.jmx.impl;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.github.nagaseyasuhito.camellia.dao.UserDao;
import com.github.nagaseyasuhito.camellia.entity.User;
import com.github.nagaseyasuhito.camellia.jmx.UserMonitor;

@Startup
@Singleton
public class UserMonitorImpl extends BaseMonitorImpl implements UserMonitor {

	@Inject
	private UserDao userDao;

	@Override
	public long getNumberOfUsers() {
		return this.userDao.count();
	}

	@Override
	protected String getType() {
		return User.class.getSimpleName();
	}
}
