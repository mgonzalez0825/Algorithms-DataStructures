import java.util.LinkedList;

public class BinarySearch {

        public static void main(String[] args) {

            int[] array = {2, 3, 56, 7, 7, 57, 34, 3, 56};
            int[] array1 = {2, 3, 5, 7, 23, 57, 77, 78, 98};
            int target = 57;
            int index = linearSearch(array, target);
            int max = linearSearchMax(array);
            int indexOf = binarySearch(array1, target);
            System.out.println(indexOf);
            System.out.println(index);
            System.out.println(max);
        }



        //linear Search O(n) linear time complexity
        static int linearSearch(int[] newArray, int num) {

            for (int i = 0; i < newArray.length; i++) {

                if (newArray[i] == num) {
                    return i;
                }

            }
            return -1;
        }

    static int linearSearchMax(int[] newArray) {

        int max_value = newArray[0];

        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] > max_value) {
                max_value = newArray[i];
            }

        }
        return max_value;
    }



//    1. Initialize the left and right indices as 0 and the length of the array.
//    2. Create a while loop that continues to execute until the left index equals the right index.
//    3. Get the value at the index that falls in the middle of left and right.
//    4. Return the index if the mid-value is equal to target.
//    5. Set left equal to the current index plus one if the target is greater than the value.
//    6. Set right equal to the current index minus one if the target is less than the value.

        static int binarySearch(int[] searchable, int target) {


            int left = 0;
            int right = searchable.length;


            while (right > left) {

                int mid = Math.floorDiv((left + right), 2);
                int midValue = searchable[mid];

                if (midValue < target) {
                    left = mid + 1;
                } else if (midValue > target) {
                    right = mid;
                } else return mid;
            }
            return -1;

        }
    public static int binarySearchRecursive(int[] arrays, int n,int l,int r) {

        if(l < r) {
            int mid = Math.floorDiv(l + r,2);


            if(arrays[mid] == n) {
                return mid;
            }else if(arrays[mid] > n) {
                return binarySearchRecursive(arrays,n,0,mid);
            }else return binarySearchRecursive(arrays,n,mid+1,r);

        }
        return -1;

    }

    }




