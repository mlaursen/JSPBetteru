package com.betteru.database;

import java.util.List;

public interface DatabaseObjectListable {

	<T extends DatabaseObject> List<T> lookupAll(Class<T> type);
	
}
