//A Binary Search Tree is an efficient value structure for fast (O(log N)) data storage and retrieval.
// It is a specialized tree data structure that is made up of a root node, and at most two child branches or subtrees.
//
//In this lesson’s implementation, each node will be an instance of the BinarySearchTree class.
// Each Binary Search Tree node has the following properties:
//
// a value
// a depth value, where a depth of 1 indicates the top level of the tree and a depth greater than 1 is a level somewhere lower in the tree
// a left pointer that points to a left child which is itself a Binary Search Tree, and must have a data lesser than its parent node’s data
// a right pointer that points to a right child which is itself a Binary Search Tree, and must have a data greater than or equal to its parent node’s data

public class BinarySearchTree {

    public int value;
    public int depth;
    public BinarySearchTree left;
    public BinarySearchTree right;

    public BinarySearchTree(int value, int depth) {
        this.value = value;
        this.depth = depth;
        this.left = null;
        this.right = null;
    }

    public BinarySearchTree(int value) {
        this.value = value;
        this.depth = 1;
        this.left = null;
        this.right = null;
    }

    public void insert(int value) {
        if (value < this.value) {
            if (this.left == null) {
                this.left = new BinarySearchTree(value, this.depth + 1);
                System.out.printf("Tree node %d added to the %s of %d at depth %d \n", value, "left", this.value, this.depth + 1);
            } else {
                this.left.insert(value);
            }
        } else {
            if (this.right == null) {
                this.right = new BinarySearchTree(value, this.depth + 1);
                System.out.printf("Tree node %d added to the %s of %d at depth %d \n", value, "right", this.value, this.depth + 1);
            } else {
                this.right.insert(value);
            }
        }
    }

    public BinarySearchTree getNodeByValue(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value && this.left != null) {
            return this.left.getNodeByValue(value);
        } else if (value > this.value && this.right != null) {
            return this.right.getNodeByValue(value);
        }
        return null;
    }


    //DFS using in order traversal for values in ascending order
    public void depthFirstTraversal() {
        if (this.left != null) {
            this.left.depthFirstTraversal();
        }
        System.out.println(this.value);
        if (this.right != null) {
            this.right.depthFirstTraversal();
        }
    }

    public static void main(String[] args) {
        System.out.println("Creating Binary Search Tree rooted at value 15:");
        BinarySearchTree tree = new BinarySearchTree(15);

        for (int i = 0; i < 10; i++) {
            tree.insert((int) (Math.random() * 100));
        }

        System.out.println("Printing the inorder depth-first traversal:");
        tree.depthFirstTraversal();
    }
}
