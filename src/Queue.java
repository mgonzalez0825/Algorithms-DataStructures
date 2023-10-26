
//Queues can be implemented using linked list or arrays
//Use FIFO protocol
//used for Breadth First Search algorithm
//.enqueue(): add a new element to the tail
//.dequeue(): remove the element at the head and return its value
//.peek(): view the value of the head without removing it
// helper methods hasSpace(), isEmpty();
public class Queue {

    public DoublyLinkedList queue;
    public int size;
    static final int DEFAULT_MAX_SIZE = Integer.MAX_VALUE;
    public int maxSize;

    //constructor for unbounded queue
    public Queue() {
        this(DEFAULT_MAX_SIZE);
    }

    //constructor for bounded queue
    public Queue(int maxSize) {
        this.queue = new DoublyLinkedList();
        this.size = 0;
        this.maxSize = maxSize;
    }

    public boolean hasSpace() {
        return this.size < this.maxSize;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void enqueue(String data) {
        if (this.hasSpace()) {
            this.queue.addToTail(data);
            this.size++;
        } else {
            throw new Error("Queue is full!");
        }
    }

    public String dequeue() {
        if (!this.isEmpty()) {
            String data = this.queue.removeHead();
            this.size--;
            return data;
        } else {
            throw new Error("Queue is empty!");
        }
    }

    public String peek() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.queue.head.data;
        }
    }

    public class DoublyLinkedList {

        Node head;
        Node tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void addToHead(String data) {
            Node newHead = new Node(data);
            Node currentHead = this.head;
            this.head = newHead;
            if (currentHead != null) {
                this.head.setNextNode(currentHead);
            }
        }

        public void addToTail(String data) {
            Node tail = this.head;
            if (tail == null) {
                this.head = new Node(data);
            } else {
                while (tail.getNextNode() != null) {
                    tail = tail.getNextNode();
                }
                tail.setNextNode(new Node(data));
            }
        }

        public String removeHead() {
            Node removedHead = this.head;
            if (removedHead == null) {
                return null;
            }
            this.head = removedHead.getNextNode();
            return removedHead.data;
        }

        public String printList() {
            Node currentNode = this.head;
            String output = "<head> ";
            while (currentNode != null) {
                output += currentNode.data + " ";
                currentNode = currentNode.getNextNode();
            }
            output += "<tail>";
            return output;
        }


        public class Node {
            String data;
            private DoublyLinkedList.Node next;
            private DoublyLinkedList.Node previous;

            public Node(String data) {
                this.data = data;
                this.next = null;
                this.previous = null;
            }

            public void setNextNode(DoublyLinkedList.Node next) {
                this.next = next;
            }

            public void setPreviousNode(DoublyLinkedList.Node previous) {
                this.previous = previous;
            }

            public DoublyLinkedList.Node getNextNode() {
                return this.next;
            }

            public DoublyLinkedList.Node getPreviousNode() {
                return this.previous;
            }
        }
    }


    public static class RestaurantOrders {

        public Queue headChef;
        public Queue sousChef;
        public Queue waitingList;

        public RestaurantOrders() {
            // Instantiate queues here
            this.headChef = new Queue(3);
            this.sousChef = new Queue(3);
            this.waitingList = new Queue();
        }

        public void assign(String[] orders) {
            for (String order : orders) {
                try {
                    // Assign orders to Head Chef
                    this.headChef.enqueue(order);
                    System.out.println("Order for " + order + " taken by Head Chef.");

                } catch (Error e) {
                    // Assign orders to Sous Chef
                    if(this.sousChef.hasSpace()){
                        this.sousChef.enqueue(order);
                        System.out.println("Head Chef is busy! Assign " + order + " order to Sous Chef.");
                    }else {
                        this.waitingList.enqueue(order);
                        System.out.println("Both chefs are busy! Add " + order + " order to the waiting list.");
                    }
                }

            }
            System.out.println("-----------------");
            System.out.println("You've got only " + this.waitingList.size + " orders on the waiting list. Keep up the hard work chefs!");
        }
        public static void main(String[]args) {}
    }
    public static void main(String[] args){

        String [] orders = {"green curry","pad thai","gyoza","cucumber salad","pad see ew", "brown rice","red curry","salad rolls"};

        RestaurantOrders foodOrders = new RestaurantOrders();

        foodOrders.assign(orders);

            }
        }




