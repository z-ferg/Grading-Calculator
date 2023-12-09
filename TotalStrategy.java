package grading;

/**
 * A GradingStrategy that calculates the total of all Grade objects.
 * 
 * @author Prof. David Bernstein, James Madison University
 *
 */
public class TotalStrategy extends WeightedTotalStrategy
{
  /**
   * Default Constructor.
   */
  public TotalStrategy()
  {
    super(null);
  }
}
