package com.github.nagaseyasuhito.camellia.api.resource;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.github.nagaseyasuhito.camellia.entity.User;
import com.github.nagaseyasuhito.camellia.service.UserService;

public abstract class BaseResource {

	@Context
	private SecurityContext securityContext;

	@Inject
	private UserService userService;

	protected User getPrincipal() {
		return this.userService.fetch(this.securityContext.getUserPrincipal().getName());
	}
}
