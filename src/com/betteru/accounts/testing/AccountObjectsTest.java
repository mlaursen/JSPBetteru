/**
 * 
 */
package com.betteru.accounts.testing;

import static com.betteru.utils.DateUtil.sameDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import com.betteru.accounts.objects.Account;
import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.UnitSystem;
import static com.betteru.utils.DateUtil.*;

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
		Account a = new Account(0);
		assertTrue(a.update());
		a.setBirthday(stringToDate("01-21-1991"));
		assertTrue(a.update());
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
		Account a = new Account();
		a.setUsername("unittest");
		a.setPassword("unittest");
		a.
	}
}
