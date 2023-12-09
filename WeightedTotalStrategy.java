package grading;

import java.util.List;
import java.util.Map;

/**
 * A GradingStrategy that calculates the weighted total of a List of Grade objects.
 *
 * @author Prof. David Bernstein, James Madison University
 */
public class WeightedTotalStrategy implements GradingStrategy
{
  private Map<String, Double> weights;

  /**
   * Default Constructor.
   */
  public WeightedTotalStrategy()
  {
    this(null);
  }

  /**
   * Explicit Value Constructor.
   *
   * @param weights
   *          The Map of weights to use (or null for equal weights)
   */
  public WeightedTotalStrategy(final Map<String, Double> weights)
  {
    this.weights = weights;
  }

  /**
   * Calculate a (in this case, weighted total) Grade from a List of Grade objects (required by
   * GradingSTrategySystem).
   *
   * Notes: (2.4.1.1) If the Map is null then a weight of 1.0 is used.
   * 
   * (2.4.1.2) If there is no weight for a particular Grade (i.e., associated with the key for that
   * Grade) then a weight of 1.0 is used.
   * 
   * (2.4.1.3) Weights that are less than 0.0 are treated as 0.0.
   * 
   * (2.4.2) Missing Grade values are treated as 0.0.
   * 
   * (2.2-2.3) If the List is null or there are no Grade objects then this method throws a
   * SizeException.
   * 
   * @param key
   *          The key to use for the resulting Grade
   * @param grades
   *          The List of Grade objects to use in the calculation
   * @return The resulting Grade
   * @throws SizeException
   *           if the List is null or empty
   */
  public LeafGrade calculate(final String key, final List<Grade> grades) throws SizeException
  {
    double total, w;

    // Error checking
    if ((grades == null) || (grades.size() == 0))
      throw new SizeException("No Grades");

    total = 0.0;
    w = 1.0;

    for (Grade g : grades)
    {
      if (weights == null)
        w = 1.0;
      else
        w = Missing.doubleValue(weights.get(g.getKey()), 1.0);

      if (w < 0.0)
        w = 0.0;
      
      total += w * Missing.doubleValue(g.getValue());
    }

    return new LeafGrade(key, Double.valueOf(total));
  }

}
