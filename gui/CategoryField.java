package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import grading.CompositeGrade;
import grading.Grade;
import grading.LeafGrade;

/**
 * This class will hold LeafGradeField objects and represent a category.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * @author Zach Ferguson
 * @version Sep 29, 2023
 */
public class CategoryField extends JPanel
{
  public static final long serialVersionUID = 1L;
  private CompositeGrade template;
  private List<LeafGradeField> fields;

  /**
   * The constructor for this class, it will add all LGF objects to the panel.
   * 
   * @param template
   *          this will give the filter/strategy as well as the title
   */
  public CategoryField(final CompositeGrade template)
  {
    super();
    this.template = template;
    fields = new ArrayList<LeafGradeField>();

    setupLayout();
  }

  private void setupLayout()
  {
    setBorder(BorderFactory.createTitledBorder(template.getKey()));
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    for (Grade g : template.getComponents())
    {

      Box b = Box.createHorizontalBox();
      LeafGradeField lgf = new LeafGradeField((LeafGrade) g);
      b.add(lgf);
      b.add(Box.createHorizontalGlue());
      add(b);
      fields.add(lgf);
    }
  }

  /**
   * This will return the a CompositeGrade with the filtered final grade of all the child
   * LeafGradeFields.
   * 
   * @return a Grade object to be used in overall course grade evaluation
   */
  public Grade getGrade()
  {
    CompositeGrade ret = new CompositeGrade(template.getKey());
    ret.setFilter(template.getFilter());
    ret.setStrategy(template.getStrategy());

    for (LeafGradeField lgf : fields)
    {
      ret.add(lgf.getGrade());
    }

    return ret;
  }

  /**
   * Simple getter for the key of the template instance variable.
   * 
   * @return this will return the title this category holds
   */
  public String getKey()
  {
    return template.getKey();
  }

  /**
   * This will loop over all of the LeafGradeField objects to reset them.
   */
  public void reset()
  {
    for (LeafGradeField lgf : fields)
    {
      lgf.reset();
    }
  }
}
