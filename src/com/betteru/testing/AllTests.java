/**
 * 
 */
package com.betteru.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.betteru.accounts.testing.AccountObjectsTest;
import com.betteru.utils.UtilsTest;

/**
 * @author mikkel.laursen
 *
 */
@RunWith(Suite.class)
@SuiteClasses({AccountObjectsTest.class, UtilsTest.class})
public class AllTests {

}
