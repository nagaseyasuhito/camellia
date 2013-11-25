package com.github.nagaseyasuhito.camellia.jmx;

import javax.management.MXBean;

@MXBean
public interface UserMonitor {
	long getNumberOfUsers();
}
