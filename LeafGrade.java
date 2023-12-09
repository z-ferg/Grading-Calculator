package grading;

/**
 * An immutable individual Grade object.
 * 
 * This work complies with the JMU honor code
 *
 * @author Prof. David Bernstein, James Madison University
 */
public class LeafGrade extends AbstractGrade
{
  private Double value;

  /**
   * Construct a Grade with the given key and a value of 0.0.
   *
   * @param key
   *          The key used to identify this Grade object
   * @throws IllegalArgumentException
   *           if key is null or the empty string
   */
  public LeafGrade(final String key) throws IllegalArgumentException
  {
    this(key, Double.valueOf(0.0));
  }

  /**
   * Construct a Grade with the given key and value.
   *
   * @param key
   *          The key used to identify this Grade object
   * @param value
   *          The value of this Grade
   * @throws IllegalArgumentException
   *           if key is null or the empty string
   */
  public LeafGrade(final String key, final double value) throws IllegalArgumentException
  {
    this(key, Double.valueOf(value));
  }

  /**
   * Construct a Grade with the given key and value.
   *
   * @param key
   *          The key used to identify this Grade object
   * @param value
   *          The value of this Grade
   * @throws IllegalArgumentException
   *           if key is null or the empty string
   */
  public LeafGrade(final String key, final Double value) throws IllegalArgumentException
  {
    super(key);
    this.value = value;
  }

  /**
   * Get the numeric value associated with this Grade object.
   *
   * @return The numeric value (or null for missing)
   */
  public Double getValue()
  {
    return value;
  }

  /**
   * This will allow a LeafGrade object to be made with a string value and string key.
   * 
   * @param key the desired key of the new LeafGrade object
   * @param value the desired value of the new LeafGrade object
   * @return a new LeafGrade object with given key and value (if applicable)
   * @throws IllegalArgumentException if given key is empty or null
   */
  public static LeafGrade parseLeafGrade(final String key, final String value)
      throws IllegalArgumentException
  {
    if (key == null || key.equals(""))
    {
      throw new IllegalArgumentException();
    }

    try
    {
      return new LeafGrade(key, Double.parseDouble(value));
    }
    catch (NumberFormatException ex)
    {
      return new LeafGrade(key, null); // Value string is improper
    }
    catch (NullPointerException ex)
    {
      return new LeafGrade(key, null); // Value string is null
    }
  }
}
