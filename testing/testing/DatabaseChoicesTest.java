/**
 * 
 */
package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.Multiplier;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.databasechoices.accounts.Weekday;
import com.betteru.databasechoices.ingredients.Brand;
import com.betteru.databasechoices.ingredients.Category;
import com.betteru.databasechoices.ingredients.FoodUnit;

/**
 * @author mikkel.laursen
 *
 */
public class DatabaseChoicesTest {
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
