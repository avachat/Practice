package avachat.common.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Abhay Avachat 212552612 on 1/18/18.
 */
public class Utils {

 public static <T extends Comparable<T>> List<T> intersectionSortedList(List<T> list1, List<T> list2) {

  List<T> intersection = new ArrayList<T>();

  Iterator<T> iter1 = list1.iterator();
  Iterator<T> iter2 = list2.iterator();

  boolean advance1 = true;
  boolean advance2 = true;
  T item1 = null;
  T item2 = null;

  while  (iter1.hasNext() && iter2.hasNext()) {
   if (advance1) {
    item1 = iter1.next();
   }
   if (advance2) {
    item2 = iter2.next();
   }
   int comparison = item1.compareTo(item2);
   if ( comparison == 0) {
    intersection.add(item1);
    advance1 = true;
    advance2 = true;
   }
   else if (comparison < 0) {
    advance1 = true;
    advance2 = false;
   } else {
    advance1 = false;
    advance2 = true;
   }
  }

  return intersection;
 }

}
