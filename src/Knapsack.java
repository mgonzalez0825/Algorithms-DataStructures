
//The knapsack problem is suited for dynamic programming because memoization
//        will allow us to store information instead of making duplicate calls.
//        We will store this information in a two-dimensional array that
//        has a row for every item and weightCap + 1 number of columns where each element
//        in the 2D array (matrix) represents a sub-problem. The element at the bottom right
//        will be the optimal solution.
// dynamic programming version of knapsack has a O runtime of O(index * weight) compared to the recursive implementation’s runtime of O(2^n).
 public class Knapsack {

    static int knapSack(int weightCap, int weights[], int values[], int i)
    {
        int index, weight;
        int matrix[][] = new int[i + 1][weightCap + 1];

        for (index = 0; index <= i; index++) {
            for (weight = 0; weight <= weightCap; weight++) {
                if (index == 0 || weight == 0)
                    matrix[index][weight] = 0;
                else if (weights[index - 1] <= weight)
                    matrix[index][weight] = Math.max(
                            values[index - 1] + matrix[index - 1][weight - weights[index - 1]],
                            matrix[index - 1][weight]);
                else {
                    matrix[index][weight] = matrix[index - 1][weight];
                }
            }
        }
        return matrix[i][weightCap];
    }


    public static void main(String[] args) {
        int values[] = new int[] { 70, 20, 39, 37, 7, 5, 10 };
        int weights[] = new int[] { 31, 10, 20, 19, 4, 3, 6 };
        int weightCap = 50;
        int i = values.length;
        Knapsack myKnapsack = new Knapsack();
        System.out.println(myKnapsack.knapSack(weightCap, weights, values, i));
    }
}