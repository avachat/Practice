package avachat.euler;

import static avachat.euler.Prob72CountingFractions.solveProb72CountingFractions;

import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by Abhay Avachat 212552612 on 1/23/18.
 */
public class Prob72CountingFractionsTest {

  @Test
  public void testSolveProb72CountingFractions_2() throws Exception {
    Assert.assertEquals(1, solveProb72CountingFractions(2));
  }

  @Test
  public void testSolveProb72CountingFractions_3() throws Exception {
    Assert.assertEquals(3, solveProb72CountingFractions(3));
  }

  @Test
  public void testSolveProb72CountingFractions_4() throws Exception {
    Assert.assertEquals(5, solveProb72CountingFractions(4));
  }

  @Test
  public void testSolveProb72CountingFractions_5() throws Exception {
    Assert.assertEquals(9, solveProb72CountingFractions(5));
  }

  @Test
  public void testSolveProb72CountingFractions_6() throws Exception {
    Assert.assertEquals(11, solveProb72CountingFractions(6));
  }

  @Test
  public void testSolveProb72CountingFractions_7() throws Exception {
    Assert.assertEquals(17, solveProb72CountingFractions(7));
  }

  @Test
  public void testSolveProb72CountingFractions_8() throws Exception {
    Assert.assertEquals(21, solveProb72CountingFractions(8));
  }

  @Test
  public void testSolveProb72CountingFractions_9() throws Exception {
    Assert.assertEquals(27, solveProb72CountingFractions(9));
  }

}