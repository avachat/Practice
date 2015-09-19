package avachat.hackrank.algorithm.sorting;

import org.testng.Assert;
import org.testng.annotations.Test;

import static avachat.hackrank.algorithm.sorting.QuickSortInPlace.partition;
import static avachat.hackrank.algorithm.sorting.QuickSortInPlace.recursiveQuickSort;

/**
 * Created by avachat on 9/17/15.
 */
public class QuickSortInPlaceTest {

    @Test
    public void testPartitionFullArrayEdge() throws Exception {

        int[] ar = null;

        // 2 elements array - no-op equal
        // pivot is always swapped with equal element if so happens that way
        ar = new int[] {7, 7};
        Assert.assertEquals(1, partition(ar, 0, 1));
        Assert.assertEquals(ar, new int[] {7, 7});

        // 2 elements array - no-op ascending
        ar = new int[] {3, 4};
        Assert.assertEquals(0, partition(ar, 0, 1));
        Assert.assertEquals(ar, new int[] {3, 4});

        // 2 elements array - change
        ar = new int[] {5, 2};
        Assert.assertEquals(1, partition(ar, 0, 1));
        Assert.assertEquals(ar, new int[] {2, 5});

        // 3 elements no-op, pivot is the lowest
        ar = new int[] {5, 6, 7};
        Assert.assertEquals(0, partition(ar, 0, 2));
        Assert.assertEquals(ar, new int[] {5, 6, 7});

        // 3 elements change
        ar = new int[] {5, 2, 7};
        Assert.assertEquals(1, partition(ar, 0, 2));
        Assert.assertEquals(ar, new int[] {2, 5, 7});

        // 3 elements change, pivot equals 1st
        ar = new int[] {5, 5, 7};
        Assert.assertEquals(1, partition(ar, 0, 2));
        Assert.assertEquals(ar, new int[] {5, 5, 7});

        // 3 elements no change, last 2 elements are same
        ar = new int[] {5, 7, 7};
        Assert.assertEquals(0, partition(ar, 0, 2));
        Assert.assertEquals(ar, new int[] {5, 7, 7});

        // 3 elements change, pivot is highest, last 2 elements are same
        ar = new int[] {5, 3, 3};
        Assert.assertEquals(2, partition(ar, 0, 2));
        Assert.assertEquals(ar, new int[] {3, 3, 5});

        // 3 elements change, pivot is highest
        ar = new int[] {5, 1, 3};
        Assert.assertEquals(2, partition(ar, 0, 2));
        Assert.assertEquals(ar, new int[] {3, 1, 5});

        // 3 elements no-op, all elements same
        ar = new int[] {1, 1, 1};
        Assert.assertEquals(2, partition(ar, 0, 2));
        Assert.assertEquals(ar, new int[] {1, 1, 1});

    }


    @Test
    public void testPartitionFullArrayBig() throws Exception {

        int[] ar = null;

        // 4 elements array - no-op equal
        // pivot is always swapped with equal element if so happens that way
        ar = new int[] {7, 7, 7, 7};
        Assert.assertEquals(3, partition(ar, 0, 3));
        Assert.assertEquals(ar, new int[] {7, 7, 7, 7});

        // 5 elements array - no-op equal
        // pivot is always swapped with equal element if so happens that way
        ar = new int[] {7, 7, 7, 7, 7};
        Assert.assertEquals(4, partition(ar, 0, 4));
        Assert.assertEquals(ar, new int[] {7, 7, 7, 7, 7});

        // 4 elements array - no-op sorted
        ar = new int[] {1, 2, 3, 4};
        Assert.assertEquals(0, partition(ar, 0, 3));
        Assert.assertEquals(ar, new int[] {1, 2, 3, 4});

        // 5 elements, reverse sorted
        ar = new int[] {9, 6, 3, 2, 1};
        Assert.assertEquals(4, partition(ar, 0, 4));
        Assert.assertEquals(ar, new int[] {1, 6, 3, 2, 9});

        // 4 elements, proper partition
        ar = new int[] {8, 2, 3, 9};
        Assert.assertEquals(2, partition(ar, 0, 3));
        Assert.assertEquals(ar, new int[] {3, 2, 8, 9});

        // 4 elements, proper partition
        ar = new int[] {6, 2, 9, 7};
        Assert.assertEquals(1, partition(ar, 0, 3));
        Assert.assertEquals(ar, new int[] {2, 6, 9, 7});

        // 6 elements, random elements
        ar = new int[] {6, 2, 9, 7, 1, 5};
        Assert.assertEquals(3, partition(ar, 0, 5));
        Assert.assertEquals(ar, new int[] {1, 2, 5, 6, 7, 9});

    }

    @Test
    public void testPartitionPartial() throws Exception {

        int[] ar = null;

        // 2 elements at the begining of the array
        ar = new int[] {7, 7, 1, 2, 3, 4, 9};
        Assert.assertEquals(1, partition(ar, 0, 1));
        Assert.assertEquals(ar, new int[] {7, 7, 1, 2, 3, 4, 9});

        // 2 elements at the end of the array
        ar = new int[] {1, 2, 3, 4, 9, 8, 7};
        Assert.assertEquals(6, partition(ar, 5, 6));
        Assert.assertEquals(ar, new int[] {1, 2, 3, 4, 9, 7, 8});

        // 2 elements in the middle
        ar = new int[] {1, 2, 6, 5, 9, 8, 8};
        Assert.assertEquals(3, partition(ar, 2, 3));
        Assert.assertEquals(ar, new int[] {1, 2, 5, 6, 9, 8, 8});

        // 2 elements in the middle
        ar = new int[] {1, 2, 4, 5, 9, 8, 8};
        Assert.assertEquals(2, partition(ar, 2, 3));
        //System.out.print(Arrays.toString(ar));
        Assert.assertEquals(ar, new int[] {1, 2, 4, 5, 9, 8, 8});

        // 3 elements at the begining of the array
        ar = new int[] {7, 7, 3, 1, 2, 3, 4, 9};
        Assert.assertEquals(2, partition(ar, 0, 2));
        Assert.assertEquals(ar, new int[] {3, 7, 7, 1, 2, 3, 4, 9});

        // 3 elements at the end of the array
        ar = new int[] {1, 2, 3, 4, 9, 8, 7};
        Assert.assertEquals(6, partition(ar, 4, 6));
        Assert.assertEquals(ar, new int[] {1, 2, 3, 4, 7, 8, 9});

        // 3 elements in the middle
        ar = new int[] {1, 2, 6, 5, 9, 8, 8};
        Assert.assertEquals(3, partition(ar, 2, 4));
        Assert.assertEquals(ar, new int[] {1, 2, 5, 6, 9, 8, 8});

        // 3 elements in the middle
        ar = new int[] {1, 2, 4, 5, 9, 8, 8};
        Assert.assertEquals(2, partition(ar, 2, 4));
        //System.out.print(Arrays.toString(ar));
        Assert.assertEquals(ar, new int[] {1, 2, 4, 5, 9, 8, 8});

    }


    @Test
    public void testRecursiveQuickSort() throws Exception {

        int[] ar = null;

        // random
        ar = new int[] {4, 6, 2, 3, 8, 7, 1, 9, 0, 5};
        recursiveQuickSort(ar, 0, 9);
        Assert.assertEquals(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, ar);

        // single element
        ar = new int[] {4};
        recursiveQuickSort(ar, 0, 0);
        Assert.assertEquals(new int[] {4}, ar);

        // 2 elements, sorted
        ar = new int[] {4, 7};
        recursiveQuickSort(ar, 0, 1);
        Assert.assertEquals(new int[] {4, 7}, ar);

        // 2 elements, reverse sorted
        ar = new int[] {4, 2};
        recursiveQuickSort(ar, 0, 1);
        Assert.assertEquals(new int[] {2, 4}, ar);

        // 3 elements, sorted
        ar = new int[] {4, 5, 7};
        recursiveQuickSort(ar, 0, 2);
        Assert.assertEquals(new int[] {4, 5, 7}, ar);

        // 3 elements, reverse sorted
        ar = new int[] {9, 4, 2};
        recursiveQuickSort(ar, 0, 2);
        //System.out.println(Arrays.toString(ar));
        Assert.assertEquals(new int[] {2, 4, 9}, ar);

        // sorted
        ar = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        recursiveQuickSort(ar, 0, 9);
        Assert.assertEquals(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, ar);

        // reverse sorted
        ar = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        recursiveQuickSort(ar, 0, 9);
        Assert.assertEquals(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, ar);

    }
}