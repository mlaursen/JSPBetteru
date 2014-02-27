/**
 * 
 */
package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author mikkel.laursen
 *
 */
@RunWith(Suite.class)
@SuiteClasses({AccountObjectsTest.class, UtilsTest.class, DatabaseChoicesTest.class})
public class AllTests {

}
