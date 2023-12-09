package grading;

/**
 * A checked exception that indicates that a size problem has occurred.
 * 
 * @author Prof. David Bernstein, James Madison University
 */
public class SizeException extends Exception
{
  public static final long serialVersionUID = 1L;

  /**
   * Default Constructor.
   */
  public SizeException()
  {
    super();
  }

  /**
   * Explicit Value Constructor.
   * 
   * @param message
   *          The human-readable message
   */
  public SizeException(final String message)
  {
    super(message);
  }
}
