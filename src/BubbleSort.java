//    Bubble sort is an algorithm that sorts elements of a list in ascending order. Bubble sort works by iterating through an array and
//    checking whether the current element is larger or smaller than the next element. If the current element is larger, then they swap.
//    If the current element is smaller, then it stays in place. Then, the pointer moves to the next index.
//    The algorithm typically iterates through the array numerous times, while no more elements are able to “bubble” up. The Java implementation looks like this:
//    The Bubble Sort algorithm utilizes two loops: an outer loop to iterate over each element in the input list, and an inner loop to iterate,
//    compare and exchange a pair of values in the list. The inner loop takes (N-1) iterations while the outer loop takes N iterations.
//    Hence, the Big-O runtime for the algorithm is the product of O(N) and O(N-1), which is O(N^2).

//pseudo code
//    while array is not sorted
//  for each value in array
//    if current value > next value
//    swap current value and next value
//    return array

//                    Best Case	    Worst Case	  Average Case	   Space Complexity
//        Bubble Sort	Ω(n)	     O(n^2)	        O(n^2)	          O(1)
//        Merge Sort	Ω(n log n)	 O(n log n)	    O(n log n)	      O(n)
//        Quicksort	    Ω(n log n)	 O(n^2)	        O(n log n)	      O(log n)



public class BubbleSort {

    public int[] bubbleSort(int[] input) {
        boolean swapping = true;
        while (swapping) {
            swapping = false;
            for (int i = 0; i < input.length - 1; i++) {
                if (input[i] > input[i+1]) {
                    swap(input, i, i+1);
                    swapping = true;
                }
            }
        }

        return input;
    }

    public static void swap(int[] arr, int indexOne, int indexTwo) {
        int temp = arr[indexTwo];
        arr[indexTwo] = arr[indexOne];
        arr[indexOne] = temp;
    }
}
