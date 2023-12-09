package testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import grading.DropFilter;
import grading.Filter;
import grading.Grade;
import grading.LeafGrade;
import grading.GradingStrategy;
import grading.WeightedTotalStrategy;
import grading.io.*;

public class CourseTest{
	private List<Grade> grades;
	private Filter filt;
	private GradingStrategy gs;
	
	@Test
	public void testConstructor() {
		List<Category> c = new ArrayList<Category>();
		filt = new DropFilter(false, false);
		gs = new WeightedTotalStrategy();
		grades = new ArrayList<Grade>();
		grades.add(new LeafGrade("Test 1", 90));
		grades.add(new LeafGrade("Test 2", 80));
		Category cat1 = new Category("Tests", grades, filt, gs);
		
		grades = new ArrayList<Grade>();
		grades.add(new LeafGrade("HW 1", 34));
		grades.add(new LeafGrade("HW 2", 76));
		Category cat2 = new Category("Homework", grades, filt, gs);
		
		grades = new ArrayList<Grade>();
		grades.add(new LeafGrade("Lab 1", 83));
		grades.add(new LeafGrade("Lab 2", 62));
		Category cat3 = new Category("Labs", grades, filt, gs);
		
		Course cs = new Course(c, gs);
		assertEquals(c, cs.getCategories());
		assertEquals(gs, cs.getStrategy());
	}
}