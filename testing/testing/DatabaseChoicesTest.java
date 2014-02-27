/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.Multiplier;

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
		
		Multiplier m = new Multiplier("SEDENTARY");
		List<Multiplier> multipliers = m.getAll(Multiplier.class);
		assertEquals(5, multipliers.size());
		assertTrue(multipliers.contains(new Multiplier("SEDENTARY")));
		assertTrue(multipliers.contains(new Multiplier("LIGHTLY ACTIVE")));
		assertTrue(multipliers.contains(new Multiplier("VERY ACTIVE")));
		assertTrue(multipliers.contains(new Multiplier("EXTREMELY ACTIVE")));
		assertTrue(multipliers.contains(new Multiplier("MODERATELY ACTIVE")));
		assertEquals(1.2, multipliers.get(0).getAmount(), 1);
		
	}

}
