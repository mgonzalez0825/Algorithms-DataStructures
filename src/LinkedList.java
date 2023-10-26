public class LinkedList{

    Node head;

    //default constructor for linkedList class
    public LinkedList(){
        this.head = null;
    }

    //add to head method
    public void addToHead(String data){

        //create new node
        Node newHead = new Node(data);

        //save current head in Node currentHead.
        Node currentHead = this.head;

       // Then assign new node as head
        this.head = newHead;

        //if list has more than one element set pointer of head.next to the old head (currentHead)
        if (currentHead != null){
           this.head.setNextNode(currentHead);
        }
    }



    //method to add node at the end of the list
    public void addToTail(String data){

        Node tail = this.head; // set tail Node to head

        //if list is empty set head to new node passed
        if(tail == null){
            this.head = new Node(data);
        }else{
            while(tail.getNextNode() != null){      // iterate through list until you reach the end
                tail = tail.getNextNode();
            }

            tail.setNextNode(new Node(data)); // set last node pointer to new node to add to the tail
        }
    }


    //Method to print a list of nodes
    public String printList(){

        String output = "<head> ";
        Node currNode = this.head;

        //iterate through list until reach the end
        while(currNode != null){
            output += currNode.data + " ";
            currNode = currNode.getNextNode();
        }

        output += " <tail>";
        System.out.println(output);
        return output;

    }



    //method to remove head of linked list
    public String removeHead(){

        Node removedHead = this.head;  // set new node removedhead to head

        if(removedHead == null){    // if list is empty return null
            return null;
        }

        this.head = removedHead.getNextNode(); //else set list head to next node
        return removedHead.data;  // return removed head data
    }

    //method to remove tail
    public String removeTail(){

        Node tail = this.head;

        if (tail == null) {
            return null;
        } else{
            while(tail.getNextNode().getNextNode() !=null){
                tail = tail.getNextNode();
            }

            String removedTail = tail.getNextNode().data;
            tail.setNextNode(null);
            return removedTail;

        }
    }

   // done by me
    public String removeByData(String data) {

        Node currentNode = this.head;
        Node prev = null;


        while(currentNode != null) {

            if(currentNode.data == data) {

                if(prev == null) {
                    this.head = currentNode.getNextNode();
                }else {
                    prev.setNextNode(currentNode.getNextNode());
                }

                return currentNode.data;
            }


            prev = currentNode;
            currentNode = currentNode.getNextNode();
        }

        return null;
    }



    public class Node{

        public String data;
        private Node next;

        //default constructor for Node class
        public Node(String data){
            this.data = data;
            this.next = null;
        }

        public void setNextNode(Node next){
            this.next = next;
        }

        public Node getNextNode(){
            return this.next;
        }
    }




    //common interview question time complexity O(n)
    //space complexity O(1) uses only 4 variables , node1,node2, node1prev,node2Prev;
//    Given an input of a linked list, data1, and data2, the general steps for swapping the position of nodes with data1 and data2 in the linked list is as follows:
//
//   1. Iterate through the list looking for the node that matches data1 to be swapped (node1), keeping track of the node’s previous node as you iterate (node1Prev)
//   2. Repeat step 1 looking for the node that matches data2 (giving you node2 and node2Prev)
//   3. If node1Prev is null, node1 was the head of the list, so set the list’s head to node2
//   4.  Otherwise, set node1Prev‘s next node to node2
//   5. If node2Prev is null, set the list’s head to node1
//   6. Otherwise, set node2Prev‘s next node to node1
//   7.  Set node1‘s next node to node2‘s next node
//   8.  Set node2‘s next node to what was node1‘s next node
    public void swapNodes(LinkedList list,String data1, String data2){

        Node node1 = list.head;
        Node node2 = list.head;
        Node node1Prev = null;
        Node node2prev = null;

        if(data1 == data2){
            System.out.println("Elements are the same value cannot be swapped");
            return;
    }

        while(node1 != null){
            if(node1.data == data1){
                break;
            }

            node1Prev = node1;
            node1 = node1.getNextNode();
        }

        while(node2 != null){
            if(node2.data == data2){
                break;
            }

            node2prev = node2;
            node2 = node2.getNextNode();
        }


        if(node1==null || node2 == null){
            System.out.println(" one or both elements are not on the list ,swap not possible");
        }

        if(node1Prev == null){
            list.head = node2;
        }else{
            node1Prev.setNextNode(node2);
        }

        if(node2prev == null){
            list.head = node1;
        }else{
            node2prev.setNextNode(node1);
        }

        Node temp = node1.getNextNode();
        node1.setNextNode(node2.getNextNode());
        node2.setNextNode(temp);

    }



    //Method to reverse a linked list
    public Node reverseList(){

        Node prev = null; // create prev and next pointers assign them to null
        Node next;
        Node currentNode = this.head;  // create currentNode and assign it to head

        while(currentNode != null){  //traverse the list

            next = currentNode.next;    //assign next to currentNode.next
            currentNode.next = prev;    // update pointer prev currentNode.next now points backwards to prev
            prev = currentNode;         // then update prev to current and current to next
            currentNode = next;         //
        }

        this.head = prev;   // assign prev last node as head
        return prev;        //return head

    }


    //two nodes moving in parallel
    // nthLastNodePointer = null
    //tailSeeker = linked list head
    //count = 0
    //
    //while tailSeeker pointer exists
    //  move tailSeeker pointer forward
    //  if count >= n
    //    set nthLastNodePointer to head if it's still null or move it forward
    //  increment count
    //
    //return nthLastNodePointer
    //see below code to find nth last node in java
    public static Node nthLastNode(LinkedList list, int n) {
        Node nthLastNode = null;
        Node tailSeeker = list.head;
        int count = 0;
        while (tailSeeker != null) {
            tailSeeker = tailSeeker.getNextNode();
            if (count >= n) {
                if (nthLastNode == null) {
                    nthLastNode = list.head;
                }
                nthLastNode = nthLastNode.getNextNode();
            }
            count++;
        }
        return nthLastNode;
    }


    // finds the middle node in a list by using the Floyd's Tortoise and Hare algorithm. two pointers one moves twice as fast as the the other one
//    fastPointer = list head
//    slowPointer = list head
//while fastPointer is not null
//    move fastPointer forward
//  if the end of the list has not been reached
//    move fastPointer forward again
//    move slowPointer forward
//return slowPointer
    public static Node findMiddle(LinkedList linkedList) {
        Node fast = linkedList.head;
        Node slow = linkedList.head;

        while (fast != null) {
            fast = fast.getNextNode();
            if (fast != null) {
                fast = fast.getNextNode();
                slow = slow.getNextNode();
            }
        }
        return slow;
    }



    public Node findNodeIteratively(String data) {
        Node currentNode = this.head;

        while (currentNode != null) {
            if (currentNode.data == data) {
                return currentNode;
            }
            currentNode = currentNode.getNextNode();
        }

        return null;
    }


//    Base case 1 – return null if the end of the linked list is reached.
//    Base case 2 – return the current node if its data value matches the data argument.
//    Recursive Case – return a call to findNodeRecursively() with the next node as
//    an argument.
    public Node findNodeRecursively(String data, Node currentNode) {
        if (currentNode == null) {
            return null;
        } else if (currentNode.data == data) {
            return currentNode;
        } else {
            return this.findNodeRecursively(data, currentNode.getNextNode());
        }
    }


//    public int findMax(LinkedList list) {
//        Node current = list.head;
//        int max = current.data;
//        while (current.getNextNode() != null) {
//            current = current.getNextNode();
//            int val = current.data;
//            if (val > max) {
//                max = val;
//            }
//        }
//        return max;
//    }


    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addToHead("Hello");
        list.addToTail("Gutten");

        list.printList();
        list.removeHead();
        list.printList();
        list.addToTail("summer");
        list.addToTail("winter");
        list.addToTail("fall");
        list.printList();



        Node head = list.reverseList();
        list.printList();
        System.out.println(head.data);
        list.swapNodes(list,"fall","Gutten");
        list.printList();
    }
}
