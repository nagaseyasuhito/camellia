package com.github.nagaseyasuhito.camellia.api.response;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = -1825876513027266759L;

	private String message;

	private String stackTrace;

	public ErrorResponse(Throwable e) {
		this.message = e.getMessage();
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		this.stackTrace = writer.toString();
	}
}
