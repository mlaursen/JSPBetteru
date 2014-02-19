package com.betteru.accounts.database;

import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.Deleteable;

public class TempAccount extends AccountTemplate implements Deleteable {

	public TempAccount() { }

	public TempAccount(String primaryKey) {
		super(primaryKey);
	}

	public TempAccount(MyResultRow r) {
		super(r);
	}

}
