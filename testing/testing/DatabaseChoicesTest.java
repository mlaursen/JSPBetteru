/**
 * 
 */
package testing;

import java.util.Arrays;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.Multiplier;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.databasechoices.accounts.Weekday;
import com.github.mlaursen.database.managers.TestingObjectManager;

import static com.betteru.databasechoices.accounts.Gender.*;
import static org.junit.Assert.*;

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
			tom = new TestingObjectManager(delete, debug, copyData, "gender", "weekday", "unit_system", "multiplier");
			tom.addPackage(Gender.class);
			tom.addPackage(Weekday.class);
			tom.addPackage(Multiplier.class);
			tom.addPackage(UnitSystem.class);
		}
		@Override
		protected void after() {
			tom.cleanUp();
		}
	};
	@Test
	public void test() {
		
	}
	
	@Test
	public void testGender() {
		List<Gender> gendersDB = tom.getAll(Gender.class);
		List<Gender> genders = Arrays.asList(MALE, FEMALE);
		assertEquals(genders.size(), gendersDB.size());
	}
/*
	@Test
	public void testAccountChoices() {
		Gender g = new Gender("MALE");
		assertEquals(g, g);
		List<Gender> genders = g.getAll(Gender.class);
		assertTrue(genders.contains(new Gender("MALE")));
		assertTrue(genders.contains(new Gender("FEMALE")));
		assertEquals(2, genders.size());
		
		Multiplier m = new Multiplier();
		Multiplier sedentary = m.get("SEDENTARY", Multiplier.class);
		List<Multiplier> multipliers = m.getAll(Multiplier.class);
		assertEquals(5, multipliers.size());
		assertTrue(multipliers.contains(new Multiplier("SEDENTARY")));
		assertTrue(multipliers.contains(new Multiplier("LIGHTLY ACTIVE")));
		assertTrue(multipliers.contains(new Multiplier("VERY ACTIVE")));
		assertTrue(multipliers.contains(new Multiplier("EXTREMELY ACTIVE")));
		assertTrue(multipliers.contains(new Multiplier("MODERATELY ACTIVE")));
		assertEquals(1.2, sedentary.getAmount(), 1);
		
		UnitSystem us = new UnitSystem();
		List<UnitSystem> units = us.getAll(UnitSystem.class);
		assertEquals(2, units.size());
		assertTrue(units.contains(new UnitSystem("IMPERIAL")));
		assertTrue(units.contains(new UnitSystem("METRIC")));
		
		Weekday w = new Weekday();
		Weekday monday = w.get("MONDAY", Weekday.class);
		List<Weekday> ws = w.getAll(Weekday.class);
		assertEquals(7, ws.size());
		assertTrue(ws.contains(monday));
		assertTrue(ws.contains(new Weekday("TUESDAY")));
		assertTrue(ws.contains(new Weekday("WEDNESDAY")));
		assertTrue(ws.contains(new Weekday("THURSDAY")));
		assertTrue(ws.contains(new Weekday("FRIDAY")));
		assertTrue(ws.contains(new Weekday("SATURDAY")));
		assertTrue(ws.contains(new Weekday("SUNDAY")));
		assertEquals(1, monday.getDayOfWeek());
	}
	
	@Test
	public void testIngredientChoices() {
		Brand b = new Brand();
		Brand testBrand = new Brand("Test");
		//System.out.println(testBrand.create());
		assertTrue(testBrand.create());
		List<Brand> bs = b.getAll(Brand.class);
		assertTrue(bs.contains(testBrand));
		assertTrue(testBrand.delete());
		
		Category c = new Category();
		List<Category> cs = c.getAll(Category.class);
		assertEquals(4,  cs.size());
		assertTrue(cs.contains(new Category("Proteins")));
		assertTrue(cs.contains(new Category("Carbs")));
		assertTrue(cs.contains(new Category("Dairy")));
		assertTrue(cs.contains(new Category("Other")));
		
		FoodUnit fu = new FoodUnit();
		FoodUnit gram = fu.get("g", FoodUnit.class);
		assertEquals("g", gram.getPrimaryKey());
		assertEquals("gram", gram.getLongName());
	}
*/
}
