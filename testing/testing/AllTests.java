/**
 * 
 */
package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import accounts.AccountObjectsTest;

import com.betteru.utils.UtilsTest;

/**
 * @author mikkel.laursen
 *
 */
@RunWith(Suite.class)
@SuiteClasses({AccountObjectsTest.class, UtilsTest.class})
public class AllTests {

}
