//tech interview steps
//        Clarify the Problem
//        Create Inputs and edge cases
//        Outline the Solution
//        Code the Solution
//        Test the Solution
//        Analyze the Solution
//
//Dynamic Programming is a programming technique used to solve recursive problems more efficiently. Specifically,
//        it adds time efficiency, and it does so by taking advantage of data structures to store reusable solutions
//        to intermediate steps, thus saving redundant computations. It’s a way of solving problems with recursive relationships
//        by solving smaller problems and building up to the solution to the original problem.

import java.util.*;
import java.util.HashMap;

//Memoization
//        Memoization is a specialized form of caching used to store the result of a function call. The next time that function is called,
//        if the result of that function call is already stored somewhere, we’ll retrieve that instead of running the function itself again. Memoization can result in much faster overall execution times (although it can increase memory requirements as function results are stored in memory).
//
//        Memoization is a great technique to use alongside recursion. The memo can even be saved between function calls if it’s being used
//        for common calculations in a program.
public class DynamicProgramming {


    //pseudo for fibonacci
//    fib(n)
//  if n is 0 or 1
//            return n
//  else
//          return fib(n - 1) + fib(n - 2)
public static int Fibonacci(int n){
    if(n == 0 || n == 1){
        return n;
    }else{
        return Fibonacci(n-1) + Fibonacci(n-2);
    }
}

//pseudocode
//Create a memo map
//
//    fibMemo(n, map)
//  if n is 0 or 1
//            return n
//  if n key exists in map
//    return map.get(n)
//            else
//    calculate current fibonacci number through a recursive call
//    store value in map
//    return value
public static int fibonacciMemo(int n,Map<Integer,Integer> map){

    if(n == 0 || n == 1){
        return n;
    }else if(map.containsKey(n)){
        return map.get(n);

    }else {

       map.put(n, fibonacciMemo(n-1,map) + fibonacciMemo(n-2,map));
        return map.get(n);
    }
}


// rainwater problem calculate total amount of water in the puddles
    //naive solution gives you O(n2)
public static int rainWater(int[] heights){

    int totalWater = 0;
    for(int i=0; i < heights.length ; i++){

        int highLeftBound = 0;
        int highRightBound = 0;
        for(int j = 0; j <= i ; j++){
            if(highLeftBound < heights[j]){
                highLeftBound = heights[j];
            }
        }

        for(int j = i; j < heights.length; j++){
            highRightBound = Math.max(highRightBound,heights[j]);
        }
        totalWater +=  Math.min(highLeftBound,highRightBound) - heights[i];
    }

    return totalWater;
}

//good solution using the two pointers technique gives you O(N);
//    totalWater = 0
//    leftPointer = 0
//    rightPointer = heights.length - 1
//    leftBound = 0
//    rightBound = 0
//    leftPointer and rightPointer will start at the beginning and end of the array, respectively, and move towards each other until they meet. The algorithm is as follows:
//
//            while leftPointer < rightPointer:
//            if the element at leftPointer <= the element at rightPointer:
//            -set leftBound to max value between leftPointer element and leftBound
//        -add difference between leftBound & the element at leftPointer to totalWater
//        -move leftPointer forward by one
//    else:
//            -set rightBound to max value between rightPointer element and rightBound
//        -add difference between rightBound & the element at rightPointer to totalWater
//        -move rightPointer back by one
//
//return totalWater

    public static int rainWaterFixed(int[] heights) {

        int totalWater = 0;
        int leftBound = 0, rightBound = 0, leftPointer = 0, rightPointer = heights.length - 1;

        while (leftPointer < rightPointer) {

            if (heights[leftPointer] <= heights[rightPointer]) {
                leftBound = Math.max(heights[leftPointer], leftBound);

                totalWater += leftBound - heights[leftPointer];
                leftPointer++;
            } else {
                rightBound = Math.max(heights[rightPointer], rightBound);
                totalWater += rightBound - heights[rightPointer];
                rightPointer--;
            }
        }
        return totalWater;

    }


    //program to determine the two numbers in array than sum an int target
    //assuming list is sorted in ascending order,use two pointer technique
    public static boolean sumOfTwo(int[] list,int target){

    int leftPointer = 0,rightPointer = list.length -1;

    while(leftPointer < rightPointer){
        if(list[leftPointer] + list[rightPointer] == target){
            return true;
        } else if(list[leftPointer] + list[rightPointer] < target){
            leftPointer++;
        }else {
            rightPointer--;
        }
    }
    return false;
        }
// same program as above but now with unsorted array, this has a O(N) time complexity
    //uses a hashset which is a collection of unique values to store elements in the array and compare them
    public static int[] sumOfTwoUnsorted(int[] list,int target){

    HashSet<Integer> set = new HashSet<>();


    for(int n : list){
        int difference = target - n;
        if(set.contains(difference)){
            return new int[]{n,difference};
        }
        set.add(n);
    }
        return null;
    }


    //given a list of numbers return whether the list contains Pythagorean Triplets .ex 3,4,5
 //O(N3) can be optimized using two pointer in outer loop , list needs to be sorted to O(N2)
    public static boolean pythagoreanTriplets(int[] list) {

        Arrays.sort(list);

        for(int i=0 ; i < list.length ;i++) {

            for(int j = i+1 ; j < list.length; j++) {

                for(int k = j+1; k < list.length ; k++) {

                    if(Math.pow(list[i],2) + Math.pow(list[j],2) == Math.pow(list[k],2)) {
                        return true;
                    }
                }
            }

        }


        return false;
    }

    public static String reverseString(String input) {

        String output = "";

        for (char character : input.toCharArray()) {
            output = character + output;
        }

        //or loop using for loop instead of for each below
        // for(int i = 0; i < input.length();i++){
        //       output = input.charAt(i) + output;
        // }
    System.out.println("hello");
    return output;
    }


//Sieve of Eratosthenes
//    Create an array of all integers from 2 to n
//    Set all elements of the array to true
//    Starting with 2, iterate through the array. If the current element is true, it is still considered prime. Since we know that all multiples of that number are NOT prime, iterate through all multiples of that number up to n and set them equal to false
//    Change the current element to the next non-false index.
//    Return the corresponding number value for any element still marked as prime (value of true).
//   algorithm finds all prime numbers from 1 to 100;
 // time complexity O(N log(logN))
public static void sieveOfEratosthenes(int limit) {
    boolean[] output = new boolean[limit + 1];
    for (int x = 0; x <= limit; x++) {
        output[x] = true;
    }
    output[0] = false;
    output[1] = false;

    for (int i = 2; i <= Math.pow(limit, 0.5); i++) {
        if (output[i]) {
            for (int j = (int) Math.pow(i, 2); j <= limit; j = j + i) {
                output[j] = false;
            }
        }
    }

    List<Integer> result = new ArrayList<Integer>();

    for (int i = 0; i < output.length; i++) {
        if (output[i]) {
            result.add(i);
        }
    }

    System.out.println(Arrays.toString(result.toArray()));
}

//    Write a Java program that prints out the numbers 1 to 50 but,
//            for multiples of three, print the word “Fizz” and for multiples of five,
//    print the word “Buzz.” For numbers that are multiples of both three and five,
//    print the word “FizzBuzz.”

    public static void fizzBuzz(int limit){

    for(int i = 1 ; i<= limit; i++){

        if(i%3 == 0 && i%5 == 0){
            System.out.println("FizzBuzz");
        } else if(i%3 == 0){
            System.out.println("Fizz");
        }else if(i%5 == 0){
            System.out.println("Buzz");
        } else System.out.println(i);



    }
    }


    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        System.out.println(fibonacciMemo(10, map));

        map = new HashMap<Integer, Integer>();
        System.out.println(fibonacciMemo(20, map));

       System.out.println( rainWaterFixed(new int[]{5, 6, 8, 3, 2, 6}));

       System.out.println(sumOfTwo(new int[]{3,4,5,8,9,15,21}, 21));
       int[] nums = (sumOfTwoUnsorted(new int[]{3,2,7,5,9,15,4}, 20));
       System.out.println("Number are "+ nums[0] + " and " + nums[1] + ".");

       System.out.println(reverseString("hello world"));

       sieveOfEratosthenes(100);

       fizzBuzz(20);
    }
}


