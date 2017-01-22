/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algouebung8;

import static algouebung8.Test.vertexDistCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * this class realize the DijsktraAlgorithmus with a PiorityQueue/ PQ. the PQ
 * sorte the Vertecie, with priority sorted by weight acsending
 *
 * @author emil
 */
public class DijsktraAlgorithmusPQ {

    private GraphOwn graph;
    private VertexDist vertex;
    private Double MAXDOUBLE = Double.POSITIVE_INFINITY;
//TODO if unneeded remove
    private HashMap<Integer, Double> prevDistanceMap = new HashMap<>();
    //filled with all vertecies from graph 
    Collection<VertexDist> vertexCollection;
    // shortestDistanceComparator for the PQ 
    private final Comparator<VertexDist> shortestDistanceComparator = new Comparator<VertexDist>() {
        @Override
        public int compare(VertexDist aktuell, VertexDist prev) {
            Double resultD = getShortestDistance(aktuell) - getShortestDistance(prev);
            int result = resultD.intValue();
            return (result == 0) ? aktuell.compareTo(prev) : result;
        }

        /**
         * @return the shortest distance from the source to the given
         * VertexDist, if there is no route to the destination returns infinity
         *
         */
        private Double getShortestDistance(VertexDist aktuell) {
//            Double shortesDistance = aktuell.getDistance();
            Double shortesDistance = aktuell.getDistance();
            int shortesIntDistance = shortesDistance.intValue();
            // tow options Double.POSITIVE_INFINITY or a number
            return shortesDistance;
        }
    };
    //this PQ safe the vertexID of unvistedvertecies with priority sorted by weight decesnding
    private PriorityQueue<VertexDist> unvisitedSet = new PriorityQueue<>(vertexCollection.size(), shortestDistanceComparator);
    //
    private PriorityQueue<VertexDist> pq = new PriorityQueue<VertexDist>(vertexCollection.size(), shortestDistanceComparator);

    /**
     *
     * @param graph
     * @param pq
     * @param startpoint
     */
    private DijsktraAlgorithmusPQ(GraphOwn graph, PriorityQueue pq, VertexDist startpoint) {
//        TODO die wieght methode,  den VertexDist aus dem Vertex den wir aus der PQ haben darüber bestimmen des kürzesten Weges
        this.graph = graph;
//        weight= weightFunction(vertex, startpoint);
        
        this.vertex = startpoint;
        vertexCollection = graph.getVertices();

    }

    private void initializeSingleSource2(GraphOwn graph, VertexDist startpoint) {
        //collection die alle Vertices enthält
//        Collection<VertexDist> allVerteciesInGraph = graph.getVertices();
        //queue die alle unbesuchten/unbearbeiteten vertices enthalten sollte 
//        unvisitedSet = new PriorityQueue<Integer>(graph.getNumberVertices());

        for (VertexDist vertexfromGraph : vertexCollection) {
            Integer vertexId = vertexfromGraph.getId();
            if (vertexfromGraph.getId() != startpoint.getId()) {

                vertexfromGraph.setDistance(MAXDOUBLE);
                vertexfromGraph.setPrevVertex(null);

            }

        }
        startpoint.setDistance(0.0);
//        startpoint.setPrevVertex(null);
    }

    /**
     * method to fill the ProirityQueue with alle Vertecies from the Graph
     * Assume: that all Vertices are not visited
     */
    private void fillPriorityQueue() {
        // PQ unvisted
        //vertexCollection unvisited Vertecies
        //****** AUS ALGOUEBUNG8*******
        for (VertexDist vertexDist : vertexCollection) {
            {
                unvisitedSet.add(vertexDist);
            }
        }
    }

    /**
     * The method relaxation of an edge(u,v) consists in the examination, if the
     * so far determined shortest path to v * can be improved by taking the
     * current shortest path from s to u and append the edge(u,v) assumes
     * VertexDist are numbered 0, 1, ... n and that the source VertexDist is 0
     *
     * @param u
     * @param v
     * @param weight
     */
    private void relax(VertexDist u, VertexDist v, Double weight) {

        fillPriorityQueue();
        Collection<EdgeOwn> incidentEdges = graph.getIncidentEdges(u);
        //TODO wegen FOLIEN gucken ob es so geht ???
        Collection<EdgeOwn> adj = graph.getNeighbours(u);
        for (EdgeOwn edge : incidentEdges) {
            Integer egdeWeight = edge.getWeight();
//
            VertexDist vertexB = edge.getVertexB();
            VertexDist vertexA = edge.getVertexA();
            //Variable um das gewicht des vorgänger zu errechnen 
            Double prevVertexDistWeight = vertexA.getDistance();
            
            Double vertexWeight = egdeWeight.doubleValue() + prevVertexDistWeight;
//TODO ist der pArt richtig Laut Folien ?? 
            if (vertexB.getId() == (v.getId()) && vertexWeight <= vertexB.getDistance()) {
                // TODO Nachgucken ob Hashmap doppelte Einträge generiert 
                //LOESUNG MIT HASHSET ASUTAUSCHEN !!!! 
                unvisitedSet.remove(vertexB);
                vertexB.setDistance(vertexWeight);
                unvisitedSet.add(vertexB);
                
//                distanceMap.remove(vertexB.getId());
//                distanceMap.put(vertexB.getId(), vertexWeight);
                //TODO UNDO //                
                pq.add(vertexB);
////                visitedSet.addAll(unvisitedSet);
                System.out.println("visited set= " + pq);
                System.out.println("visited set= " + unvisitedSet);
//               
            }
        }
        //Der Part stimmt 100% mit den Folien ueberein 
        if(u.getDistance()> v.getDistance()+weight){
            Double newDistance = v.getDistance()+weight;
            Integer newPredecessor = u.getId();
            v.setDistance(newDistance);
            v.setPrevVertex(newPredecessor.doubleValue());
        }
    }

    //utility method to poll data from queue
    /**
     * this is literally the extract-Methode for the PriorityQue it uses the
     * methode poll of the PQ and removes the vertex at the head of the PQ and
     * returns the id from thhe vertex author: emil Steinkopf
     *
     * @param customerPriorityQueue
     */
    private static void pollDataFromQueue(PriorityQueue<VertexDist> customerPriorityQueue) {
        while (customerPriorityQueue.peek()!=null) {
            //               U = extract-Min(Q)
            VertexDist vertexU = customerPriorityQueue.poll();
            System.out.println("Processing Vertex with ID=" + vertexU.getId());
        }
    }

    //utility method to add random data to Queue
    private static void addDataToQueue(PriorityQueue<VertexDist> customerPriorityQueue) {
        Random rand = new Random();
        for (int i = 0; i < vertexDistCollection.size(); i++) {
            int id = rand.nextInt(100);
            int dist = rand.nextInt(100);
            VertexDist newVertexDist = new VertexDist(id);
            newVertexDist.setDistance(dist);
            customerPriorityQueue.add(newVertexDist);
        }
    }

}
