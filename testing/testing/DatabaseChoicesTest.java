/**
 * 
 */
package testing;

import static com.betteru.databasechoices.accounts.Gender.FEMALE;
import static com.betteru.databasechoices.accounts.Gender.MALE;
import static com.betteru.databasechoices.accounts.Multiplier.EXTREMELY_ACTIVE;
import static com.betteru.databasechoices.accounts.Multiplier.LIGHTLY_ACTIVE;
import static com.betteru.databasechoices.accounts.Multiplier.MODERATELY_ACTIVE;
import static com.betteru.databasechoices.accounts.Multiplier.SEDENTARY;
import static com.betteru.databasechoices.accounts.Multiplier.VERY_ACTIVE;
import static com.betteru.databasechoices.accounts.UnitSystem.IMPERIAL;
import static com.betteru.databasechoices.accounts.UnitSystem.METRIC;
import static com.betteru.databasechoices.accounts.Weekday.FRIDAY;
import static com.betteru.databasechoices.accounts.Weekday.MONDAY;
import static com.betteru.databasechoices.accounts.Weekday.SATURDAY;
import static com.betteru.databasechoices.accounts.Weekday.SUNDAY;
import static com.betteru.databasechoices.accounts.Weekday.THURSDAY;
import static com.betteru.databasechoices.accounts.Weekday.TUESDAY;
import static com.betteru.databasechoices.accounts.Weekday.WEDNESDAY;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.Multiplier;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.databasechoices.accounts.Weekday;
import com.betteru.databasechoices.ingredients.Brand;
import com.betteru.databasechoices.ingredients.Category;
import com.betteru.databasechoices.ingredients.FoodUnit;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.database.managers.TestingObjectManager;

/**
 * @author mikkel.laursen
 * 
 */
public class DatabaseChoicesTest {
	
	protected static TestingObjectManager tom;
	
	@ClassRule
	public static ExternalResource resource = new ExternalResource() {
		
		@Override
		protected void before() {
			boolean debug = false;
			boolean delete = true;
			boolean copyData = true;
			tom = new TestingObjectManager(delete, debug, copyData, "gender", "weekday", "unit_system", "multiplier", "category", "brand",
					"ingredient", "food_unit");
			tom.addPackage(Gender.class);
			tom.addPackage(Weekday.class);
			tom.addPackage(Multiplier.class);
			tom.addPackage(UnitSystem.class);
			tom.addPackage(Category.class);
			tom.addPackage(Brand.class);
			tom.addPackage(FoodUnit.class);
		}
		
		@Override
		protected void after() {
			tom.cleanUp();
		}
	};
	
	@Test
	public void testGender() {
		List<Gender> gendersDB = tom.getAll(Gender.class);
		List<Gender> genders = Arrays.asList(MALE, FEMALE);
		assertEquals(genders.size(), gendersDB.size());
		List<DropdownChoice> choices = new Gender().getAllChoices();
		assertEquals(3, choices.size());
	}
	
	@Test
	public void testMultiplier() {
		List<Multiplier> multipliersDB = tom.getAll(Multiplier.class);
		List<Multiplier> multipliers = Arrays.asList(SEDENTARY, LIGHTLY_ACTIVE, MODERATELY_ACTIVE, VERY_ACTIVE, EXTREMELY_ACTIVE);
		assertEquals(multipliers.size(), multipliersDB.size());
		List<DropdownChoice> choices = new Multiplier().getAllChoices();
		assertEquals(multipliers.size() + 1, choices.size());
	}
	
	@Test
	public void testWeekday() {
		List<Weekday> weekdaysDB = tom.getAll(Weekday.class);
		List<Weekday> weekdays = Arrays.asList(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY);
		assertEquals(weekdays.size(), weekdaysDB.size());
		List<DropdownChoice> choices = new Weekday().getAllChoices();
		// no default choice
		assertEquals(weekdays.size(), choices.size());
	}
	
	@Test
	public void testUnitSystem() {
		List<UnitSystem> unitsDB = tom.getAll(UnitSystem.class);
		List<UnitSystem> units = Arrays.asList(IMPERIAL, METRIC);
		assertEquals(units.size(), unitsDB.size());
		List<DropdownChoice> choices = new UnitSystem().getAllChoices();
		assertEquals(units.size() + 1, choices.size());
	}
	
	@Test
	public void testBrand() {
		List<Brand> bsDB = tom.getAll(Brand.class);
		List<DropdownChoice> choices = new Brand().getAllChoices();
		assertEquals(bsDB.size() + 1, choices.size());
		assertTrue(choices.contains(new Brand("New Brand")));
	}
	
	@Test
	public void testCreateBrand() {
		Brand testing = new Brand("Testing");
		assertTrue(tom.create(testing));
		List<Brand> bs = tom.getAll(Brand.class);
		assertTrue(bs.contains(testing));
	}
	
	@Test
	public void testCategory() {
		List<Category> csDB = tom.getAll(Category.class);
		List<DropdownChoice> choices = new Category().getAllChoices();
		assertEquals(csDB.size(), choices.size());
		assertTrue(csDB.contains(new Category("Proteins")));
		assertTrue(csDB.contains(new Category("Carbs")));
		assertTrue(csDB.contains(new Category("Dairy")));
		assertTrue(csDB.contains(new Category("Other")));
	}
	
	@Test
	public void testCreateCategory() {
		Category c = new Category("CAN't");
		assertFalse(tom.create(c));
	}
	
	@Test
	public void testDeleteCategory() {
		assertFalse(tom.delete(new Category("Proteins")));
	}
	
	@Test
	public void testFoodUnit() {
		List<FoodUnit> unitsDB = tom.getAll(FoodUnit.class);
		List<DropdownChoice> choices = new FoodUnit().getAllChoices();
		assertEquals(unitsDB.size(), choices.size());
		FoodUnit g = tom.get("g", FoodUnit.class);
		assertNotNull(g);
		assertEquals("g", g.getPrimaryKey());
		assertEquals("gram", g.getLongName());
	}
	
	@Test
	public void testDeleteFoodUnit() {
		FoodUnit g = tom.get("g", FoodUnit.class);
		assertFalse(tom.delete(g));
	}
	
	@Test
	public void testCreateFoodUnit() {
		FoodUnit g = new FoodUnit("blob");
		assertFalse(tom.create(g));
	}
}
