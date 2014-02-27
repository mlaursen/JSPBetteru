/**
 * 
 */
package com.betteru.accounts.testing;

import static com.betteru.utils.DateUtil.sameDate;
import static com.betteru.utils.DateUtil.stringToDate;
import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import com.betteru.accounts.objects.Account;
import com.betteru.accounts.objects.TempAccount;
import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.github.mlaursen.database.ObjectManager;

/**
 * @author mikkel.laursen
 *
 */
public class AccountObjectsTest {
	
	@Test
	public void testGetAccount() {
		Account a = new Account(0);
		assertEquals(a.getPrimaryKey(),"0");
		assertEquals(a.getUsername(), "test");
		assertTrue(sameDate(a.getBirthday(), stringToDate("21-JAN-91", "dd-MMM-yy")));
		assertEquals(a.getUnitSystem(), new UnitSystem("IMPERIAL"));
		assertEquals(a.getGender(), new Gender("FEMALE"));
		assertTrue(sameDate(a.getActiveSince(), stringToDate("14-FEB-14", "dd-MMM-yy")));
	}

	@Test
	public void testUpdateAccount() {
		/*
		Account a = new Account(0);
		assertTrue(a.update());
		a.setBirthday(stringToDate("01-21-1991"));
		assertTrue(a.update());
		*/
	}
	
	@Test
	public void testUpdateLastLogin() {
		Account a = new Account(0);
		assertTrue(a.updateLastLogin());
		a = new Account(0);
		assertTrue(sameDate(a.getLastLogin(), new java.sql.Date(Calendar.getInstance().getTimeInMillis())));
	}
	
	@Test
	public void testCreateDeleteAccount() {
		TempAccount ta = new TempAccount("testing", "testing");
		assertTrue(ta.create());
		assertTrue(ta.createAccount());
		ta = new TempAccount(ta.getPrimaryKey());
		assertTrue(ta.getPrimaryKey() == null);
		
		Account a = new Account("testing", "testing");
		assertTrue(a.isValidUser());
		assertNotNull(a.getPrimaryKey());
		assertNotNull(a.getUsername());
		assertNotNull(a.getPassword());
		assertNull(a.getBirthday());
		assertNull(a.getGender());
		assertNull(a.getUnitSystem());
		
		a.setBirthday(stringToDate("01-01-1991"));
		a.setGender(new Gender("MALE"));
		a.setUnitSystem(new UnitSystem("IMPERIAL"));
		assertTrue(a.update());
		a = new Account(a.getPrimaryKey());
		
		assertTrue(a.delete());
		
	}
}
