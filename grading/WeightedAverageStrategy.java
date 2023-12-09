package grading;

import java.util.*;

/**
 * A GradingStrategy that calculates the weighted average of a collection of grades.
 *
 * @author Ann E. Coda, Sagacious Media
 */
public class WeightedAverageStrategy implements GradingStrategy
{
  private static final Double ZERO = new Double(0.0);

  private boolean shouldIgnoreMissing;
  private Map<String, Double> weights;

  /**
   * Default Constructor.
   */
  public WeightedAverageStrategy()
  {
    this(null, true);
  }

  /**
   * Explicit Value Constructor.
   *
   * @param weights
   *          The Map of weights to use (or null for equal weights)
   */
  public WeightedAverageStrategy(final Map<String, Double> weights)
  {
    this(weights, true); // Calling this is better, and super does not have constructor
  }

  /**
   * Explicit Value Constructor.
   *
   * @param weights
   *          The Map of weights to use (or null for equal weights)
   * @param shouldIgnoreMissing
   *          true to ignore missing values; false to treat as 0
   */
  public WeightedAverageStrategy(final Map<String, Double> weights,
      final boolean shouldIgnoreMissing)
  {
    this.shouldIgnoreMissing = shouldIgnoreMissing; // Must use this before both of these
    this.weights = weights;
  }

  /**
   * Use this GradingStrategy to calculate a numeric grade.
   *
   * Note: If there are no weights (i.e., the weights Map is null) then each grade is assumed to
   * have a weight of 1. If there are weights but an individual Grade does not have a corresponding
   * weight its weight is assumed to be 0.0.
   *
   * @param key
   *          The key for the Grade to return
   * @param grades
   *          The collection of grades
   * @return The numeric grade
   */
  public LeafGrade calculate(final String key, final List<Grade> grades) // Must return a LeafGrade
                                                                         // object
  {
    double denominator, numerator, w;
    Double grade, weight;

    // Early return
    if ((grades == null) || (grades.size() == 0)) // Check for null first to avoid nullPointer
      return new LeafGrade(key, ZERO);

    numerator = 0.0;
    denominator = 0.0;

    for (Grade g : grades)
    {
      grade = g.getValue();

      if (weights == null) // All weights are unspecified
      {
        w = 1.0; // Make this 1 so that it will still be weighted when weights is null.
      }
      else if (weights.containsKey(g.getKey()) && weights.get(g.getKey()) != null)
      {// Check that weights contains proper key
       // weight = weights.get(g.getKey()); This is not needed
        w = weights.get(g.getKey());
      }
      else
      { // This grade does not have a weight in the weights map, it is unspecified
        w = 0.0;
      }

      if (grade == null)
      {
        // Handle a missing Grade
        if (!shouldIgnoreMissing)
        {
          numerator += 0.0; // This line is not really needed but provides context
          denominator += w;
        }
      }
      else
      {
        // Handle an existing Grade
        numerator += w * grade;
        denominator += w;
      }
    }
    if (denominator == 0)
    {// Check if either list is empty or all weights were 0
      return new LeafGrade(key, ZERO); // Return new LeafGrade to avoid divide by 0
    }

    String s = String.format("%.2f", numerator / denominator); // Use this to truncate to 2 decimal
                                                               // places
    return new LeafGrade(key, Double.parseDouble(s));
  }
}
