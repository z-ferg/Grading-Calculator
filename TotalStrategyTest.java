package testing;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import grading.TotalStrategy;

public class TotalStrategyTest
{
  @Test
  public void testConstructor() {
    TotalStrategy ts = new TotalStrategy();
    assertNotNull(ts);
  }
}
