package com.github.nagaseyasuhito.camellia.jaxb;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.format.ISODateTimeFormat;

public class ISO8601DateAdapter extends XmlAdapter<String, Date> {
	@Override
	public Date unmarshal(String v) throws Exception {
		return new Date(ISODateTimeFormat.dateTimeNoMillis().withZoneUTC().parseMillis(v));
	}

	@Override
	public String marshal(Date v) throws Exception {
		return ISODateTimeFormat.dateTimeNoMillis().withZoneUTC().print(v.getTime());
	}
}
