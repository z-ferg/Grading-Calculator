package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import grading.CompositeGrade;
import grading.Grade;

/**
 * This class will hold CategoryFields and the Menu items.
 * 
 * This work complies with the JMU Honor Code
 * 
 * @author Zach Ferguson
 * @version Sep 29, 2023
 */
public class GradientWindow extends JFrame implements ActionListener
{
  public static final long serialVersionUID = 1L;

  private List<CategoryField> categoryFields;
  private CompositeGrade template;

  private String exitCommand = "exit_command";
  private String calcCommand = "calc_command";
  private String restCommand = "rest_command";

  /**
   * The constructor for this class, will set create all components for the frame.
   * 
   * @param template
   *          the template that holds the CompositeGrades for categories and the filter/strategy
   */
  public GradientWindow(final CompositeGrade template)
  {
    super();
    this.template = template;
    this.categoryFields = new ArrayList<CategoryField>();

    setupLayout();
  }

  private void setupLayout()
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setTitle("Gradient by ferguszo");

    menuStuff();

    getContentPane().add(Box.createHorizontalStrut(20));

    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    for (Grade g : template.getComponents())
    {
      categoryFields.add(new CategoryField((CompositeGrade) g));
    }

    for (CategoryField cf : categoryFields)
    {
      getContentPane().add(cf);
    }

    getContentPane().add(Box.createHorizontalGlue());

    setVisible(true);
    setResizable(false);
    pack();
  }

  private void menuStuff()
  {
    JMenuBar bar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem exit = new JMenuItem("Exit");
    exit.setActionCommand(exitCommand);
    exit.addActionListener(this);
    file.add(exit);
    bar.add(file);

    JMenu edit = new JMenu("Edit");
    JMenuItem calc = new JMenuItem("Calculate");
    calc.setActionCommand(calcCommand);
    calc.addActionListener(this);
    JMenuItem reset = new JMenuItem("Reset");
    reset.setActionCommand(restCommand);
    reset.addActionListener(this);
    edit.add(calc);
    edit.add(reset);
    bar.add(edit);

    bar.add(Box.createHorizontalGlue());

    getContentPane().add(bar);
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    if (e.getActionCommand().equals(exitCommand))
    {
      System.exit(0);
    }
    else if (e.getActionCommand().equals(calcCommand))
    {
      CompositeGrade calc = new CompositeGrade(template.getKey());
      calc.setFilter(template.getFilter());
      calc.setStrategy(template.getStrategy());

      for (CategoryField cf : categoryFields)
      {
        calc.add(cf.getGrade());
      }

      JOptionPane.showMessageDialog(null, calc.getValue(), "Course Grade",
          JOptionPane.INFORMATION_MESSAGE, null);
    }
    else if (e.getActionCommand().equals(restCommand))
    {
      for (CategoryField cf : categoryFields)
      {
        cf.reset();
      }
    }
  }
}
