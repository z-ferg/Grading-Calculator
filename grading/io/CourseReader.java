package grading.io;

import grading.*;
import java.io.*;
import java.util.*;

/**
 * A utility class for reading Course objects and Category objects.
 * 
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class CourseReader
{
  private static final String TAB = "\t";

  /**
   * Read a Category (that has an associated TotalStrategy).
   * 
   * @param in
   *          The BufferedReader to read from
   * @param size
   *          The number of records
   * @param key
   *          The key for the Category
   * @param filter
   *          The Filter for the Category
   * @return The Category
   * @throws IllegalArgumentException
   *           If the key is invalid
   * @throws IOException
   *           If the Category can't be read
   */
  public static CompositeGrade readCategory(final BufferedReader in, final int size,
      final String key, final Filter filter) throws IllegalArgumentException, IOException
  {

    ArrayList<Grade> grades = new ArrayList<Grade>();
    Double value;
    String line;
    String[] fields;

    for (int i = 0; i < size; i++)
    {
      line = in.readLine();
      fields = line.split(TAB);
      if (fields.length >= 2)
        value = Double.valueOf(Double.parseDouble(fields[1]));
      else
        value = null;
      grades.add(new LeafGrade(fields[0], value));
    }

    CompositeGrade cg = new CompositeGrade(key);
    cg.setFilter(filter);
    cg.setStrategy(new TotalStrategy());

    for (Grade grade : grades)
    {
      cg.add(grade);
    }

    return cg;
  }

  /**
   * Read a Course (that has a WeightedTotalStrategy).
   * 
   * @param in
   *          The BufferedReader to read from
   * @param size
   *          The number of records
   * @return The Course
   * @throws IOException
   *           If the Course can't be read
   */
  public static CompositeGrade readCourse(final BufferedReader in, final int size)
      throws IOException
  {
    List<Grade> categories = new ArrayList<Grade>();
    Map<String, Double> weights = new HashMap<String, Double>();
    String line;
    String[] fields;

    CompositeGrade cg = new CompositeGrade("Course");

    for (int i = 0; i < size; i++)
    {
      line = in.readLine();
      fields = line.split(TAB);
      String category = fields[0];
      int n = Integer.parseInt(fields[1]);
      double weight = Double.parseDouble(fields[2]);
      boolean dropLow = Boolean.parseBoolean(fields[3]);
      boolean dropHigh = Boolean.parseBoolean(fields[4]);

      Filter filter = new DropFilter(dropLow, dropHigh);
      weights.put(category, weight);

      categories.add(readCategory(in, n, category, filter));
    }

    GradingStrategy strategy = new WeightedTotalStrategy(weights);
    cg.setStrategy(strategy);

    for (Grade grade : categories)
    {
      cg.add(grade);
    }

    return cg;
  }
}
