package org.dean.duck.ut;

import mockit.Tested;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * MyBusinessService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11月 11, 2019</pre>
 */
public class MyBusinessServiceTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Tested
	final EntityX entityX = new EntityX(1, "abc", "a.com");
	@Tested(fullyInitialized = true)
	MyBusinessService myBusinessService;

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: doBusinessOperationXyz()
	 */
	@Test
	public void testDoBusinessOperationXyz() throws Exception {
//TODO: Test goes here...
		EntityX existedItem = new EntityX(1, "AX5", "abc@com");
		myBusinessService.doBusinessOperationXyz();
	}


} 
