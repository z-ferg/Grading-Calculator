package grading;

/**
 * A utility class for working with Number objects that can be missing.
 * 
 * @author Prof. David Bernstein, James Madison University
 */
public class Missing
{
  public static final double DEFAULT_MISSING_VALUE = 0.0;

  /**
   * Return the global default value if the given Double is null. Otherwise return its double value.
   * 
   * @param number
   *          The number to check
   * @return The appropriate double value
   */
  public static double doubleValue(final Double number)
  {
    return doubleValue(number, DEFAULT_MISSING_VALUE);
  }

  /**
   * Return the given missing value if the given Double is null. Otherwise return its double value.
   * This method should only be used when the DEFAULT_MISSING_VALUE is inappropriate.
   * 
   * @param number
   *          The number to check
   * @param missingValue
   *          The value to use in place of missing
   * @return The appropriate double value
   */
  public static double doubleValue(final Double number, final double missingValue)
  {
    if (number == null)
      return missingValue;
    else
      return number.doubleValue();
  }
}
