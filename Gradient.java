import grading.*;
import grading.io.*;
import gui.*;
import java.io.*;

/**
 * An application for calculating the numeric grade for a course from the grades on individual
 * assignments.
 * 
 * @version 4.0
 * @author Sagacious Media
 *
 */
public class Gradient
{
  /**
   * The entry point for the application.
   * 
   * @param args
   *          args[0] contains the path (relative or absolute) to the .grd file
   */
  public static void main(String[] args)
  {
    // Early exit
    if ((args == null) || (args.length < 1))
    {
      System.err.println("You must enter a file name.");
      System.exit(1);
    }

    try
    {
      BufferedReader in = new BufferedReader(new FileReader(args[0]));
      String line = in.readLine();
      int categories = Integer.parseInt(line);
      CompositeGrade course = CourseReader.readCourse(in, categories);
      in.close();

      new GradientWindow(course);
      
    }
    catch (IOException ioe)
    {
      System.out.println("Unable to open file.");
      System.exit(1);
    }
  }
}
