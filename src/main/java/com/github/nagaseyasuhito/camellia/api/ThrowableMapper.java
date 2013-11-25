package com.github.nagaseyasuhito.camellia.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.github.nagaseyasuhito.camellia.api.response.ErrorResponse;

public class ThrowableMapper implements ExceptionMapper<Throwable> {
	@Override
	public Response toResponse(Throwable exception) {
		return Response.status(Status.SERVICE_UNAVAILABLE).entity(new ErrorResponse(exception)).build();
	}
}