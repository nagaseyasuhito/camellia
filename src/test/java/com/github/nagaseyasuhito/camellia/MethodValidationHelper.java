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

import org.hibernate.validator.HibernateValidator;

public class MethodValidationHelper {
	private ExecutableValidator executableValidator = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory().getValidator().forExecutables();

	@SneakyThrows
	public <T> T create(Class<T> clazz) {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setSuperclass(clazz);

		@SuppressWarnings("unchecked")
		T object = (T) proxyFactory.createClass().newInstance();
		((ProxyObject) object).setHandler(new MethodHandler() {
			@Override
			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				Set<ConstraintViolation<Object>> parameterViolations = MethodValidationHelper.this.executableValidator.validateParameters(self, thisMethod, args);
				if (!parameterViolations.isEmpty()) {
					throw new ConstraintViolationException(parameterViolations);
				}

				Object result;
				try {
					result = proceed.invoke(self, args);
				} catch (InvocationTargetException e) {
					throw e.getCause();
				}

				Set<ConstraintViolation<Object>> returnViolations = MethodValidationHelper.this.executableValidator.validateReturnValue(self, thisMethod, result);
				if (!returnViolations.isEmpty()) {
					throw new ConstraintViolationException(returnViolations);
				}

				return result;
			}
		});

		return object;
	}
}
