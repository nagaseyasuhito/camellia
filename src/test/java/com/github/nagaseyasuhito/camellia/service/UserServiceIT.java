package com.github.nagaseyasuhito.camellia.service;

import javax.inject.Inject;

import org.junit.Test;

import com.github.nagaseyasuhito.camellia.FixtureHelper;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

public class UserServiceIT extends BaseServiceIT {
	@Inject
	private FixtureHelper fixtureHelper;

	@Inject
	private UserService userService;

	@Test
	public void createSuccess() {
		assertThat(this.userService.create("create", "password"), is(notNullValue()));
	}

	@Test
	public void showSuccess() {
		this.fixtureHelper.createUser("show", "password");
		assertThat(this.userService.show("show"), is(notNullValue()));
	}

	@Test
	public void deleteSuccess() {
		this.fixtureHelper.createUser("delete", "password");
		assertThat(this.userService.delete("delete"), is(notNullValue()));
	}
}
