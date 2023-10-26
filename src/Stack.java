

   // Stacks:
//    Contain data nodes
//    Support three main operations
//    Push adds data to the top of the stack
//    Pop removes and provides data from the top of the stack
//    Peek reveals data on the top of the stack
//    Implementations include a linked list or array
//    Can have a limited size
//    Pushing data onto a full stack results in a stack overflow
//    Stacks process data Last In, First Out (LIFO)

    public class Stack {

        public DoublyLinkedList stack;
        public int size;
        static final int DEFAULT_MAX_SIZE = Integer.MAX_VALUE;
        public int maxSize;

        //constructor for unbounded queue
        public Stack() {
            this(DEFAULT_MAX_SIZE);
        }

        //constructor for bounded queue
        public Stack(int maxSize) {
            this.stack = new DoublyLinkedList();
            this.size = 0;
            this.maxSize = maxSize;
        }

        public boolean hasSpace() {
            return this.size < this.maxSize;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public void push(String data) {
            if (this.hasSpace()) {
                this.stack.addToHead(data);
                this.size++;
            } else {
                throw new Error("Stack is full!");
            }
        }

        public String pop() {
            if (!this.isEmpty()) {
                String data = this.stack.removeHead();
                this.size--;
                return data;
            } else {
                throw new Error("Stack is empty!");
            }
        }

        public String peek() {
            if (this.isEmpty()) {
                return null;
            } else {
                return this.stack.head.data;
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


        public static class PizzaDelivery {

            public Stack deliveryGal;
            public Stack pizzaHouse;

            public PizzaDelivery() {
                // 1. Instantiate deliveryGal and pizzaHouse stacks below
                this.deliveryGal = new Stack(5);
                this.pizzaHouse = new Stack();

            }

            public void assign(String [] pizzas) {
                for (String pizza : pizzas) {
                    try {
                        // 2. Push pizzas onto deliveryGal and print the update
                        this.deliveryGal.push(pizza);
                        System.out.println(pizza + " pizza added to deliveryGal stack.");

                    } catch (Error e) {
                        // 3. Push pizzas onto pizzaHouse and print the update
                        this.pizzaHouse.push(pizza);
                        System.out.println("deliveryGal left to make deliveries! " + pizza + " pizza added to pizzaHouse stack.");
                    }
                }

                System.out.println("\nDELIVERIES ARE UNDERWAY...\n");

            }

            public void deliver() {
                int numPizzas = this.deliveryGal.size;
                for (int i = 0; i < numPizzas; i++) {
                    // 4. Pop off each pizza from deliveryGal and print the update
                    String pizzaType = this.deliveryGal.pop();
                    System.out.println( pizzaType + " pizza delivered!");
                }
            }


        }

        public static void main(String[]args) {
            String [] pizzas = {"pepperoni","cheese","veggie","meat","hawaiian", "margherita"};
            PizzaDelivery pizzaDelivery = new PizzaDelivery();
            pizzaDelivery.assign(pizzas);
            pizzaDelivery.deliver();
        }
        }





