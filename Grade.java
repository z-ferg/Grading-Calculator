package grading;

/**
 * Interface for Grade objects, used for lists to generalize the items.
 * 
 * This work complies with the JMU honor code
 * 
 * @author Zach Ferguson
 * @version Sep 21, 2023
 *
 */
public interface Grade extends Comparable<Object>
{
  /**
   * Simple getter for returning the key of this object.
   * 
   * @return the key of this object
   */
  public String getKey();

  /**
   * Allows user/system to get the value of this Grade object.
   * 
   * @return the value of this object
   */
  public Double getValue();
}
