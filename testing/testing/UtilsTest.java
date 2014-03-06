/**
 * 
 */
package testing;

import static com.betteru.utils.SecurityUtil.createCode;
import static com.betteru.utils.SecurityUtil.createHash;
import static com.betteru.utils.SecurityUtil.repeatedHashing;
import static com.betteru.utils.StringNumberUtil.attemptParseInteger;
import static com.betteru.utils.StringNumberUtil.calculateStringFraction;
import static com.betteru.utils.StringNumberUtil.formatDecimalString;
import static com.betteru.utils.StringNumberUtil.formatFractionString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Test;

/**
 * @author mikkel.laursen
 *
 */
public class UtilsTest {
	

	@Test
	public void testRepeatedHashing() {
		assertNotNull(repeatedHashing("test", "test"));
	}
	@Test
	public void testCreateHash() {
		assertNotNull(createHash("test", "teset"));
	}
	
	@Test
	public void testCreateCode() {
		assertEquals(createCode(32).length(), 32);
		assertEquals(createCode().length(), 32);
		assertEquals(createCode(1).length(), 1);
	}
	
	@Test
	public void testFormatFractionString() {
		assertEquals(formatFractionString("0.25"), "1/4");
		assertEquals(formatFractionString(.25), "1/4");
		assertEquals(formatFractionString(0.33), "1/3");
		assertEquals(formatFractionString(0.333333), "1/3");
		assertEquals(formatFractionString(0.25000), "1/4");
		assertEquals(formatFractionString(.5), "1/2");
		assertEquals(formatFractionString("000000.5000000"), "1/2");
		assertEquals(formatFractionString(.125), "1/8");
		assertEquals(formatFractionString("000000.12500000000"), "1/8");
		assertEquals(formatFractionString("            .125"), "1/8");
		assertEquals(formatFractionString(".125      "), "1/8");
	}
	
	@Test
	public void testFormatDecmalString() {
		assertEquals(formatDecimalString(1800.43), "1800.43");
		assertEquals(formatDecimalString("1800.4334003"), "1800.43");
		assertEquals(formatDecimalString(1800.43003), "1800.43");
	}
	
	@Test
	public void testCalculateStringFraction() {
		assertEquals(calculateStringFraction("1/8"), 0.125, 1);
		assertEquals(calculateStringFraction("1"), 1, 1);
		assertEquals(calculateStringFraction("1/2"), .5, 1);
		assertEquals(calculateStringFraction("1/32"), 1/32, 1);
		assertEquals(calculateStringFraction("        1/8"), 1/8, 1);
	}
	
	@Test
	public void testAttemptParseInteger() {
		assertEquals(attemptParseInteger("21"), new Integer(21));
		assertEquals(attemptParseInteger("0"), new Integer(0));
		assertEquals(attemptParseInteger("-21"), new Integer(-21));
		assertEquals(attemptParseInteger("hello world!"), new Integer(0));
		assertEquals(attemptParseInteger("hello world!", 22), new Integer(22));
		assertEquals(attemptParseInteger(""), new Integer(0));
		assertEquals(attemptParseInteger(null), new Integer(0));
	}
}
