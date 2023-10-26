
//Graphs are an essential data structure in computer science for modeling networks. Let’s review some key terms:
//
//        vertex: A node in a graph.
//        edge: A connection between two vertices.
//        adjacent: When an edge exists between vertices.
//        path: A sequence of one or more edges between vertices.
//        disconnected: Graph where at least two vertices have no path connecting them.
//        weighted: Graph where edges have an associated cost.
//        directed: Graph where travel between vertices can be restricted to a single direction.
//        cycle: A path which begins and ends at the same vertex.
//        adjacency matrix: Graph representation where vertices are both the rows and the columns. Each cell represents a possible edge.
//        adjacency list: Graph representation where each vertex has a list of all the vertices it shares an edge with.
//
import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices;
    private boolean isWeighted;
    private boolean isDirected;

    public Graph(boolean inputIsWeighted, boolean inputIsDirected) {
        this.vertices = new ArrayList<Vertex>();
        this.isWeighted = inputIsWeighted;
        this.isDirected = inputIsDirected;
    }

    public Vertex addVertex(String data) {
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);

        return newVertex;
    }

    public void addEdge(Vertex vertex1, Vertex vertex2, Integer weight) {
        if (!this.isWeighted) {
            weight = null;
        }

        vertex1.addEdge(vertex2, weight);

        if (!this.isDirected) {
            vertex2.addEdge(vertex1, weight);
        }
    }

    public void removeEdge(Vertex vertex1, Vertex vertex2) {
        vertex1.removeEdge(vertex2);

        if (!this.isDirected) {
            vertex2.removeEdge(vertex1);
        }
    }

    public void removeVertex(Vertex vertex) {
        this.vertices.remove(vertex);
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    public boolean isWeighted() {
        return this.isWeighted;
    }

    public boolean isDirected() {
        return this.isDirected;
    }

    public Vertex getVertexByValue(String value) {
        for(Vertex v: this.vertices) {
            if (v.getData() == value) {
                return v;
            }
        }

        return null;
    }

    public void print() {
        for(Vertex v: this.vertices) {
            v.print(isWeighted);
        }
    }

    public static void main(String[] args) {

            Graph trainNetwork = new Graph(true,true);

            Vertex la = trainNetwork.addVertex("Los Angeles");
            Vertex sf =trainNetwork.addVertex("San Francisco");
            Vertex ny = trainNetwork.addVertex("New York");
            Vertex at = trainNetwork.addVertex("Atlanta");
            Vertex de = trainNetwork.addVertex("Denver");
            Vertex calg = trainNetwork.addVertex("Calgary");

            trainNetwork.addEdge(sf,la,400);
            trainNetwork.addEdge(la,sf,400);
            trainNetwork.addEdge(ny,de,1800);
            trainNetwork.addEdge(de,ny,1800);
            trainNetwork.addEdge(calg,de,1000);
            trainNetwork.addEdge(de,calg,1000);
            trainNetwork.addEdge(la,at,2100);
            trainNetwork.addEdge(at,la,2100);

            trainNetwork.removeEdge(calg,de);
            trainNetwork.removeEdge(de,calg);

            trainNetwork.removeEdge(ny,de);

            trainNetwork.removeVertex(calg);

            trainNetwork.print();
        }



    public class Vertex {
        private String data;
        private ArrayList<Edge> edges;

        public Vertex(String inputData) {
            this.data = inputData;
            this.edges = new ArrayList<Edge>();
        }

        public void addEdge(Vertex endVertex, Integer weight) {
            this.edges.add(new Edge(this, endVertex, weight));
        }

        public void removeEdge(Vertex endVertex) {
            this.edges.removeIf(edge -> edge.getEnd().equals(endVertex));
        }

        public String getData() {
            return this.data;
        }

        public ArrayList<Edge> getEdges(){
            return this.edges;
        }

        public void print(boolean showWeight) {
            String message = "";

            if (this.edges.size() == 0) {
                System.out.println(this.data + " -->");
                return;
            }

            for(int i = 0; i < this.edges.size(); i++) {
                if (i == 0) {
                    message += this.edges.get(i).getStart().data + " -->  ";
                }

                message += this.edges.get(i).getEnd().data;

                if (showWeight) {
                    message += " (" + this.edges.get(i).getWeight() + ")";
                }

                if (i != this.edges.size() - 1) {
                    message += ", ";
                }
            }
            System.out.println(message);
        }

        public static void main(String[] args) {

        }
    }

    public class Edge {
        private Vertex start;
        private Vertex end;
        private Integer weight;

        public Edge(Vertex startV, Vertex endV, Integer inputWeight) {
            this.start = startV;
            this.end = endV;
            this.weight = inputWeight;
        }

        public Vertex getStart() {
            return this.start;
        }

        public Vertex getEnd() {
            return this.end;
        }

        public Integer getWeight() {
            return this.weight;
        }

    }

}
