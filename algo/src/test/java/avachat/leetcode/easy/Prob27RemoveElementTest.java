package avachat.leetcode.easy;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * Created by Abhay Avachat 212552612 on 2/7/18.
 */
public class Prob27RemoveElementTest {

  @Test
  public void removeElement() throws Exception {

    Prob27RemoveElement testObj = new Prob27RemoveElement();

    int[] emptyArr = new int[0];
    assertEquals(0, testObj.removeElement(emptyArr, 0));

    int[] singletonArrayWithVal = new int[] {3};
    assertEquals(0, testObj.removeElement(singletonArrayWithVal, 3));

    int[] singletonArrayWithoutVal = new int[] {0};
    assertEquals(1, testObj.removeElement(singletonArrayWithoutVal, 3));

    int[] tupleArrayWithVal = new int[] {3, 3};
    assertEquals(0, testObj.removeElement(tupleArrayWithVal, 3));

    int[] tupleArrayWithoutVal = new int[] {0, 2};
    assertEquals(2, testObj.removeElement(tupleArrayWithoutVal, 3));

    int[] tripleArrayWithVal = new int[] {3, 3, 3};
    assertEquals(0, testObj.removeElement(tripleArrayWithVal, 3));

    int[] tripleArrayWithoutVal = new int[] {0, 2, 1};
    assertEquals(3, testObj.removeElement(tripleArrayWithoutVal, 3));

    int[] largeArrayWithVal = new int[] {3, 3, 3, 3, 3, 3};
    assertEquals(0, testObj.removeElement(largeArrayWithVal, 3));

    int[] largeArrayWithoutVal = new int[] {0, 2, 1, 4, 8, 1};
    assertEquals(6, testObj.removeElement(largeArrayWithoutVal, 3));

    int[] singleRemovableInTupleBegin = new int[] {3, 0};
    assertEquals(1, testObj.removeElement(singleRemovableInTupleBegin, 3));
    assertEquals(new int[] {0, 0}, singleRemovableInTupleBegin);

    int[] singleRemovableInTupleEnd = new int[] {0, 3};
    assertEquals(1, testObj.removeElement(singleRemovableInTupleEnd, 3));
    assertEquals(new int[] {0, 3}, singleRemovableInTupleEnd);

    int[] singleRemovableInTripleBegin = new int[] {3, 0, 1};
    assertEquals(2, testObj.removeElement(singleRemovableInTripleBegin, 3));
    assertEquals(new int[] {1, 0, 1}, singleRemovableInTripleBegin);

    int[] singleRemovableInTripleEnd = new int[] {1, 0, 3};
    assertEquals(2, testObj.removeElement(singleRemovableInTripleEnd, 3));
    assertEquals(new int[] {1, 0, 3}, singleRemovableInTripleEnd);

    int[] fewAtBegin = new int[] {3, 3, 3, 4, 5, 6, 7};
    assertEquals(4, testObj.removeElement(fewAtBegin, 3));
    assertEquals(new int[] {7, 6, 5, 4, 5, 6, 7}, fewAtBegin);

    int[] fewAtEnd = new int[] {7, 5, 6, 4, 3, 3, 3};
    assertEquals(4, testObj.removeElement(fewAtEnd, 3));
    assertEquals(new int[] {7, 5, 6, 4, 3, 3, 3}, fewAtEnd);

    int[] halfHalf = new int[] {3, 3, 3, 4, 5, 6};
    assertEquals(3, testObj.removeElement(halfHalf, 3));
    assertEquals(new int[] {6, 5, 4, 4, 5, 6}, halfHalf);

    int[] halfHalf1 = new int[] {3, 3, 3, 0, 4, 5, 6};
    assertEquals(4, testObj.removeElement(halfHalf1, 3));
    assertEquals(new int[] {6, 5, 4, 0, 4, 5, 6}, halfHalf1);

    int[] halfHalf2 = new int[] {3, 3, 3, 0, 0, 4, 5, 6};
    assertEquals(5, testObj.removeElement(halfHalf2, 3));
    assertEquals(new int[] {6, 5, 4, 0, 0, 4, 5, 6}, halfHalf2);

    int[] alternateEven = new int[] {3, 1, 3, 2, 3, 4, 3, 5};
    assertEquals(4, testObj.removeElement(alternateEven, 3));
    assertEquals(new int[] {5, 1, 4, 2, 3, 4, 3, 5}, alternateEven);

    int[] alternateOdd = new int[] {3, 1, 3, 2, 3, 4, 3, 5, 3, 6};
    assertEquals(5, testObj.removeElement(alternateOdd, 3));
    assertEquals(new int[] {6, 1, 5, 2, 4, 4, 3, 5, 3, 6}, alternateOdd);


  }

}