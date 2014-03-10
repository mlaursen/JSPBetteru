/**
 * 
 */
package testing;

import static org.junit.Assert.fail;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import com.betteru.accounts.objects.Account;
import com.betteru.intake.objects.DailyIntake;
import com.betteru.intake.objects.DailyMealIntake;
import com.github.mlaursen.database.managers.TestingObjectManager;


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
			tom = new TestingObjectManager(delete, debug, copyData, "daily_intake","daily_meal_intake","account","weight");
			tom.addPackage(Account.class);
			tom.addPackage(Weight.class);
			tom.addPackage(DailyIntake.class);
			tom.addPackage(DailyMealIntake.class);
		}
		
		@Override
		protected void after() {
			tom.cleanUp();
		}
	};
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
}
