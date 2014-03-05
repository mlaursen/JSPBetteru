/**
 * 
 */
package testing;

import static com.betteru.utils.DateUtil.sameDate;
import static com.betteru.utils.DateUtil.stringToDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import com.betteru.accounts.objects.Account;
import com.betteru.accounts.objects.AccountSetting;
import com.betteru.accounts.objects.AccountView;
import com.betteru.accounts.objects.TempAccount;
import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.Multiplier;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.databasechoices.accounts.Weekday;
import com.github.mlaursen.database.managers.TestingObjectManager;

/**
 * @author mikkel.laursen
 *
 */
public class AccountObjectsTest {
	protected static TestingObjectManager tom = new TestingObjectManager();
	static {
		tom.setDebug(true);
		tom.setDelete(false);
	}
	
	@ClassRule
	public static ExternalResource resource = new ExternalResource() {
		@Override
		protected void after() {
			tom.cleanUp();
		}
	};
	
	@Test
	public void testCreateDeleteUpdateAccount() {
		tom.addPackage(Account.class);
		//System.out.println(tom);
		//tom.addPackage(TempAccount.class);
		//TempAccount ta = new TempAccount("testing", "testing");
		//ta.hashPassword();
		//assertTrue(tom.create(ta));
		//assertTrue(tom.executeCustomProcedure("newaccount", TempAccount.class, ta.getUsername(), ta.getPassword()));
		//assertTrue(ta.create());
		//assertTrue(ta.createAccount());
		//ta = tom.get(ta.getPrimaryKey(), TempAccount.class);
		//ta = new TempAccount(ta.getPrimaryKey());
		//assertTrue(ta.getPrimaryKey() == null);
	}
		/*
		Account a = new Account("testing", "testing");
		assertTrue(a.isValidUser());
		assertNotNull(a.getPrimaryKey());
		assertNotNull(a.getUsername());
		assertNotNull(a.getPassword());
		assertNull(a.getBirthday());
		assertNull(a.getGender());
		assertNull(a.getUnitSystem());
		a.setBirthday(stringToDate("01-JAN-91", "dd-MMM-yy"));
		a.setGender(new Gender("MALE"));
		a.setUnitSystem(new UnitSystem("IMPERIAL"));
		assertTrue(a.update());
		a = new Account(a.getPrimaryKey());
		assertNotNull(a.getBirthday());
		assertTrue(sameDate(a.getBirthday(), stringToDate("01-JAN-91", "dd-MMM-yy")));
		assertEquals(a.getGender(), new Gender("MALE"));
		assertEquals(a.getUnitSystem(), new UnitSystem("IMPERIAL"));
		assertTrue(a.delete());
	}
	@Test
	public void testGetAccount() {
		Account a = new Account(0);
		assertEquals(a.getPrimaryKey(),"0");
		assertEquals(a.getUsername(), "test");
		assertTrue(sameDate(a.getBirthday(), stringToDate("21-JAN-91", "dd-MMM-yy")));
		assertEquals(a.getUnitSystem(), new UnitSystem("IMPERIAL"));
		assertEquals(a.getGender(), new Gender("MALE"));
		assertTrue(sameDate(a.getActiveSince(), stringToDate("14-FEB-14", "dd-MMM-yy")));
	}
	
	@Test
	public void testUpdateLastLogin() {
		Account a = new Account(0);
		assertTrue(a.updateLastLogin());
		a = new Account(0);
		assertTrue(sameDate(a.getLastLogin(), new java.sql.Date(Calendar.getInstance().getTimeInMillis())));
	}
	
	@Test
	public void testAccountSetting() {
		AccountSetting as = new AccountSetting(0);
		assertNotNull(as.getHeight());
		assertNotNull(as.getMultiplier());
		assertNotNull(as.getPrimaryKey());
		assertEquals(as.getAccountId(), "0");
		assertEquals(as.getWeekday(), new Weekday("TUESDAY"));
		assertEquals(as.getMultiplier(), new Multiplier("SEDENTARY"));
		assertEquals(as.getHeight(), 71, 1);
		assertNotNull(as.getDateChanged());
		
		as.setWeekday(new Weekday("MONDAY"));
		as.setHeight(70);
		assertTrue(as.update());
		as = new AccountSetting(0);
		assertEquals(as.getWeekday(), new Weekday("MONDAY"));
		assertEquals(as.getHeight(), 70, 1);
		
		as.setWeekday(new Weekday("TUESDAY"));
		assertTrue(as.update());
	}
	
	
	@Test
	public void testAccountView() {
		Account a = new Account(0);
		AccountSetting as = new AccountSetting(0);
		AccountView av = new AccountView(0);
		assertTrue(sameDate(av.getBirthday(), a.getBirthday()));
		assertEquals(av.getGender(), a.getGender());
		assertEquals(av.getUnitSystem(), a.getUnitSystem());
		assertEquals(av.getHeight(), as.getHeight(), 1);
		assertEquals(av.getMultiplier(), as.getMultiplier());
		assertEquals(av.getWeekday(), as.getWeekday());
		assertEquals(av.getAge(), 23);
		
		av.setGender("FEMALE");
		assertTrue(av.update());
		
		av.setGender("MALE");
		assertTrue(av.update());
	}
	*/
}
