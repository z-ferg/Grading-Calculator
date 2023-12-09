package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import grading.CompositeGrade;
import grading.DropFilter;
import grading.LeafGrade;
import grading.SizeException;
import grading.TotalStrategy;

public class CompositeGradeTest
{
  @Test
  public void testConstructor()
  {
    assertNotNull(new CompositeGrade("test"));

    assertThrows(IllegalArgumentException.class, () -> {
      new CompositeGrade(null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new CompositeGrade("");
    });
  }

  @Test
  public void testAddWithGetComponents()
  {
    CompositeGrade cg = new CompositeGrade("CS 345");
    assertEquals(0, cg.getComponents().size());
    cg.add(new LeafGrade("PA 1", 50.0));
    assertEquals(1, cg.getComponents().size());
  }
  
  @Test
  public void testSetFilter() {
    CompositeGrade cg = new CompositeGrade("CS 345");
    assertNull(cg.getFilter());
    cg.setFilter(new DropFilter(true, false));
    assertNotNull(cg.getFilter());
    assertEquals(new DropFilter().getClass(), cg.getFilter().getClass());
  }
  
  @Test
  public void testSetStrategy() {
    CompositeGrade cg = new CompositeGrade("CS 345");
    assertNull(cg.getStrategy());
    cg.setStrategy(new TotalStrategy());
    assertNotNull(cg.getStrategy());
    assertEquals(new TotalStrategy().getClass(), cg.getStrategy().getClass());
  }
  
  @Test
  public void testGetValueNullStrategy() {
    CompositeGrade cg = new CompositeGrade("CS 345");
    assertNull(cg.getValue());
    
  }
  
  @Test
  public void testGetValueNullFilter() {
    CompositeGrade cg = new CompositeGrade("CS 345");
    assertNull(cg.getValue());
  }
  
  @Test
  public void testGetValueNormalFlow() {
    CompositeGrade cg = new CompositeGrade("CS 345");
    cg.setFilter(new DropFilter(true, true));
    cg.setStrategy(new TotalStrategy());
    
    cg.add(new LeafGrade("PA1", 20));
    cg.add(new LeafGrade("PA2", 20));
    cg.add(new LeafGrade("PA3", 20));
    
    assertEquals(20, cg.getValue(), 0);
  }
}
