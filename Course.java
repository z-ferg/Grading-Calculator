package grading.io;
import grading.*;
import java.util.*;

/**
 * An encapsulation of a Course.
 * 
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class Course
{
  private GradingStrategy strategy;
  private List<Category>  categories;
  
  /**
   * Explicit Value Constructor.
   * 
   * @param categories  The List of Category objects in this Course
   * @param strategy    The GradingStrategy for this Course
   */
  public Course(final List<Category> categories, final GradingStrategy strategy)
  {
    this.categories = categories;
    this.strategy   = strategy;
  }
  
  /**
   * Get the List of Category objects for this Course.
   *
   * @return The List
   */
  public List<Category> getCategories()
  {
    return categories;
  }
  
  /**
   * Get the GradingStrategy for this Course.
   * 
   * @return The GradingStrategy
   */
  public GradingStrategy getStrategy()
  {
    return strategy;
  }
}
