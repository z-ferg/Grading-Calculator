import java.lang.reflect.*;
import javax.swing.*;

import grading.CompositeGrade;
import grading.Grade;
import grading.LeafGrade;
import gui.CategoryField;
import gui.LeafGradeField;

/**
 * The main class for the CashMachine application.
 */
public class GUITest implements Runnable
{
    /**
     * The entry point of the application (which is executed in the
     * main thread of execution).
     *
     * @param args   The command-line arguments
     */
    public static void main(String[] args) throws InterruptedException, InvocationTargetException
    {
      SwingUtilities.invokeAndWait(new GUITest());
      
    }
    
    /**
     * The code that is executed in the event dispatch thread.
     */
    public void run()
    {
      JFrame window = new JFrame();   
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setSize(400, 400);
      
      CompositeGrade cg = new CompositeGrade("GUI Test!");
      cg.add(new LeafGrade("HW 1", 20));
      cg.add(new LeafGrade("HW 2", 30));
      cg.add(new LeafGrade("HW 3", 40));
      CategoryField cf = new CategoryField(cg);
      window.add(cf);
      window.setVisible(true);
      window.setResizable(false);
    }
}
