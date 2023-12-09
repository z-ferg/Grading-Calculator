package grading;

import java.util.List;

/**
 * The requirements of a strategy (in the sense of the Strategy Pattern) for calculating a numeric
 * grade from a collection of grades.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public interface GradingStrategy
{
  /**
   * Use this GradingStrategy to calculate a numeric grade.
   *
   * @param key
   *          The key to use for the resulting Grade
   * @param componenets
   *          The collections of grades
   * @throws SizeException
   *           if List has incorrect size
   * @return The numeric grade
   */
  public abstract LeafGrade calculate(final String key, final List<Grade> componenets)
      throws SizeException;

}
