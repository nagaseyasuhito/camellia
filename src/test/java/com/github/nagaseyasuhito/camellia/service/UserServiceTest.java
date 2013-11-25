package com.github.nagaseyasuhito.camellia.service;

import javax.validation.ConstraintViolationException;

import mockit.Deencapsulation;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;

import com.github.nagaseyasuhito.camellia.MethodValidationHelper;
import com.github.nagaseyasuhito.camellia.dao.UserDao;

public class UserServiceTest {
	private MethodValidationHelper methodValidationHelper = new MethodValidationHelper();

	private UserService userService = this.methodValidationHelper.create(UserService.class);

	@Mocked
	private UserDao userDao;

	@Before
	public void before() {
		Deencapsulation.setField(this.userService, this.userDao);
	}

	@Test
	public void createSuccess() {
		this.userService.create("user", "password");
	}

	@Test(expected = ConstraintViolationException.class)
	public void createFailureByNullName() {
		this.userService.create(null, "password");
	}

	@Test(expected = ConstraintViolationException.class)
	public void createFailureByNullPassword() {
		this.userService.create("user", null);
	}
}
