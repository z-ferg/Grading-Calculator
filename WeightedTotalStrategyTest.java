package testing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import grading.Grade;
import grading.LeafGrade;
import grading.SizeException;
import grading.WeightedTotalStrategy;

public class WeightedTotalStrategyTest{
	private WeightedTotalStrategy w, w1;
	private Map<String, Double> weights;
	private List<Grade> grades;
	
	@Before
	public void setup() 
	{
		weights = new HashMap<String, Double>();
		weights.put("HW", 0.3);
		weights.put("PA1", 0.7);
		weights.put("Quiz", -0.1);
		
		grades = new ArrayList<Grade>();
		grades.add(new LeafGrade("HW", 50));
		grades.add(new LeafGrade("HW", 90));
		grades.add(new LeafGrade("PA1", 80));
		
		w = new WeightedTotalStrategy();
		w1 = new WeightedTotalStrategy(weights);
	}
	
	/**
	 * Test that the WeightedTotalStrategy default constructor works.
	 */
	@Test
	public void testDefaultConstructor() 
	{
		assertNotNull(w);
	}
	
	/**
	 * Test the WeightedTotalStrategy one argument constructor.
	 */
	@Test
	public void testOneArgConstructor() 
	{
		assertNotNull(w1);
	}
	
	/**
	 * Test the Calculate method when passed a null list.
	 */
	@Test
	public void testCalculateNullList() 
	{
		assertThrows(SizeException.class, () -> 
		{
      List<Grade> test = null;
      w.calculate("Zach", test);
	  });
	}
	
	/**
	 * Test the Calculate method when passed an empty list.
	 */
	@Test
	public void testCalculateEmptyList() 
	{
		assertThrows(SizeException.class, () -> 
		{
	    List<Grade> test = new ArrayList<Grade>();
	    w.calculate("Zach", test);
	  });
	}
	
	/**
	 * Test the Calculate method when weights instance variable is null.
	 * @throws SizeException 
	 */
	@Test
	public void testCalculateNullWeights() throws SizeException 
	{
		assertEquals(220.0, w.calculate("Zach", grades).getValue(), 0);
	}
	
	/**
	 * Test the Calculate method when weights does not have corresponding key.
	 * @throws SizeException 
	 */
	@Test
	public void testCalculateMissingKey() throws SizeException 
	{
		grades.add(new LeafGrade("Test", 80));
		assertEquals(178, w1.calculate("Zach", grades).getValue(), 0);
	}
	
	/**
	 * Test the Calculate method with a negative value for a LeafGrade object.
	 * @throws SizeException 
	 */
	@Test
	public void testCalculateNegativeValue() throws SizeException 
	{
		grades.add(new LeafGrade("Quiz", 50));
		assertEquals(98, w1.calculate("Zach", grades).getValue(), 0);
	}
}
