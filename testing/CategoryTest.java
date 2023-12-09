package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import grading.*;
import grading.io.Category;

public class CategoryTest{
	private Category c;
	private List<Grade> grades;
	private Filter f;
	private GradingStrategy gs;
	
	@Before
	public void setup() {
		grades = new ArrayList<Grade>();
		grades.add(new LeafGrade("HW 1", 25));
		grades.add(new LeafGrade("HW 2", 25));
		grades.add(new LeafGrade("HW 3", 50));
		
		f = new DropFilter(true, true);
		
		gs = new WeightedTotalStrategy();
	}
	
	@Test
	public void testConstructor() {
		c = new Category("Homework", grades, f, gs);
		assertEquals("Homework", c.getKey());
		assertEquals(grades, c.getGrades());
		assertEquals(f, c.getFilter());
		assertEquals(gs, c.getStrategy());
	}
	
	@Test
	public void testConstructorException() {
		assertThrows(IllegalArgumentException.class, () -> 
		{
			c = new Category("", grades, f, gs);
	    });
		assertThrows(IllegalArgumentException.class, () -> 
		{
			c = new Category(null, grades, f, gs);
	    });
	}
}