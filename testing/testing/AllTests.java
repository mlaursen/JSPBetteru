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
@SuiteClasses({UtilsTest.class, DatabaseChoicesTest.class, AccountObjectsTest.class, IngredientObjectsTest.class, MealObjectsTest.class})
public class AllTests {

}
