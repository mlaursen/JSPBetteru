/**
 * 
 */
package testing;

import static com.github.mlaursen.database.utils.DateUtil.sameDate;
import static com.github.mlaursen.database.utils.DateUtil.stringToDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import com.betteru.accounts.objects.Account;
import com.betteru.accounts.objects.AccountSetting;
import com.betteru.accounts.objects.AccountView;
import com.betteru.accounts.objects.TempAccount;
import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.utils.SecurityUtil;
import com.github.mlaursen.database.managers.ObjectManager;
import com.github.mlaursen.database.managers.TestingObjectManager;

/**
 * @author mikkel.laursen
 *
 */
public class AccountObjectsTest {
	protected static TestingObjectManager tom = new TestingObjectManager();
	static {
		//tom.setDebug(true);
		//tom.setDelete(false);
	}
	
	@ClassRule
	public static ExternalResource resource = new ExternalResource() {
		@Override
		protected void before() {
			tom.addPackage(AccountSetting.class);
			tom.addPackageWithView(Account.class, AccountView.class);
			tom.addPackage(TempAccount.class);
			tom.recompile();
		}
		@Override
		protected void after() {
			tom.cleanUp();
		}
	};
	
	public TempAccount createTestTempAccount(String username, String password) {
		TempAccount ta = new TempAccount(username, password);
		ta.hashPassword();
		assertTrue(tom.create(ta));
		return ta;
	}
	
	public Account createTestAccount(String username, String password) {
		TempAccount ta = createTestTempAccount(username, password);
		assertTrue(tom.executeCustomProcedure("newaccount", TempAccount.class, ta.getUsername()));
		return tom.get(username, Account.class);
	}
	
	public Account createFullTestAccount(String username, String password) {
		Account a = createTestAccount(username, password);
		a.setBirthday(stringToDate("01-JAN-91", "dd-MMM-yy"));
		a.setGender(new Gender("MALE"));
		a.setUnitSystem(new UnitSystem("IMPERIAL"));
		assertTrue(tom.update(a));
		return a;
	}
	
	/**
	 * Testing sucks. Order for all of those matter
	 */
	@Test
	public void testAccount1() {
		TempAccount ta = createTestTempAccount("testing", "testing");
		assertTrue(tom.executeCustomProcedure("newaccount", TempAccount.class, ta.getUsername()));
		Account a = tom.get(0, Account.class);
		assertNotNull(a);
		Account a2 = tom.get("testing", Account.class);
		assertEquals(a, a2);
		a.setBirthday(stringToDate("01-JAN-91", "dd-MMM-yy"));
		a.setGender(new Gender("MALE"));
		a.setUnitSystem(new UnitSystem("IMPERIAL"));
		assertTrue(tom.update(a));
		
		a2 = tom.get("testing", Account.class);
		assertNotNull(a2);
		assertNotNull(a2.getBirthday());
		assertNotNull(a2.getPrimaryKey());
		assertTrue(sameDate(a2.getBirthday(), stringToDate("01-JAN-91", "dd-MMM-yy")));
		assertEquals(a2.getGender(), new Gender("MALE"));
		assertEquals(a2.getUnitSystem(), new UnitSystem("IMPERIAL"));
		assertTrue(tom.delete(a2));
		assertTrue(tom.create(a2));
	}
	
	@Test
	public void testAccountLastLogin() {
		Account a = createTestAccount("archer", "boop");
		assertNotNull(a);
		boolean valid = false;
		if(a != null && a.getPassword() != null) {
			String pswd = a.getPassword();
			String salt = pswd.substring(0, 64);
			String hash = SecurityUtil.repeatedHashing(salt, "boop");
			valid = hash.equals(pswd);
		}
		assertTrue(valid);
		assertTrue(tom.delete(a));
	}
	
	
	@Test
	public void testUpdateLastLogin() {
		Account a = createTestAccount("archer", "boop");
		assertTrue(tom.executeCustomProcedure(Account.UPDATE_LAST_LOGIN, Account.class, a.getPrimaryKey()));
		assertTrue(tom.delete(a));
	}
	
	@Test
	public void testAccountSetting() {
		Account a = createFullTestAccount("archer", "boop");
		System.out.println(a);
		//AccountSetting as = tom.get(a.getPrimaryKey(), AccountSetting.class);
		
		/*
		assertNotNull(as.getHeight());
		assertNotNull(as.getMultiplier());
		assertNotNull(as.getPrimaryKey());
		assertEquals(as.getAccountId(), a.getPrimaryKey());
		assertEquals(as.getWeekday(), new Weekday("TUESDAY"));
		assertEquals(as.getMultiplier(), new Multiplier("SEDENTARY"));
		assertEquals(as.getHeight(), 71, 1);
		assertNotNull(as.getDateChanged());
		*/
		assertTrue(tom.delete(a));
	}
	/*
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
