/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;

import static com.betteru.utils.DateUtil.*;
import static com.betteru.utils.SecurityUtil.*;
import static com.betteru.utils.StringNumberUtil.*;

/**
 * @author mikkel.laursen
 *
 */
public class UtilsTest {
	@Test
	public void testSameDay() {
		assertTrue(sameDate(Date.valueOf("1990-01-01"), Date.valueOf("1990-01-01")));
		assertFalse(sameDate(Date.valueOf("1991-01-01"), Date.valueOf("1990-01-01")));
	}
	
	
	@Test
	public void testStringToDate() {
		Date d = Date.valueOf("1990-02-21");
		assertTrue(sameDate(stringToDate("21-FEB-1990", "dd-MMM-yyyy"), d));
		assertTrue(sameDate(stringToDate("02-21-1990", "MM-dd-yyyy"), d));
		assertTrue(sameDate(stringToDate("21-02-1990", "dd-MM-yyyy"), d));
		assertTrue(sameDate(stringToDate("1990-02-21"), d));
		assertTrue(sameDate(stringToDate("02/21/1990"), d));
	}
	
	@Test
	public void testSysdateToString() {
		assertTrue(sameDate(sysdateToDate("1990-02-21 8:22:13"), sysdateToDate("1990-02-21 8:22:13")));
		assertTrue(sameDate(sysdateToDate("1990-02-21 8:22:13"), Date.valueOf("1990-02-21")));
	}

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
