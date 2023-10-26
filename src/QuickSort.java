//Quicksort is an unusual algorithm in that the worst case runtime is O(N^2), but the average case is O(N * logN).
// We typically only discuss the worst case when talking about an algorithm’s runtime, but for Quicksort it’s so uncommon that we generally refer to it as O(N * logN).

//Quicksort is an efficient recursive algorithm for sorting arrays or lists of values. The algorithm is a comparison sort,
// where values are ordered by a comparison operation such as > or <.
//
//        Quicksort uses a divide and conquer strategy, breaking the problem into smaller sub-problems until the solution is so clear there’s nothing to solve.
//
//        The problem: many values in the array which are out of order.
//
//        The solution: break the array into sub-arrays containing at most one element. One element is sorted by default!
//
//        We choose a single pivot element from the list. Every other element is compared with the pivot, which partitions the array into three groups.
//
//        A sub-array of elements smaller than the pivot.
//        The pivot itself.
//        A sub-array of elements greater than the pivot.

//pseudo Code for partition function
//int partition (int[] arr, int leftPointer, int rightPointer) {
//        Pivot = value of arr at (average of leftPointer and rightPointer, rounded down)
//        While leftPointer and rightPointer don't meet (i.e. the pointers haven't hit all the elements yet)
//        Increment leftPointer until element pointed to is > pivot
//        Decrement rightPointer until element pointed to is < pivot
//    If leftPointer less than rightPointer:
//            Swap elements at leftPointer and rightPointer
//            return leftPointer as the partition point

//Pseudo code for quickSort fucntion
//public int[] quickSort(int[] arr, int start, int end) {
//        if (end > start) {
//        int partition = partition(arr, start, end);
//        // If there is more than one element on left side, quicksort on all the elements from start up to the partition point
//        // If there is more than one element on right side, quicksort on all the elements between partition point + 1 and end
//        }
//        return arr; // Base case code
//        }
//            }

import java.util.Arrays;
public class QuickSort {

        public int[] quickSort(int[] arr) {
            if (arr.length <= 1) {
                return arr;
            }
            return quickSort(arr, 0, arr.length - 1);
        }

        public int[] quickSort(int[] arr, int start, int end) {
            if (end > start) {
                int partition = partition(arr, start, end);
                if (start < partition) {
                    quickSort(arr, start, partition);
                }
                if (partition + 1 < end) {
                    quickSort(arr, partition + 1, end);
                }
            }
            return arr;
        }

        public int partition(int[] arr, int leftPointer, int rightPointer) {
            //System.out.println("Partitioning the current subarray:");
            //System.out.println(Arrays.toString(Arrays.copyOfRange(arr, leftPointer, rightPointer + 1)));

            int pivot = arr[Math.floorDiv((leftPointer + rightPointer), 2)];
            //System.out.println("The pivot value is: " + pivot);

            while (leftPointer < rightPointer) {
                while (arr[leftPointer] < pivot) {
                    leftPointer++;
                    //System.out.println("Incrementing left pointer to " + arr[leftPointer]);
                }
                while (arr[rightPointer] > pivot) {
                    rightPointer--;
                    // System.out.println("Decrementing right pointer to " + arr[rightPointer]);
                }
                if (leftPointer < rightPointer) {
                    //System.out.println("Swapping " + arr[leftPointer] + " and " + arr[rightPointer]);
                    swap(arr, leftPointer, rightPointer);
                }
            }
            return leftPointer;
        }

        public static void main(String[] args) {
            QuickSort qs = new QuickSort();
            int[] unsorted = {39, 15, 24, 35, 76, 19};
            System.out.println("Sorting the array " + Arrays.toString(unsorted));
            qs.quickSort(unsorted);
            System.out.println("After sorting: " + Arrays.toString(unsorted));
        }


public static int[] swap(int[] arr,int indexOne,int indexTwo){
    int temp = arr[indexTwo];
    arr[indexTwo] = arr[indexOne];
    arr[indexOne] = temp;

    return arr;

}
}
