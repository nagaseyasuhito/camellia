package com.github.nagaseyasuhito.camellia.api;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.github.nagaseyasuhito.camellia.api.resource.UserResource;
import com.google.common.collect.Sets;

@ApplicationPath("api")
public class CamelliaApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = Sets.newHashSet();
		classes.add(UserResource.class);

		classes.add(ThrowableMapper.class);

		return classes;
	}
}
