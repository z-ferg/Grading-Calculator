package grading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A DropFilter can be used to drop the lowest and/or highest Grade from a List.
 *
 * @author Prof. David Bernstein, James Madison University
 */
public class DropFilter implements Filter
{
  private boolean shouldDropHighest, shouldDropLowest;

  /**
   * Default Constructor (i.e., construct a DropRule that drops the highest and lowest Grade
   * objects).
   */
  public DropFilter()
  {
    this(true, true);
  }

  /**
   * Explicit Value Constructor.
   *
   * @param shouldDropLowest
   *          true if this Rule should drop the lowest Grade
   * @param shouldDropHighest
   *          true if this Rule should drop the highest Grade
   */
  public DropFilter(final boolean shouldDropLowest, final boolean shouldDropHighest)
  {
    this.shouldDropLowest = shouldDropLowest;
    this.shouldDropHighest = shouldDropHighest;
  }

  /**
   * Apply this DropFilter (required by Filter).
   * 
   * This method throws a SizeException if the given List is null or the number of elements to be
   * dropped is greater than or equal to the number of elements in the List.
   *
   * @param grades
   *          The original List of Grade objects
   * @return The List of resulting Grade objects (not necessarily in the same order)
   * @throws SizeException
   *           if the List is null or too small
   */
  public List<Grade> apply(final List<Grade> grades) throws SizeException
  {
    ArrayList<Grade> result;

    if ((grades == null) || (numberToDrop() >= grades.size()))
      throw new SizeException();

    result = new ArrayList<Grade>();

    // Make a copy of the List
    for (int i = 0; i < grades.size(); i++)
      result.add(grades.get(i));

    // Sort the copy using the natural order (i.e., the Grade object's compareTo() method)
    Collections.sort(result);

    // Drop elements as appropriate
    if (shouldDropLowest)
      result.remove(0);
    if (shouldDropHighest)
      result.remove(result.size() - 1);

    return result;
  }

  /**
   * Get the number of Grade objects that should be dropped.
   * 
   * @return The number (i.e., either 0, 1, or 2)
   */
  private int numberToDrop()
  {
    int result = 0;

    if (shouldDropLowest)
      ++result;
    if (shouldDropHighest)
      ++result;

    return result;
  }

}
