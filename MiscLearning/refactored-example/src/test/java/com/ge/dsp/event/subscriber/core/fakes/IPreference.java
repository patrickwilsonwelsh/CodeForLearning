package com.ge.dsp.event.subscriber.core.fakes;

public interface IPreference {

	void setDupsContext(String string);

	void setKey(String string);

	void setUuid(String string);

	void setValue(String string);

	String getUuid();

	String getValue();

	String getKey();

	String getDupsContext();

}
