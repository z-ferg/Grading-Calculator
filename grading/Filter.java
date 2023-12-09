package grading;

import java.util.List;

/**
 * A Filter can be used to "remove or include" specific grades from a collection of grades.
 *
 * Note: A Filter does not change the underlying collection, it creates a new collection that
 * satisfies the conditions of the Filter.
 *
 * @author Prof. David Bernstein, James Madison University
 */
public interface Filter
{
  /**
   * Apply this Filter.
   *
   * @param componenets
   *          The grades to filter
   * @return A collection containing the resulting grades
   * @throws SizeException
   *           if the List is not of appropriate size
   */
  public abstract List<Grade> apply(final List<Grade> componenets) throws SizeException;
}
