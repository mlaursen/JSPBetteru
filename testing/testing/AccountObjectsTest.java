/**
 * 
 */
package testing;

import static com.github.mlaursen.database.utils.DateUtil.sameDate;
import static com.github.mlaursen.database.utils.DateUtil.stringToDate;
import static org.junit.Assert.*;

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
import com.betteru.utils.SecurityUtil;
import com.github.mlaursen.database.managers.TestingObjectManager;

/**
 * @author mikkel.laursen
 *
 */
public class AccountObjectsTest {
	protected static TestingObjectManager tom = new TestingObjectManager();
	static {
		//tom.setDebug(true);
		tom.setDelete(false);
	}
	
	@ClassRule
	public static ExternalResource resource = new ExternalResource() {
		@Override
		protected void before() {
			tom = new TestingObjectManager(AccountSetting.class, TempAccount.class, Account.class);//, AccountView.class);
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
	
	public Account createTestAccountFull(String username, String password) {
		Account a = createTestAccount(username, password);
		a.setBirthday(stringToDate("01-JAN-91", "dd-MMM-yy"));
		a.setGender(new Gender("MALE"));
		a.setUnitSystem(new UnitSystem("IMPERIAL"));
		assertTrue(tom.update(a));
		return a;
	}
	
	public AccountSetting createFullTestAccountSetting(Account a) {
		AccountSetting as = tom.get(a.getPrimaryKey(), AccountSetting.class);
		as.setHeight(71);
		as.setMultiplier(new Multiplier("SEDENTARY"));
		as.setWeekday(new Weekday("TUESDAY"));
		assertTrue(tom.create(as));
		return as;
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
		Account a = createTestAccountFull("archer", "boop");
		AccountSetting as = tom.get(a.getPrimaryKey(), AccountSetting.class);
		assertNotNull(as);
		assertEquals(a.getPrimaryKey(), as.getAccountId());
		as.setWeekday(new Weekday("TUESDAY"));
		as.setMultiplier(new Multiplier("SEDENTARY"));
		as.setHeight(71);
		assertTrue(tom.create(as));
		
		AccountSetting as2 = tom.get(a.getPrimaryKey(), AccountSetting.class);
		assertEquals(as, as2);
		assertFalse(tom.delete(as));
		assertTrue(tom.delete(a));
	}
	
	@Test
	public void testAccountView() {
		Account a = createTestAccountFull("archer2", "boop");
		AccountSetting as = createFullTestAccountSetting(a);
		System.out.println(a);
		System.out.println(as);
		AccountView av = tom.get(a.getPrimaryKey(), AccountView.class);
		System.out.println(av);
		//assertTrue(sameDate(a.getBirthday(), av.getBirthday()));
		//assertEquals(a.getGender(), av.getGender());
		//assertEquals(a.getUnitSystem(), av.getUnitSystem());
		//assertEquals(as.getHeight(), av.getHeight(), 0);
		/*
		assertTrue(sameDate(av.getBirthday(), a.getBirthday()));
		assertEquals(av.getGender(), a.getGender());
		assertEquals(av.getUnitSystem(), a.getUnitSystem());
		assertEquals(av.getHeight(), as.getHeight(), 1);
		assertEquals(av.getMultiplier(), as.getMultiplier());
		assertEquals(av.getWeekday(), as.getWeekday());
		assertEquals(av.getAge(), 23);
		
		av.setGender("FEMALE");
		//assertTrue(av.update());
		
		av.setGender("MALE");
		//assertTrue(av.update());
		 */
		assertTrue(tom.delete(a));
	}
	
}
