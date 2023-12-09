package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import grading.*;

public class WeightedAverageStrategyTest
{
  private WeightedAverageStrategy was;
  private List<Grade> grades;
  private Map<String, Double> weights;

  @Before
  public void setup()
  {
    weights = new HashMap<String, Double>();
    weights.put("Test 1", 25.0);
    weights.put("Test 2", 25.0);
    weights.put("Final", 25.0);

    grades = new ArrayList<Grade>();

    was = new WeightedAverageStrategy(weights);
  }

  @Test
  public void testDefaultConstructor()
  {
    was = new WeightedAverageStrategy();

    assertNotNull(was);

    grades.add(new LeafGrade("Test 1", 30));
    grades.add(new LeafGrade("Test 2", 40));

    assertEquals(35, was.calculate("Zach", grades).getValue(), 0); // Check null weights map

    grades.add(new LeafGrade("Test 3", null));

    assertEquals(35, was.calculate("Zach", grades).getValue(), 0); // Check ignores missing
  }

  @Test
  public void testOneParamConstructor()
  {
    assertNotNull(was);

    grades.add(new LeafGrade("Test 1", 30));
    grades.add(new LeafGrade("Test 2", 40));
    grades.add(new LeafGrade("Final", 50));

    assertEquals(40, was.calculate("Zach", grades).getValue(), 0);

    grades.add(new LeafGrade("Test 1", null));

    assertEquals(40, was.calculate("Zach", grades).getValue(), 0);
  }

  @Test
  public void testTwoParamConstructor()
  {
    assertNotNull(was);

    grades.add(new LeafGrade("Test 1", 30));
    grades.add(new LeafGrade("Test 2", 40));
    grades.add(new LeafGrade("Final", 50));

    assertEquals(40, was.calculate("Zach", grades).getValue(), 0);

    grades.add(new LeafGrade("Test 1", null));

    assertEquals(40, was.calculate("Zach", grades).getValue(), 0);
  }

  @Test
  public void testImmutableListPassed()
  {
    grades.add(new LeafGrade("Test 1", 30));
    grades.add(new LeafGrade("Test 2", 40));
    grades.add(new LeafGrade("Final", 50));
    List<Grade> expected = new ArrayList<Grade>();

    expected.addAll(grades);

    was.calculate("Zach", grades);

    assertEquals(expected, grades);
  }

  @Test
  public void testNullGradesCalculate()
  {
    assertEquals(0.0, was.calculate("Zach", null).getValue(), 0);

    weights.put("Homework 1", null);
    grades.add(new LeafGrade("Test 1", 30));
    grades.add(new LeafGrade("Homework 1", 90));

    assertEquals(30, was.calculate("Zach", grades).getValue(), 0);
  }

  @Test
  public void testEmptyList()
  {
    assertEquals(0.0, was.calculate("Zach", grades).getValue(), 0);
  }

  @Test
  public void testNullWeightsMap()
  {
    was = new WeightedAverageStrategy(null);
    grades.add(new LeafGrade("Test 1", 50));
    grades.add(new LeafGrade("Test 2", 50));

    assertEquals(50, was.calculate("Zach", grades).getValue(), 0);

    grades.add(new LeafGrade("Test 3", 50));

    assertEquals(50, was.calculate("Zach", grades).getValue(), 0);
  }

  @Test
  public void testAllZeroWeights()
  {
    weights.put("HW 1", 0.0);
    weights.put("HW 2", 0.0);
    weights.put("HW 3", 0.0);

    grades.add(new LeafGrade("HW 1", 25));
    grades.add(new LeafGrade("HW 2", 25));
    grades.add(new LeafGrade("HW 3", 25));

    assertEquals(0.0, was.calculate("Zach", grades).getValue(), 0);
  }

  @Test
  public void testCorrectCalculateIgnoreMissing()
  {
    was = new WeightedAverageStrategy(weights, true);
    weights.put("HW", 25.0);
    weights.put("Labs", 30.0);
    weights.put("Exams", 40.0);
    weights.put("Participation", 5.0);

    grades.add(new LeafGrade("HW", 70));
    grades.add(new LeafGrade("HW", null));

    grades.add(new LeafGrade("Labs", 90));
    grades.add(new LeafGrade("Labs", null));

    grades.add(new LeafGrade("Exams", 100)); // Optimistic
    grades.add(new LeafGrade("Participation", 100));

    grades.add(new LeafGrade("PA", 50)); // Should not be calculated

    assertEquals(89.5, was.calculate("Zach", grades).getValue(), 0);
  }

  @Test
  public void testCorrectCalculateDontIgnoreMissing()
  {
    was = new WeightedAverageStrategy(weights, false);
    weights.put("HW", 25.0);
    weights.put("Labs", 30.0);
    weights.put("Exams", 40.0);
    weights.put("Participation", 5.0);

    grades.add(new LeafGrade("HW", 70));
    grades.add(new LeafGrade("HW", null));

    grades.add(new LeafGrade("Labs", 90));
    grades.add(new LeafGrade("Labs", null));

    grades.add(new LeafGrade("Exams", 100));
    grades.add(new LeafGrade("Participation", 100));

    assertEquals(57.74, was.calculate("Zach", grades).getValue(), 0);
  }
}
