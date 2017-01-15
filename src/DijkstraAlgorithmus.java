import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Diese Klasse initialisiert den Dijkstra Algorithmus.
 *
 * @author Emil Steinkopf, Leon Rössler, Stephan Dünkel, Ersin Yildirim
 */
public class DijkstraAlgorithmus {
    // SAFE 100% richtig

    private Graph graph;
    private Vertex vertex;
    private Double MAXDOUBLE = Double.POSITIVE_INFINITY;

    //Distance alternative zum zweidimensionalen Array
    //mit dem Index(Double) greift man auf einen Hashmap zu ueber die man mit der ID die distanc erhält
//    private HashMap<Vertex.id, Integer> vertexDistance;
//    predecessor
//    private HashMap<Vertex.id, Vertex.id> vertexDistance;
    private HashMap<Double, HashMap<Double, Double>> vertexDistance;

    private HashMap<HashMap<Double, Integer>, Double> shortesvertexDistance;
    private HashMap<Integer, Double> distanceMap = new HashMap<>();
    private HashMap<Integer, Double> prevDistanceMap = new HashMap<>();
    //Distance alternative zum zweidimensionalen Array ENDE
    private Double[] distance;
    private Double[] prevDistance;

    // SAFE 100% richtig ENDE

    //TODO noch nicht sicher ob als Attribut der Klasse oder Lokale Variable
    private PriorityQueue<Integer> unvisitedSet;
    private PriorityQueue<Integer> visitedSet;

    /**
     *
     * @param graph
     * @param weight
     * @param startpoint
     */
    private DijkstraAlgorithmus(Graph graph, Double weight, Vertex startpoint) {
        //TODO die wieght funtion den Vertex irgendwoher holen der über bestimmen des kürzesten Weges
        this.graph = graph;
//      weight= weightFunction(vertex, startpoint);
        this.vertex = startpoint;

    }

    private void initializeSingleSource2(Graph graph, Vertex startpoint) {
        //collection die alle Vertices enthält
        Collection<Vertex> allVerteciesInGraph = graph.getVertices();
        //queue die alle unbesuchten/unbearbeiteten vertices enthalten sollte
        unvisitedSet = new PriorityQueue<Integer>(graph.getNumberVertices());

        for (Vertex vertexfromGraph : allVerteciesInGraph) {
            Integer vertexId = vertexfromGraph.getId();
            if (vertexfromGraph.getId() != startpoint.getId()) {
                //HASHMAP variante
                //  die distance mit dem VertexID... bekommt den wert  Maxdouble
                distanceMap.put(vertexId, MAXDOUBLE);
                //  die prevdistance mit dem VertexID... bekommt den wert  null
                prevDistanceMap.put(vertexId, null);
                distanceMap.put(startpoint.getId(), 0.0);
                //HASHMAP variante ENDE
                //zur Queuefür die unbesuchten Knoten hinzufügen
                unvisitedSet.add(vertexfromGraph.getId());

            }

        }
    }


    /**
     * The method relaxation of an edge(u,v) consists in the examination, if the
     * so far determined shortest path to v * can be improved by taking the
     * current shortest path from s to u and append the edge(u,v) assumes Vertex
     * are numbered 0, 1, ... n and that the source Vertex is 0
     *
     * @param u
     * @param v
     * @param weight
     */
    private void relax(Vertex u, Vertex v, Double weight) {
        //asume u is start vertex
        // PQ unvisted
        //graph

        Collection<Edge> incidentEdges = graph.getIncidentEdges(u);
        for (Edge edge : incidentEdges) {
            Integer egdeWeight = edge.getWeight();
            Vertex vertexB = edge.getVertexB();
            //TODO Nachgucken ob Hashmap doppelte Einträge generiert
            //LOESUNG MIT HASHSET AUSTAUSCHEN !!!!
            distanceMap.remove(vertexB.getId(), egdeWeight.doubleValue());
            distanceMap.put(vertexB.getId(), egdeWeight.doubleValue());
            visitedSet.add(vertexB.getId());

        }

//        vertex später an erstestelle
//        [startVertex,,2,3,4,5,6]
// private HashMap<vertex.ID, gewicht/distance>> vertexDistance;
// private HashMap<HashMap<Double, Vertex.id>, Double> vertexDistance;
// private HashMap<Integer, HashMap<Double, Double>> vertexDistance;

        //ist es richtig distance als klassenvariable zu definieren

        distance[u.getId()] = weight;
        Integer uID = u.getId();
//        HashMap<Integer, Double> vDistance = new HashMap<>();
        if (distance[u.getId()] > distance[v.getId()] + weight) {
            distance[v.getId()] = distance[v.getId()] + weight;
            prevDistance[v.getId()] = uID.doubleValue();
//            vDistance.put(v.getId(), v.getId() + weight);
        }
//        return vDistance;
    }

    //TODO herrausfinden wie man einen Vertex aus der Queue herrausextrahiert
    //1.ANSATZ man steckt in die Que nicht den vertex sondern seine ID und vergleicht die ID
    //des übergebenden VERTEX mit der aus der QUE und removed sie dann
    /**
     * 2.ANSATZ you will have to delete and re-insert each element which is
     * editted. (the actual element, and not a dummy one!). so, every time you
     * update distances, you need to remove and add the elements that were
     * affected by the changed entree. remove(Object o) doesn't have to be O(n).
     * It's just the Java library use a naive O(n) method to locate the element
     * to remove. If instead you use an indexing structure like a map to store
     * the position of the element in your heap, you can do remove in O(log(n)).
     */
    /**
     * Assumes that PQ is not empty method that ectract min value from Priority
     * Queue
     *
     * @param filledPriorityQueue
     * @param toExtratc
     */
    private void extractMin(PriorityQueue<Vertex> filledPriorityQueue, Vertex toExtratc) {
        if (filledPriorityQueue != null || !filledPriorityQueue.isEmpty()) {
            // nimmt den VErtext an erster Stelle = startpoint
            Vertex source = filledPriorityQueue.peek();
            Integer index = 1;
            for (Vertex vertextInPQ : filledPriorityQueue) {
                if (vertextInPQ.getId() != source.getId()) {
                    Integer vertextID = vertextInPQ.getId();
                    //@TODO aus der PQ das gewicht zeihen mit peek und durch relax vergleichen lassen
                    //https://github.com/Gerst20051/JAVA/blob/master/workspace/graphs/src/renaud/waldura/dijkstra/DijkstraEngine.java
                    // relax(source, vertextInPQ, fil)); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!<--------------------------------------------
//                    vertexDistance.put(index, distanceMap.put(vertextInPQ.getId(), dis));
                    distanceMap.put(index, vertextID.doubleValue());
                    index++;
                }
            }

            filledPriorityQueue.remove(toExtratc);
        }

    }

    /**
     * Assumes that PQ is not empty method that ectract min value from Priority
     * Queue
     *
     * @param filledPriorityQueue
     * @param newdistance
     */
    private void extractMin2(PriorityQueue<Integer> filledPriorityQueue, Double newdistance) {
        if (filledPriorityQueue != null || !filledPriorityQueue.isEmpty()) {
            // nimmt den VErtext an erster Stelle = startpoint
            Integer source = filledPriorityQueue.peek();
            //setz den index auf 1 um nicht den startpoint zunhemen Depricated weil Hashmap
            Integer index = 1;
            for (Integer vertextInPQwithID : filledPriorityQueue) {
                if (vertextInPQwithID != source && distanceMap.get(vertextInPQwithID) != MAXDOUBLE) {
                    //der Vertex mit der kleinsten distance
                    Integer vertexIdWithshortestDistance = vertextInPQwithID;
//                private HashMap<HashMap<Double, Integer>, Double> shortesvertexDistance;
                    Double u = distanceMap.get(vertextInPQwithID);
//                entferne u aus Q
                    filledPriorityQueue.remove(vertexIdWithshortestDistance);

                    //TODO DANN PASSIERT WAS
                    //DANN WIEDER HINZUFÜGEN
                    filledPriorityQueue.add(vertexIdWithshortestDistance);
//                    vertexDistance.put(index, distanceMap.put(vertextInPQ.getId(), dis));
                    distanceMap.put(index, vertexIdWithshortestDistance.doubleValue());
                }
            }

        }

    }

}
