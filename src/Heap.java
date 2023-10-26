//Imagine you have a demanding boss (hopefully this is theoretical!).
// They always want the most important thing done. Of course, once you finish the most important task, another one takes its place.
//
//You can manage this problem using a priority queue to ensure you’re always working on the most pressing assignment and heaps are commonly used
// to create a priority queue.
//
//Heaps tracking the maximum or minimum value are max-heaps or min-heaps. We will focus on min-heaps, but the concepts for a max-heap are nearly identical.
//
//Think of the min-heap as a binary tree with two qualities:
//
//   The root is the minimum value of the dataset.
//   Every child’s value is greater than or equal to its parent.


//        Conceptually, the tree representation is beneficial for understanding.
//        Practically, we implement heaps in a sequential data structure like an array or list for efficiency.
//
//        Notice how by filling the tree from left to right; we’re leaving no gaps in the array
//        The location of each child or parent derives from a formula using the index.
//
//        left child: (index * 2) + 1
//        right child: (index * 2) + 2
//        parent: (index - 1) / 2 — not used on the root!
import java.util.ArrayList;
public class Heap {
    public ArrayList<Integer> heap;
    public int size;

    public Heap() {
        this.heap = new ArrayList<Integer>();
        this.heap.add(null);
        this.size = 0;
    }
    public int getParent(int current) {
        return (int) Math.floor(current / 2);
    }

    public int getLeft(int current) {
        return current * 2;
    }

    public int getRight(int current) {
        return (current * 2) + 1;
    }
    public void add(int value) {
        this.heap.add(value);
        this.size++;
        this.bubbleUp();
    }

    private void swap(int a, int b) {
        int temp = this.heap.get(b);
        this.heap.set(b, this.heap.get(a));
        this.heap.set(a, temp);
    }

    private boolean exists(int index) {
        return index <= this.size;
    }

    private boolean canSwap(int current, int leftChild, int rightChild) {
        return (this.exists(leftChild) && (this.heap.get(current) > this.heap.get(leftChild)))
                || (this.exists(rightChild) && (this.heap.get(current) > this.heap.get(rightChild)));
    }


    private void bubbleUp() {
        int current = this.size;
        while (current > 1 && this.heap.get(current) < this.heap.get(this.getParent(current))) {
            this.swap(current, this.getParent(current));
            current = this.getParent(current);
        }
    }
    public int popMin() {
        if (this.size == 0) {
            throw new Error("Heap is empty!");
        }
        this.swap(1, this.size);
        int min = this.heap.remove(this.size);
        this.size--;
        this.heapify();
        return min;
    }

    private void heapify() {
        int current = 1;
        int leftChild = this.getLeft(current);
        int rightChild = this.getRight(current);
        int swapCount = 0;
        while (this.canSwap(current, leftChild, rightChild)) {
            if (this.exists(leftChild) && this.exists(rightChild)) {
                if (this.heap.get(leftChild) < this.heap.get(rightChild)) {
                    this.swap(current, leftChild);
                    current = leftChild;
                    swapCount++;
                } else {
                    this.swap(current, rightChild);
                    current = rightChild;
                    swapCount++;
                }
            } else {
                this.swap(current,leftChild);
                current = leftChild;
                swapCount++;
            }
            leftChild = this.getLeft(current);
            rightChild = this.getRight(current);
        }

        if (this.size == 9999) {
            System.out.println("A heap of " + this.size + " elements was restored with only " + swapCount + " swaps!");
        }
    }

    public static void main(String[] args) {
        Heap minHeap = new Heap();

        // Populate minHeap with descending #s from 10001 to 1
        System.out.println("Adding 10000 individual elements...");
        for (int i = 10000; i >= 1; i--) {
            minHeap.add(i);
        }
        // Remove minimum value from minHeap
        System.out.println("Removing the minimum value...");
        System.out.println("Minimum value: " + minHeap.popMin());
    }
}
