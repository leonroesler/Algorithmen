import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Dijkstra Algorithm.
 */
public class Dijkstra {

    private Graph graph;
    private Vertex vertex;
    private Edge<Vertex> edge;
    private PriorityQueue priorityQueue;
    private double MAXDDOUBLE = Double.POSITIVE_INFINITY;

    //    private public Dijkstra(Graph graph, Vertex vertex, Edge<Vertex> edge) {
//        this.graph = graph;
//        this.vertex = vertex;
//        this.edge = edge;
//    }
    public Dijkstra(Graph graph, double distanceFromUandV, Edge edge) {
//        initializeSingleSource
        this.graph = graph;
        // u(vector) - v(vector?)
        this.edge = edge;
//         distanceFromUandV= Double.POSITIVE_INFINITY;

        priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(graph.getVertices());
    }

    //HIER SIND WIR
    private PriorityQueue initializeSingleSource(Graph graph1, Vertex startpoint) {
        startpoint = null;


        PriorityQueue newPriorityQueue = new PriorityQueue<Vertex>();
        newPriorityQueue.addAll(graph1.getVertices());

        Collection allVertices = graph1.getVertices();
        Collection allEdges = graph1.getEdges();

        graph1 = new Graph(allVertices, allEdges);
        Vertex vertexTemp;


        int index = 0;

        for (Iterator it = graph1.getVertices().iterator(); it.hasNext(); ) {
            Vertex vertex = graph1.getVertex(index);
            if (vertex != startpoint) {
                Collection neighboursCollection = graph1.getNeighbours(vertex);


//                distance[vertex]=MAXDDOUBLE;

//                  distance[index]=MAXDDOUBLE;
//                  prev[index-1]= null;
//                prev[vertex]= null;
            }

        }
        for (int i = 0; i < graph1.getNumberVertices() - 1; i++) {
//            distance[index] = MAXDDOUBLE;
//             = arr[i];

        }

        for (int i = 0; i < newPriorityQueue.size(); i++) {
//             = arr[i];

        }

//        newPriorityQueue.addAll(graph.getVertices());
        return newPriorityQueue;
    }

    //    //TODO
    private int Relax(Vertex u, Vertex v, int weight) {

// (u,v) edge, w weight function
        int zahl = 0;
        return zahl;
    }

//    public int initializeWIght(Vertex u ,Vertex v){
//        int gewicht =
//        return  gewicht ;
//    }



    public static void main(String[] args) {

        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(1);

        System.out.println("queue: " + q);

    }

}