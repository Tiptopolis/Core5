package com.uchump.prime._MISC._WS.datasources;
import java.io.Serializable;

public interface DataSourceDescriptor extends Serializable {
	DataSource createDataSource();

	String getDisplayName();
}