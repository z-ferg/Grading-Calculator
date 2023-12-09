package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;

import grading.LeafGrade;
import grading.LeafGrade;

/**
 * This will be the test class for the LeafGrade class.
 * 
 * @author Zach Ferguson
 * @version Aug 28, 2023
 */
public class LeafGradeTest
{
  private LeafGrade g, g1, g2, error;

  @Before
  public void setUp()
  {
    g = new LeafGrade("PA1");
    g1 = new LeafGrade("PA2", 1.1);
    g2 = new LeafGrade("PA3", 2.2);
  }

  @Test
  public void testOneArgGrade()
  {
    assertEquals("PA1", g.getKey());
    assertEquals(0.0, g.getValue(), 0.001);
    assertThrows(IllegalArgumentException.class, () -> {
      error = new LeafGrade("");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      error = new LeafGrade("", 1.1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      error = new LeafGrade("", 2.2);
    });
  }

  @Test
  public void testTwoArgGrade()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      LeafGrade error1 = new LeafGrade("", 25.0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      LeafGrade error2 = new LeafGrade(null, 25.0);
    });
  }

  @Test
  public void testToString()
  {
    String actual = g.toString();
    String expected = "PA1:   0.0";
    assertEquals(expected, actual);

    LeafGrade test = new LeafGrade("HW1", null);
    actual = test.toString();
    expected = "HW1:    NA";
  }

  @Test
  public void testCompareTo()
  {
    LeafGrade nV1 = new LeafGrade("HW1", null);
    LeafGrade nV2 = new LeafGrade("HW2", null);
    LeafGrade v3 = new LeafGrade("HW3", 1.1);
    LeafGrade v4 = new LeafGrade("HW4", 1.2);

    assertEquals(0, nV1.compareTo(nV2));
    assertEquals(1, v3.compareTo(nV1));
    assertEquals(-1, nV1.compareTo(v3));
    assertEquals(-1, v3.compareTo(v4));
  }

  @Test
  public void testGetKey()
  {
    assertEquals("PA1", g.getKey());
    assertEquals("PA2", g1.getKey());
    assertEquals("PA3", g2.getKey());
  }

  @Test
  public void testGetValue()
  {
    assertEquals(0.0, g.getValue(), 0);
    assertEquals(1.1, g1.getValue(), 0);
    assertEquals(2.2, g2.getValue(), 0);
  }

  @Test
  public void testParseLeafInvalidKeys()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      LeafGrade.parseLeafGrade(null, "50.0");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      LeafGrade.parseLeafGrade("", "50.0");
    });
  }

  @Test
  public void testParseLeafNullValue()
  {
    LeafGrade lg = LeafGrade.parseLeafGrade("HW5", null);
    assertNull(lg.getValue());
  }

  @Test
  public void testParseLeafImproperValue()
  {
    LeafGrade lg = LeafGrade.parseLeafGrade("HW5", "abc");
    assertNull(lg.getValue());
  }

  @Test
  public void testParseLeafCorrectlyWorking()
  {
    LeafGrade lg = LeafGrade.parseLeafGrade("HW5", "50.0");
    assertEquals(50.0, lg.getValue(), 0);
  }
}
