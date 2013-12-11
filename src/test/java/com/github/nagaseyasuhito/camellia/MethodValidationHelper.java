package com.github.nagaseyasuhito.camellia;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.executable.ExecutableValidator;

import lombok.SneakyThrows;

public class MethodValidationHelper {
	private static ExecutableValidator VALIDATOR = Validation.byDefaultProvider().configure().buildValidatorFactory().getValidator().forExecutables();

	@SneakyThrows
	public static <T> T create(Class<T> clazz) {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setSuperclass(clazz);

		@SuppressWarnings("unchecked")
		T object = (T) proxyFactory.createClass().newInstance();
		((ProxyObject) object).setHandler(new MethodHandler() {
			@Override
			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				Set<ConstraintViolation<Object>> parameterViolations = VALIDATOR.validateParameters(self, thisMethod, args);
				if (!parameterViolations.isEmpty()) {
					throw new ConstraintViolationException(parameterViolations);
				}

				Object result;
				try {
					result = proceed.invoke(self, args);
				} catch (InvocationTargetException e) {
					throw e.getCause();
				}

				Set<ConstraintViolation<Object>> returnViolations = VALIDATOR.validateReturnValue(self, thisMethod, result);
				if (!returnViolations.isEmpty()) {
					throw new ConstraintViolationException(returnViolations);
				}

				return result;
			}
		});

		return object;
	}
}
