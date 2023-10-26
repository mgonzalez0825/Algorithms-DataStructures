public class DoublyLinkedList {

    Node head;
    Node tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }


    //method to add node to head of doubly linked list
    public void addToHead(String data) {

        Node newHead = new Node(data); // create new node with data passed as argument
        Node currentHead = this.head;  // create currentHead variable to store actual head assign to head


        if (currentHead != null)  // if list is not empty
        {
            currentHead.setPreviousNode(newHead); //update pointers
            newHead.setNextNode(currentHead);
        }
        this.head = newHead;   // assign newHead as the new head of the list

        if (this.tail == null) {  // if tail is null means there is only one element therefore newHead is also the tail
            this.tail = newHead;
        }

    }

    //method to addTail to list
    public void addToTail(String data) {

        Node newTail = new Node(data);  //create newTail passing data as argument for the constructor
        Node currentTail = this.tail;  // create variable current tail and set it to tail;

        if (currentTail != null) {   // if list is not empty
            currentTail.setNextNode(newTail);  // set currentTail.next pointer to newTail
            newTail.setPreviousNode(currentTail); // set newTail previous pointer to currentTail
        }

        this.tail = newTail; //set new tail as tail

        if (this.head == null) {  //if list is empty head is null then assign newTail as the head also
            this.head = newTail;
        }
    }


    //method to removeHead of the list
    public String removeHead() {

        Node headToRemove = this.head;

        if (headToRemove == null) {
            return null;
        }

        this.head = headToRemove.getNextNode();

        if (this.head != null) {
            this.head.setPreviousNode(null);
        }

        if (headToRemove == this.tail) {
            this.removeTail();
        }

        return headToRemove.data;

    }

    //method to remove from tail
    public String removeTail() {

        Node tailToRemove = this.tail;

        if (tailToRemove == null) {
            return null;
        }

        this.tail = tailToRemove.getPreviousNode();

        if (this.tail != null) {
            this.tail.setNextNode(null);
        }

        if (tailToRemove == this.head) {
            this.removeHead();
        }
        return tailToRemove.data;
    }


    //method to remove any node
    public Node removeByData(String data) {

        Node nodeRemoved = null;
        Node currentNode = this.head;

        while (currentNode != null) {


            if (currentNode.data == data) {
                nodeRemoved = currentNode;
                break;
            }

            Node prev = currentNode;
            currentNode = currentNode.getNextNode();
        }

        if (nodeRemoved == null) {
            return null;
        }

        if (nodeRemoved == this.head) {
            this.removeHead();
        } else if (nodeRemoved == this.tail) {
            this.removeTail();
        } else {
            Node previousNode = nodeRemoved.getPreviousNode();
            Node nextNode = nodeRemoved.getNextNode();

            previousNode.setNextNode(nextNode);
            nextNode.setPreviousNode(previousNode);
        }

        return nodeRemoved;


    }

    public String printList() {

        Node currentNode = this.head;

        String output = "<head>";
        while (currentNode != null) {
            output += currentNode.data + " ";
            currentNode = currentNode.getNextNode();
        }
        output += " <tail> ";
        System.out.println(output);
        return output;
    }


    public class Node {
        String data;
        private Node next;
        private Node previous;

        public Node(String data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

            public void setNextNode (Node next){
                this.next = next;
            }

            public void setPreviousNode (Node previous){
                this.previous = previous;
            }

            public Node getNextNode () {
                return this.next;
            }

            public Node getPreviousNode () {
                return this.previous;
            }
        }


    public static void main (String[] args){

        DoublyLinkedList planeItinerary = new DoublyLinkedList();

        planeItinerary.addToHead("Miami");
        planeItinerary.addToHead("California");
        planeItinerary.addToHead("Las Vegas");
        planeItinerary.addToTail("Madrid");
        planeItinerary.printList();

        planeItinerary.removeTail();
        planeItinerary.removeByData("Las Vegas");
        planeItinerary.printList();



    }
}
