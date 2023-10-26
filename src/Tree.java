//A binary tree is a type of tree where each parent can have no more than two
// children, known as the left child and right child.
//
//        Further constraints make a binary search tree:
//
//        Left child values must be lesser than their parent.
//        Right child values must be greater than their parent.
//        The constraints of a binary search tree allow us to search the tree efficiently.
//        At each node, we can discard half of the remaining possible values!

//  Terms  root: A node which has no parent. One per tree.
//        parent: A node which references other nodes.
//        child: Nodes referenced by other nodes.
//        sibling: Nodes which have the same parent.
//        leaf: Nodes which have no children.
//        level: The height or depth of the tree. Root nodes are at level 1, their children are at level 2, and so on.
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {

    public TreeNode root;

    public Tree(TreeNode root) {
        this.root = root;
    }

    public void print() {
        print(this.root, 0);
    }

    public void print(TreeNode current, int level) {
        String levelMarks = "";
        for (int i = 0; i < level; i++) {
            levelMarks += "-- ";
        }
        System.out.println(levelMarks + current.data);
        for (TreeNode child : current.children) {
            print(child, level + 1);
        }
    }

    public void depthFirstTraversal(TreeNode current) {
        System.out.print(current.data + " ");
        for (TreeNode child : current.children) {
            depthFirstTraversal(child);
        }
    }

//    Breadth-first-search (BFS) is a technique in a tree that visits all children
//    of a node first before visiting any further levels.
 //Pseudo for BFT
//    Create a queue
//    Initialize by adding the root node
//    While the queue is not empty
//    Set the first tree node from the queue as current
//    Print current tree node's data
//    Add all current tree node's children to the queue
    public void breadthFirstTraversal() {
        TreeNode current = this.root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(current);
        while (!queue.isEmpty()) {
            current = queue.poll();
            System.out.print(current.data + " ");
            queue.addAll(current.children);
        }
    }

    public static void main(String[] args) {
        TreeNode animals = new TreeNode("Animals");
        TreeNode reptile = new TreeNode("Reptiles");
        TreeNode mammals = new TreeNode("Mammals");
        animals.addChild(reptile);
        animals.addChild(mammals);
        reptile.addChild("Lizard");
        reptile.addChild("Snake");
        TreeNode equine = new TreeNode("Equine");
        TreeNode bovine = new TreeNode("Bovine");
        TreeNode marsupial = new TreeNode("Marsupial");
        mammals.addChild(equine);
        mammals.addChild(bovine);
        mammals.addChild(marsupial);
        equine.addChild("Horse");
        equine.addChild("Zebra");
        bovine.addChild("Husky");
        marsupial.addChild("Koala");

        Tree animalTree = new Tree(animals);

        // Add and remove accordingly
        bovine.removeChild("Husky");
        bovine.addChild("Cow");
        marsupial.addChild("Kangaroo");

        animalTree.print();

        System.out.println("Printing DFS Traversal:");
        // Depth-first traversal
        animalTree.depthFirstTraversal(animals);

        System.out.println("");
        System.out.println("Printing BFS Traversal:");
        // Breadth-first traversal
        animalTree.breadthFirstTraversal();
    }

    public static class TreeNode {

        public Object data;
        public ArrayList<TreeNode> children;

        public TreeNode(Object data) {
            this.data = data;
            this.children = new ArrayList<TreeNode>();
        }

        public void addChild(TreeNode child) {
            this.children.add(child);
        }

        public void addChild(Object childData) {
            TreeNode child = new TreeNode(childData);
            this.children.add(child);
        }

        public void removeChild(TreeNode childToRemove) {
            if (this.children.isEmpty()) {
                return;
            } else if (this.children.contains(childToRemove)) {
                this.children.remove(childToRemove);
                return;
            } else {
                for (TreeNode child : this.children) {
                    child.removeChild(childToRemove);
                }
            }
        }

        public void removeChild(Object data) {
            if (this.children.isEmpty()) {
                return;
            }
            for (TreeNode child : this.children) {
                if (child.data == data) {
                    removeChild(child);
                    return;
                }
            }
            for (TreeNode child : this.children) {
                child.removeChild(data);
            }
        }
    }
}

