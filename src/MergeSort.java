
//Merge sort is a divide and conquer algorithm that achieves sorting in O(NlogN), best worst and average case are always NLogN.class
//requires a sort method that splits array elements into separate individual arrays , then merge method will merge elements while ordering them in the process.

//pseudocode for sort()
//        function mergeSort(arr)
//        if the length of arr equals 1
//        return arr
//
//        midIndex = the floor integer of (left + right) / 2
//        leftArray = arr from 0 to midIndex
//        rightArray = arr from midIndex to end
//
//        return merge(sort(leftArray), sort(rightArray))

//merge function pseudocode
//function merge(leftArray, rightArray)
//        sortedArray = int[]
//        rightPosition = 0
//        leftPosition = 0
//        currentIndex = 0
//        while leftArray is greater than starting leftPosition and rightArray is greater than the starting right position
//        if leftArray[leftPosition] is less than rightArray[rightPosition]
//        set sortedArray at currentIndex to left[leftPosition]
//        increment leftPosition by 1
//        else
//        set sortedArray at currentIndex to right[rightPosition]
//        increment rightPosition by 1
//
//        increment currentPosition by 1
//
//        copy the left and right sorted arrays into sortedArray
//
//        return sortedArray

import java.util.Arrays;

    public class MergeSort {

        public int[] sort(int arr[]) {
            int length = arr.length;
            if (length <= 1) {
                return arr;
            }
            int mid = Math.floorDiv(length, 2);
            int[] leftArray = Arrays.copyOfRange(arr, 0, mid);
            int[] rightArray = Arrays.copyOfRange(arr, mid, length);
            return merge(sort(leftArray), sort(rightArray));
        }

        public int[] merge(int left[], int[] right) {
            int[] merged = new int[left.length + right.length];
            int leftPos = 0;
            int rightPos = 0;
            int curIndex = 0;

            while (leftPos < left.length && rightPos < right.length) {
                if (left[leftPos] < right[rightPos]) {
                    merged[curIndex] = left[leftPos++];

                } else {
                    merged[curIndex] = right[rightPos++];

                }
                curIndex++;
            }

            System.arraycopy(left, leftPos, merged, curIndex, left.length - leftPos);
            System.arraycopy(right, rightPos, merged, curIndex, right.length - rightPos);
            return merged;
        }


        public static void main (String[] args) {
            int[] inputArr = {3, 45, 2, 90, 4, 7};
            MergeSort sorter = new MergeSort();
            System.out.println(Arrays.toString(sorter.sort(inputArr)));
        }
    }

