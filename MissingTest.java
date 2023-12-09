package testing;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import grading.Missing;

public class MissingTest{
	
	@Test
	public void testOneArgDouble() 
	{
		Missing m = new Missing();
		assertEquals(0.0, m.doubleValue(null), 0);
		assertEquals(5.0, m.doubleValue(5.0), 0);
	}
	
	@Test
	public void testTwoArgDouble() 
	{
		Missing m = new Missing();
		assertEquals(1.0, m.doubleValue(null, 1.0), 0);
		assertEquals(5.0, m.doubleValue(5.0, 1.0), 0);
	}
}