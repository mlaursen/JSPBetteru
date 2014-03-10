/**
 * 
 */
package testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import com.betteru.accounts.objects.Account;
import com.betteru.accounts.objects.AccountSetting;
import com.betteru.accounts.objects.AccountView;
import com.betteru.accounts.objects.TempAccount;
import com.betteru.intake.objects.DailyIntake;
import com.betteru.intake.objects.DailyMealIntake;
import com.betteru.intake.objects.Weight;
import com.github.mlaursen.database.managers.TestingObjectManager;
import com.github.mlaursen.database.utils.DateUtil;


/**
 * @author mikkel.laursen
 *
 */
public class IntakeObjectsTest {
protected static TestingObjectManager tom;
	
	@ClassRule
	public static ExternalResource resource = new ExternalResource() {
		
		@Override
		protected void before() {
			boolean debug = false;
			boolean delete = true;
			boolean copyData = false;
			tom = new TestingObjectManager(delete, debug, copyData, "daily_intake","daily_meal_intake","account","temp_account","weight","account_setting", "account_view");
			tom.addPackage(TempAccount.class);
			tom.addPackage(AccountSetting.class);
			tom.addPackageWithView(Account.class, AccountView.class);
			tom.addPackage(Weight.class);
			tom.addPackage(DailyIntake.class);
			tom.addPackage(DailyMealIntake.class);
			tom.recompile();
		}
		
		@Override
		protected void after() {
			tom.cleanUp();
		}
	};
	
	@Test
	public void test() {
		Account a = AccountObjectsTest.createTestingAccount("test", "test", tom);
		String id = a.getPrimaryKey();
		assertNotNull(id);
		Weight w = new Weight(id, DateUtil.stringToDate("10-FEB-14", "dd-MMM-yy"), 180);
		assertTrue(tom.create(w));
	}
	
}
