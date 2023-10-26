
//Hash map: non linear data structure very efficient for storing and accessing info.
// A key-value store that uses an array and a hashing function to save and retrieve values.
//        Key: The identifier given to a value for later retrieval.
//        Hash function: A function that takes some input and returns a number.
//        Compression function: A function that transforms its inputs into some smaller range of possible outputs.
//
//        Recipe for saving to a hash table:
//        - Take the key and plug it into the hash function, getting the hash code.
//        - Modulo that hash code by the length of the underlying array, getting an array index.
//        - Check if the array at that index is empty, if so, save the value (and the key) there.
//        - If the array is full at that index continue to the next possible position depending on your collision strategy.
//
//        Recipe for retrieving from a hash table:
//        - Take the key and plug it into the hash function, getting the hash code.
//        - Modulo that hash code by the length of the underlying array, getting an array index.
//        - Check if the array at that index has contents, if so, check the key saved there.
//        - If the key matches the one you're looking for, return the value.
//        - If the keys don't match, continue to the next position depending on your collision strategy.

//A hash map is:
//
//        Built on top of an array using a special indexing system.
//        A key-value storage with fast assignments and lookup.
//        A table that represents a map from a set of keys to a set of values.
//        Hash maps accomplish all this by using a hash function, which turns a key into an index into the underlying array.
//
//        A hash collision is when a hash function returns the same index for two different keys.
//
//        There are different hash collision strategies. Two important ones are separate chaining, where each array index points to a different data structure,
//        and open addressing, where a collision triggers a probing sequence to find where to store the value for a given key.
//public class HashMap {
//
//    public String[] hashmap;
//
//    public HashMap(int size) {
//        this.hashmap = new String[size];
//    }
//
//    public int hash(String key) {
//        int hashCode = 0;
//        for (int i = 0; i < key.length(); i++) {
//            hashCode = hashCode + Character.codePointAt(key, i);
//        }
//        hashCode = hashCode % this.hashmap.length;
//        return hashCode;
//    }
//
//    public void assign(String key, String value) {
//        int arrayIndex = this.hash(key);
//        this.hashmap[arrayIndex] = value;
//    }
//
//    public String retrieve(String key) {
//        int arrayIndex = this.hash(key);
//        return this.hashmap[arrayIndex];
//    }
//
//    public static void main(String[] args) {
//        HashMap parkInventory = new HashMap(2);
//        parkInventory.assign("reed", "marsh plant");
//        parkInventory.assign("deer", "forest animal");
//
//        System.out.println(parkInventory.retrieve("reed"));
//        System.out.println(parkInventory.retrieve("deer"));
//    }
//}

//HashMap avoiding collision while using separate chaining;


public class HashMap {

    public LinkedList[] hashmap;

    public HashMap(int size) {
        this.hashmap = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.hashmap[i] = new LinkedList();
        }
    }

    public int hash(String key) {
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            hashCode = hashCode + Character.codePointAt(key, i);
        }
        hashCode = hashCode % this.hashmap.length;
        return hashCode;
    }

    public void assign(String key, String value) {
        int arrayIndex = this.hash(key);
        LinkedList list = this.hashmap[arrayIndex];
        if (list.head == null) {
            list.addToHead(key, value);
            return;
        }
        LinkedList.Node current = list.head;
        while (current != null) {
            if (current.key == key) {
                current.setKeyValue(key, value);
            }
            if (current.getNextNode() == null) {
                current.setNextNode(new LinkedList.Node(key, value));
                break;
            }
            current = current.getNextNode();
        }
    }

    public String retrieve(String key) {
        int arrayIndex = this.hash(key);
        LinkedList.Node current = this.hashmap[arrayIndex].head;
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.getNextNode();
        }
        return null;
    }


    public class LinkedList {
        public Node head;

        public LinkedList() {
            this.head = null;
        }

        public void addToHead(String key, String value) {
            Node newHead = new Node(key, value);
            Node currentHead = this.head;
            this.head = newHead;
            if (currentHead != null) {
                this.head.setNextNode(currentHead);
            }
        }

        public void addToTail(String key, String value) {
            Node tail = this.head;
            if (tail == null) {
                this.head = new Node(key, value);
            } else {
                while (tail.getNextNode() != null) {
                    tail = tail.getNextNode();
                }
                tail.setNextNode(new Node(key, value));
            }
        }

        public void removeHead() {
            Node removedHead = this.head;
            if (removedHead == null) {
                return;
            }
            this.head = removedHead.getNextNode();
        }

        public static class Node {
            public String key;
            public String value;
            private Node next;

            public Node(String key, String value) {
                this.key = key;
                this.value = value;
                this.next = null;
            }

            public void setNextNode(Node node) {
                this.next = node;
            }

            public Node getNextNode() {
                return this.next;
            }

            public void setKeyValue(String key, String value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    public static void main(String[] args) {

    }

}