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

/**
 * @author mikkel.laursen
 *
 */
public class DatabaseChoicesTest {

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
	}

}
