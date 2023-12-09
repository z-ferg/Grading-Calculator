package grading;

/**
 * Abstract class representing a generic Grade.
 * 
 * This work complies with the JMU honor code
 * 
 * @author Zach Ferguson
 * @version Sep 21, 2023
 *
 */
public abstract class AbstractGrade implements Grade
{
  private String key;

  /**
   * Constructor for the AbstractGrade to be used in Composite and Leaf.
   * 
   * @param key
   * @throws IllegalArgumentException
   */
  public AbstractGrade(final String key) throws IllegalArgumentException
  {
    if (key == null || key.equals(""))
      throw new IllegalArgumentException();
    this.key = key;
  }

  @Override
  public int compareTo(final Object o)
  {
    if (this.getValue() == null)
    {
      if (((AbstractGrade) o).getValue() == null)
      { // Both null
        return 0;
      }
      else
      { // This null, other non-null
        return -1;
      }
    }
    else
    {
      if (((AbstractGrade) o).getValue() == null)
      { // This non-null, other null
        return 1;
      }
      else
      { // Both non-null
        return this.getValue().compareTo(((AbstractGrade) o).getValue());
      }
    }
  }

  @Override
  public String getKey()
  {
    return this.key;
  }

  @Override
  public String toString()
  {
    if (this.getValue() == null)
      return String.format("%s: %5s", key, "NA");
    else
      return String.format("%s: %5.1f", key, this.getValue());
  }

  @Override
  public abstract Double getValue();
}
