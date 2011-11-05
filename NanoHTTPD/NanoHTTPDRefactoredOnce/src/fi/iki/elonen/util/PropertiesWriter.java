package fi.iki.elonen.util;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesWriter {

	@SuppressWarnings("unchecked")
	public void writeProperties(Properties properties, String propertyTypeName,
			PrintWriter logger) {
		Enumeration e = properties.propertyNames();
		while (e.hasMoreElements()) {
			String value = (String) e.nextElement();
			logger.println("  " + propertyTypeName + ": '" + value + "' = '"
					+ properties.getProperty(value) + "'");
		}
	}
}
