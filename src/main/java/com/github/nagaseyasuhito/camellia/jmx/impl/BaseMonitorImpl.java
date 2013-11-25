package com.github.nagaseyasuhito.camellia.jmx.impl;

import java.lang.management.ManagementFactory;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import lombok.SneakyThrows;

public abstract class BaseMonitorImpl {

	protected abstract String getType();

	@SneakyThrows
	@PostConstruct
	public void initialize() {
		ObjectName objectName = new ObjectName("Camellia:type=" + this.getType());
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		if (server.isRegistered(objectName)) {
			server.unregisterMBean(objectName);
		}
		server.registerMBean(this, objectName);
	}
}
