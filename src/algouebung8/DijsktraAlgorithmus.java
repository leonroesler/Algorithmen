/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algouebung8;

import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author emil
 */
public class DijsktraAlgorithmus {
// SAFE 100% richtig 

    private Graph graph;
    private Vertex vertex;
    private Double MAXDOUBLE = Double.POSITIVE_INFINITY;

    //Distance alternative zum zweidimensionalen Array
    //mit dem Index(Double) greift man auf einen Hashmap zu ueber die man mit der ID die distanc erhält 
    private HashMap<Double, HashMap<Double, Double>> vertexDistance;
    private HashMap<Integer, Double> distanceMap = new HashMap<>();
    private HashMap<Integer, Double> prevDistanceMap = new HashMap<>();
    //Distance alternative zum zweidimensionalen Array ENDE
    private Double[] distance;
    private Double[] prevDistance;
// SAFE 100% richtig ENDE

    //TODO noch nicht sicher ob als Attribut der Klasse oder Lokale Variable
    private PriorityQueue<Vertex> unvisitedSet;
//  private PriorityQueue<Vertex> visitedSet;

    /**
     *
     * @param graph
     * @param weight
     * @param startpoint
     */
    private DijsktraAlgorithmus(Graph graph, Double weight, Vertex startpoint) {
//        TODO die wieght funtion  den Vertex irgendwoher holen der  über bestimmen des kürzesten Weges
        this.graph = graph;
//        weight= weightFunction(vertex, startpoint);
        this.vertex = startpoint;
        this.unvisitedSet = initializeSingleSource(graph, startpoint);

    }

    /**
     *
     * @param graph
     * @param startpoint
     * @return
     */
    private PriorityQueue initializeSingleSource(Graph graph, Vertex startpoint) {
//        distance[startpoint.getId()]= 0.0;
        //collection die alle Vertices enthält
        Collection<Vertex> allVerteciesInGraph = graph.getVertices();

//      PriorityQueue<Vertex> unvisitedSet = new PriorityQueue<Vertex>();
        //queue die alle unbesuchten/unbearbeiteten vertices enthalten sollte 
        unvisitedSet = new PriorityQueue<Vertex>();

        PriorityQueue<Vertex> visitedSet = new PriorityQueue<Vertex>();
        //falls benötig wird
        Double vertexIndex = 1.0;
//      TODO  HIER BIN ICH TODO

        for (Vertex vertexfromGraph : allVerteciesInGraph) {
            Integer vertexId = vertexfromGraph.getId();

            if (vertexfromGraph.getId() != startpoint.getId()) {
                //  die distance  am Index ... bekommt den wert  Maxdouble
                //HASHMAP variante
                distanceMap.put(vertexId, MAXDOUBLE);
                prevDistanceMap.put(vertexId, null);
                distanceMap.put(startpoint.getId(), 0.0);
                //HASHMAP variante ENDE

                //ARRAY variante 
                distance[vertexId] = MAXDOUBLE;
                prevDistance[vertexId] = null;
                distance[startpoint.getId()] = 0.0;
                //ARRAY variante ENDE
                //zur Queue hinzufügen 
                unvisitedSet.add(vertexfromGraph);
            }
            while (unvisitedSet != null || unvisitedSet.size() != 0) {
//                Der Abstand vom Startknoten zum Knoten v verkürzt sich dann,
//                wenn der Weg zu v über u kürzer als der bisher bekannte Weg ist.
//                Entsprechend wird u zum Vorgänger von v auf dem kürzesten Weg.
//                                        u = distance[v]
//                Edge distanceFromUandV = new Edge(vertex, vertex)
                //siehe beschreibung von relax
                relax(vertex, vertex, vertexIndex); // u muss noch zugewiesen werden nochmal lesen was sie will -.-,
            }

        }

        // das Stimmt so nicht ganz glaube ich knnte mich auch Irren :D
        return unvisitedSet;
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
        weight = weightFunction(u, v);
        distance[u.getId()] = weight;
        Integer uID = u.getId();
        if (distance[u.getId()] > distance[v.getId()] + weight) {
            distance[v.getId()] = distance[v.getId()] + weight;
            prevDistance[v.getId()] = uID.doubleValue();
        }
    }

    /**
     * methode that calculate and return the weight of to vertecies as Double
     * value
     *
     * @param u
     * @param v
     * @return
     */
    private Double weightFunction(Vertex u, Vertex v) {
        Edge distanceFromUandV = new Edge(u, v);
        Integer tempWeight = distanceFromUandV.getWeight();
        Double weight;
        weight = tempWeight.doubleValue();
        return weight;
    }

    //TODO  herrausfinden wie man einen Vertex aus der Queue herrausextrahiert 
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
    private void extractMin(PriorityQueue filledPriorityQueue, Vertex toExtratc) {
        if (filledPriorityQueue != null || !filledPriorityQueue.isEmpty()) {

            filledPriorityQueue.remove(toExtratc);
        }

    }

}
