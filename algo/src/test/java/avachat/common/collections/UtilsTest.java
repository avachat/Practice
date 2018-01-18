package avachat.common.collections;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Abhay Avachat 212552612 on 1/18/18.
 */
public class UtilsTest {

  @Test
  public void testIntersectionEmptyLists()  {
    List<Integer> list1 = new ArrayList<>(0);
    List<Integer> list2 = new ArrayList<>(0);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertTrue(intersection.isEmpty());
  }

  @Test
  public void testIntersectionEmptyLeft() {
    List<Integer> list1 = ImmutableList.of(1, 2, 3);
    List<Integer> list2 = new ArrayList<>(0);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertTrue(intersection.isEmpty());
  }

  @Test
  public void testIntersectionEmptyRight() {
    List<Integer> list1 = new ArrayList<>(0);
    List<Integer> list2 = ImmutableList.of(1, 2, 3);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertTrue(intersection.isEmpty());
  }

  @Test
  public void testIntersectionSingletonEmpty() {
    List<Integer> list1 = ImmutableList.of(1);
    List<Integer> list2 = ImmutableList.of(2);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertTrue(intersection.isEmpty());
  }

  @Test
  public void testIntersectionSingleton() {
    List<Integer> list1 = ImmutableList.of(1);
    List<Integer> list2 = ImmutableList.of(1);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertEquals(intersection.size(), 1);
  }

  @Test
  public void testIntersectionEqual() {
    List<Integer> list1 = ImmutableList.of(1, 2, 3, 4);
    List<Integer> list2 = ImmutableList.of(1, 2, 3, 4);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertEquals(intersection.size(), 4);
    Assert.assertEquals(intersection, list1);
  }
  @Test
  public void testIntersectionInterleaved() {
    List<Integer> list1 = ImmutableList.of(1, 3, 5, 7, 9, 11, 13);
    List<Integer> list2 = ImmutableList.of(2, 4, 6, 8, 10);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertEquals(intersection.size(), 0);
  }

  @Test
  public void testIntersectionStaggered() {
    List<Integer> list1 = ImmutableList.of(1, 2, 3, 4);
    List<Integer> list2 = ImmutableList.of(5, 6, 7, 8);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertEquals(intersection.size(), 0);
  }

  @Test
  public void testIntersectionCommonAtBeginning() {
    List<Integer> list1 = ImmutableList.of(1, 3, 5, 7, 9, 11, 13);
    List<Integer> list2 = ImmutableList.of(1, 3, 4, 6, 8, 10);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertEquals(intersection.size(), 2);
    Assert.assertEquals(intersection, ImmutableList.of(1, 3));
  }

  @Test
  public void testIntersectionCommonAtEnd() {
    List<Integer> list1 = ImmutableList.of(1, 3, 5, 7, 9, 11, 13);
    List<Integer> list2 = ImmutableList.of(2, 4, 6, 8, 10, 11, 13);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertEquals(intersection.size(), 2);
    Assert.assertEquals(intersection, ImmutableList.of(11, 13));
  }

  @Test
  public void testIntersectionAtMiddle() {
    List<Integer> list1 = ImmutableList.of(1, 3, 5, 7, 9, 11, 13);
    List<Integer> list2 = ImmutableList.of(2, 4, 5, 7, 9, 12, 14);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertEquals(intersection.size(), 3);
    Assert.assertEquals(intersection, ImmutableList.of(5, 7, 9));
  }

  @Test
  public void testIntersectionAtRandom() {
    List<Integer> list1 = ImmutableList.of(1, 3, 5, 7, 9, 11, 13);
    List<Integer> list2 = ImmutableList.of(2, 4, 5, 6, 9, 12, 14);
    List<Integer> intersection = Utils.intersectionSortedList(list1, list2);
    Assert.assertEquals(intersection.size(), 2);
    Assert.assertEquals(intersection, ImmutableList.of(5, 9));
  }

}