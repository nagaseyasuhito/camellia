package com.github.nagaseyasuhito.camellia.api.resource;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.github.nagaseyasuhito.camellia.entity.User;
import com.github.nagaseyasuhito.camellia.service.UserService;

@Path("user")
public class UserResource extends BaseResource {

	@Inject
	private UserService userService;

	@Path("{name}")
	@PUT
	public User create(@PathParam("name") String name, @QueryParam("password") String password) {
		return this.userService.create(name, password);
	}

	@Path("{name}")
	@GET
	public User show(@PathParam("name") String name) {
		return this.userService.show(name);
	}

	@Path("{name}")
	@DELETE
	public User delete(@PathParam("name") String name) {
		return this.userService.delete(name);
	}
}
