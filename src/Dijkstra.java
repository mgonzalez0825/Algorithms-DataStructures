
//Dijkstra’s Algorithm works as following:
//
//        Instantiate a dictionary that will eventually map vertices to their distance from the start vertex
//        Assign the start vertex a distance of 0 in a min heap
//        Assign every other vertex a distance of infinity in a min heap
//        Remove the vertex with the smallest distance from the min heap and set that to the current vertex
//        For the current vertex, consider all of its adjacent vertices and calculate the distance to them as (distance to the current vertex) + (edge weight of current vertex to adjacent vertex).
//        If this new distance is less than the current distance, replace the current distance.
//        Repeat 4 and 5 until the heap is empty
//        After the heap is empty, return the distances

//In the worst case, we would update the min-heap every iteration. Since there are at most E + V iterations of Dijkstra’s
//        and it takes log V to update a min-heap in the worst case, then the runtime of Dijkstra’s is O((E+V)log V).

//Dijkstra’s algorithm is an algorithm to find all of the shortest distances between a start vertex and the rest of the vertices in a graph.
//        The algorithm works by keeping track of all the distances and updating the distances as it conducts a breadth-first search.
//        Dijkstra’s algorithm runs in O((E+V)log V).

//PseudoCode
//We first need to initialize the two objects that we would use to keep track of the shortest paths from the starting vertex to every vertex in the graph. The previous object keeps track of the preceding vertices in the path, like a reverse linked-list. We can use it to reconstruct the entire path, but backwards. The distances object keeps track of how far each vertex is from the starting vertex.
//
//        Before we can start traversing through the edges in the graph, we must initialize each vertex’s distance and previous vertex. When the dictionaries are initialized, we have not traversed down any paths to any of the vertices, so the initial distances should all be Infinity (and any actual paths are guaranteed to be less than the initial distance). When initializing the previous dictionary, previous vertices are all null. The only exception is the starting vertex where the distance from the starting vertex to itself is 0.
//
//        distances = {}
//        previous = {}
//
//        for every vertex in the graph:
//        distances[vertex] = Infinity
//        previous[vertex] = null
//
//        distances[starting vertex] = 0

//while there are vertices in the queue:
//        dequeue vertex from queue
//
//        for every neighbor in vertex:
//        alternate = distances[vertex] + distance from vertex to neighbor
//if alternate < distances[neighbor]:
//        distances[neighbor] = alternate
//        previous[neighbor] = vertex
//        add neighbor to queue

//import java.util.*;
//
//public class Dijkstra {
//
//    public static Dictionary[] dijkstra (Graph g, Vertex startingVertex){
//        Dictionary<String, Integer> distances = new Hashtable<>();
//        Dictionary<String, Vertex> previous = new Hashtable<>();
//        PriorityQueue<QueueObject> queue = new PriorityQueue<QueueObject>();
//
//        queue.add(new QueueObject(startingVertex, 0));
//
//        for (Vertex v: g.getVertices()) {
//            if(v != startingVertex){
//                distances.put(v.getData(), Integer.MAX_VALUE);
//            }
//            previous.put(v.getData(), new Vertex("Null"));
//        }
//
//        distances.put(startingVertex.getData(), 0);
//
//
//        while(queue.size() != 0){
//            Vertex current = queue.poll().vertex;
//            for (Edge e: current.getEdges()) {
//                Integer alternate = distances.get(current.getData()) + e.getWeight();
//                String neighborValue = e.getEnd().getData();
//                if (alternate < distances.get(neighborValue)){
//                    distances.put(neighborValue, alternate);
//                    previous.put(neighborValue, current);
//                    queue.add(new QueueObject(e.getEnd(), distances.get(neighborValue)));
//                }
//            }
//        }
//
//        return new Dictionary[]{distances, previous};
//    }
//
//    public static void shortestPathBetween(Graph g, Vertex startingVertex, Vertex targetVertex){
//        Dictionary[] dijkstraDicts = dijkstra(g, startingVertex);
//        Dictionary distances = dijkstraDicts[0];
//        Dictionary previous = dijkstraDicts[1];
//        Integer distance = (Integer) distances.get(targetVertex.getData());
//        System.out.println("Shortest Distance between " + startingVertex.getData() + " and " + targetVertex.getData());
//        System.out.println(distance);
//
//        ArrayList<Vertex> path = new ArrayList<>();
//        Vertex v = targetVertex;
//        while(v.getData() != "Null"){
//            path.add(0,v);
//            v = (Vertex) previous.get(v.getData());
//        }
//        System.out.println("Shortest Path");
//        for (Vertex pathVertex: path){
//            System.out.println(pathVertex.getData());
//        }
//    }
//
//    public static void dijkstraResultPrinter(Dictionary[] d){
//        System.out.println("Distances:\n");
//        for (Enumeration keys = d[0].keys(); keys.hasMoreElements();){
//            String nextKey = keys.nextElement().toString();
//            System.out.println(nextKey + ": " + d[0].get(nextKey));
//        }
//        System.out.println("\nPrevious:\n");
//        for (Enumeration keys = d[1].keys(); keys.hasMoreElements();) {
//            String nextKey = keys.nextElement().toString();
//            Vertex nextVertex = (Vertex) d[1].get(nextKey);
//            System.out.println(nextKey + ": " + nextVertex.getData());
//        }
//    }
//
//    public static void main(String[] args){
//        Graph testGraph = new Graph(true, true);
//        Vertex a = testGraph.addVertex("A");
//        Vertex b = testGraph.addVertex("B");
//        Vertex c = testGraph.addVertex("C");
//        Vertex d = testGraph.addVertex("D");
//        Vertex e = testGraph.addVertex("E");
//        Vertex f = testGraph.addVertex("F");
//        Vertex g = testGraph.addVertex("G");
//
//        testGraph.addEdge(a, c, 100);
//        testGraph.addEdge(a, b, 3);
//        testGraph.addEdge(a, d, 4);
//        testGraph.addEdge(d, c, 3);
//        testGraph.addEdge(d, e, 8);
//        testGraph.addEdge(e, b, -2);
//        testGraph.addEdge(e, f, 10);
//        testGraph.addEdge(b, g, 9);
//        testGraph.addEdge(e, g, -50);
//
//        dijkstraResultPrinter(dijkstra(testGraph, a));
//        shortestPathBetween(testGraph, a, g);
//    }
//}
