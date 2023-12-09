package gui;

import grading.Grade;
import grading.LeafGrade;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * This class will hold the data for a single grade
 * 
 * This work complies with the JMU Honor Code.
 * 
 * @author Zach Ferguson
 * @version Sep 29, 2023
 */
public class LeafGradeField extends JTextField
{
  public static final long serialVersionUID = 1L;
  private LeafGrade template;

  /**
   * The constructor for the class, acts as a JTextField component itself.
   * 
   * @param template
   *          the starting value/key for this grade
   */
  public LeafGradeField(final LeafGrade template)
  {
    super(5);

    this.template = template;
    setupLayout();
  }

  private void setupLayout()
  {
    setBorder(BorderFactory.createTitledBorder(template.getKey()));
    setLayout(new FlowLayout());
    if (template.getValue() != null)
    {
      setText(template.getValue().toString());
    }
    else
    {
      setText("");
    }
    setSize(getMinimumSize());
    setForeground(Color.BLUE);
    setFont(new Font(null, Font.BOLD, 12));
  }

  /**
   * Will return a Grade representing the text field (if possible).
   * 
   * @return a Grade object with the key and double value of this text area
   */
  public Grade getGrade()
  {
    return LeafGrade.parseLeafGrade(getKey(), getText());
  }

  /**
   * Simple getter method for the key of this template.
   * 
   * @return the title of this LeafGradeField
   */
  public String getKey()
  {
    return template.getKey();
  }

  /**
   * Simple method that sets text to blank.
   */
  public void reset()
  {
    setText("");
  }

  @Override
  public Dimension getMaximumSize()
  {
    return getPreferredSize();
  }
}
