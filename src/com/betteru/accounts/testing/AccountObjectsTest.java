/**
 * 
 */
package com.betteru.accounts.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.betteru.accounts.objects.Account;
import com.betteru.utils.DateUtil;

import static com.betteru.utils.DateUtil.sameDate;

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
		assertTrue(sameDate(a.getBirthday(), DateUtil.stringToDate("21-JAN-91", "dd-MMM-yy")));
	}

}
