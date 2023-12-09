package grading;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will represent a "directory" of Grade objects.
 * 
 * This work complies with the JMU honor code
 * 
 * @author Zach Ferguson
 * @version Sep 21, 2023
 */
public class CompositeGrade extends AbstractGrade
{
  private Filter filter;
  private GradingStrategy strategy;
  private List<Grade> components;

  /**
   * The constructor for this class, calls the AbstractGrade constructor.
   * 
   * @param key
   *          the key representing this collection of grades
   * @throws IllegalArgumentException
   *           if the key is null or empty
   */
  public CompositeGrade(final String key) throws IllegalArgumentException
  {
    super(key);
    this.components = new ArrayList<Grade>();
  }

  /**
   * This method will add a Grade object (Leaf or Composite) to the list of components.
   * 
   * @param grade
   *          the Grade object to be added
   */
  public void add(final Grade grade)
  {
    this.components.add(grade);
  }

  /**
   * Simple getter method the component instance variable.
   * 
   * @return the components instance variable
   */
  public List<Grade> getComponents()
  {
    return this.components;
  }

  /**
   * Simple getter method the filter instance variable.
   * 
   * @return the filter instance variable
   */
  public Filter getFilter()
  {
    return this.filter;
  }

  /**
   * Simple getter method the strategy instance variable.
   * 
   * @return the strategy instance variable
   */
  public GradingStrategy getStrategy()
  {
    return this.strategy;
  }

  /**
   * Applies the filter if there is one and calculates the total if possible.
   * 
   * @return a Double of the total value of all the components
   */
  public Double getValue()
  {
    List<Grade> copy = this.components;

    try
    {
      if (this.filter != null)
      {
        copy = this.filter.apply(components);
      }

      if (this.strategy != null)
      {
        return this.strategy.calculate(this.getKey(), copy).getValue();
      }
      else
      {
        throw new SizeException();
      }
    }
    catch (SizeException e)
    {
      return null;
    }
  }

  /**
   * Setter method for the filter instance variable.
   * 
   * @param filter
   *          the filter to set it to
   */
  public void setFilter(final Filter filter)
  {
    this.filter = filter;
  }

  /**
   * Setter method for the strategy instance variable.
   * 
   * @param strategy
   *          the strategy to set it to
   */
  public void setStrategy(final GradingStrategy strategy)
  {
    this.strategy = strategy;
  }
}
