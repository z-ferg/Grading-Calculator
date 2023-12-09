package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import grading.CompositeGrade;
import grading.DropFilter;
import grading.Filter;
import grading.Grade;
import grading.GradingStrategy;
import grading.SizeException;
import grading.WeightedTotalStrategy;
import grading.io.Category;
import grading.io.Course;
import grading.io.CourseReader;

/**
 * This class will test the static methods of the CourseReader class.
 * 
 * @author Zach Ferguson
 * @version Sep 14, 2023
 */
public class CourseReaderTest
{
  private BufferedReader in;
  private int size;
  private CourseReader cr;

  /**
   * The entry point for the application.
   * 
   * @param args
   *          args[0] contains the path (relative or absolute) to the .grd file
   * @throws IOException
   */
  @Before
  public void setup() throws IOException
  {
    in = new BufferedReader(new FileReader("complete_01.grd"));
    in.readLine();
    cr = new CourseReader();
  }

  @Test
  public void testReadCourse() throws NumberFormatException, IOException
  {
    BufferedReader in = new BufferedReader(new FileReader("missing_one-in-each.grd"));
    int size = Integer.parseInt(in.readLine());
    CompositeGrade course = CourseReader.readCourse(in, size);
    assertEquals(course.getComponents().get(0).getKey(), "Programming Assignments");
    assertEquals(course.getComponents().get(1).getKey(), "Homework Assignments");
    assertEquals(course.getComponents().get(2).getKey(), "Midterm Exam");
    assertEquals(course.getComponents().get(3).getKey(), "Final Exam");
  }

  @Test
  public void testReadCategory() throws NumberFormatException, IOException
  {
    assertThrows(IllegalArgumentException.class, () -> {
      CourseReader.readCategory(in, size, null, new DropFilter());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      CourseReader.readCategory(in, size, "", new DropFilter());
    });
  }
}
