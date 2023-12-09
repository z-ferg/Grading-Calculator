package testing;

import grading.DropFilter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import grading.DropFilter;
import grading.Grade;
import grading.LeafGrade;
import grading.SizeException;

/**
 * This class will be the test for the DropFilter class.
 * 
 * This work complies with the JMU honor code
 * 
 * @author Zach Ferguson
 * @version Aug 29, 2023
 */

public class DropFilterTest
{
	private DropFilter df;
	private List<Grade> grades;
	
	@Before
	public void setup() {
		df = new DropFilter();
		grades = new ArrayList<Grade>();
		grades.add(new LeafGrade("HW", 100));
	}
	
	@Test
	public void testDefaultConstructor() 
	{
		assertNotNull(df);
	}
	
	@Test
	public void test2ArgConstructor() 
	{
		df = new DropFilter(false, false);
		assertNotNull(df);
	}
	
	@Test
	public void testApplyNullGrades() 
	{
		df = new DropFilter();
		assertThrows(SizeException.class, () -> 
		{
	        df.apply(null);
	    });
	}
	
	@Test
	public void testApplyTooSmall() {
		df = new DropFilter();
		assertThrows(SizeException.class, () -> 
		{
	        df.apply(grades);
	    });
	}
	
	@Test
	public void testApplyDropHighest() {
		df = new DropFilter(false, true);
		grades.add(new LeafGrade("HW", 80));
		try {
			grades = df.apply(grades);
		} catch (SizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, grades.size());
		assertEquals(80.0, grades.get(0).getValue(), 0);
	}
	
	@Test
	public void testApplyDropLowest() {
		df = new DropFilter(true, false);
		grades.add(new LeafGrade("HW", 80));
		try {
			grades = df.apply(grades);
		} catch (SizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, grades.size());
		assertEquals(100.0, grades.get(0).getValue(), 0);
	}
}
