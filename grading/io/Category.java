package grading.io;
import grading.*;
import java.util.*;


/**
 * A Category of Grade objects (that includes an associated Filter
 * and GradingStrategy).
 * 
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class Category
{
  private Filter          filter;
  private GradingStrategy strategy;
  private List<Grade>     grades;
  private String          key;
  
  /**
   * Explicit Value Constructor.
   * 
   * @param key      The key used to identify this Category
   * @param grades   The List of Grade objects
   * @param filter   The Filter used by this Category
   * @param strategy The GradingStrategy used by this Category
   * @throws IllegalArgumentException If the key is invalid
   */
  public Category(final String key, final List<Grade> grades, final Filter filter, 
      final GradingStrategy strategy) throws IllegalArgumentException
  {
    if ((key == null) || (key.equals(""))) throw new IllegalArgumentException();

    this.key = key;
    this.grades = grades;
    this.filter = filter;
    this.strategy = strategy;
  }
  
  /**
   * Get the Filter associated with this Category.
   * 
   * @return  The Filter
   */
  public Filter getFilter()
  {
    return filter;
  }
  
  /**
   * Get the GradingStrategy associated with this Category.
   * 
   * @return  The GradingStrategy
   */
  public GradingStrategy getStrategy()
  {
    return strategy;
  }
  
  /**
   * Get the List of Grade objects for this Category.
   * 
   * @return The List
   */
  public List<Grade> getGrades()
  {
    return grades;
  }
  
  /**
   * Get the key used to identify this Category.
   * 
   * @return The key.
   */
  public String getKey()
  {
    return key;
  }
}
