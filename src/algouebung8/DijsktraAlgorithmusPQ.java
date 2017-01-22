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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private  final Comparator<VertexDist> shortestDistanceComparator = new Comparator<VertexDist>() {
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
    private PriorityQueue<VertexDist> unvisitedPQ = new PriorityQueue<>(vertexCollection.size(), shortestDistanceComparator);
    //
    private PriorityQueue<VertexDist> visitedPQ = new PriorityQueue<VertexDist>(vertexCollection.size(), shortestDistanceComparator);
    private PriorityQueue weight = new PriorityQueue<VertexDist>(vertexCollection.size(), shortestDistanceComparator);

    /**
     *
     * @param graph
     * @param pq
     * @param startpoint
     */
    private DijsktraAlgorithmusPQ(GraphOwn graph, PriorityQueue weight, VertexDist startpoint) {
//        TODO die wieght methode,  den VertexDist aus dem Vertex den wir aus der PQ haben darüber bestimmen des kürzesten Weges
        this.graph = graph;
        Collection<EdgeOwn> collectionOfEdges = graph.getEdges();
        
        weightMethode(collectionOfEdges, weight);
        negativeEgdeCheck(collectionOfEdges);
        this.weight = weight;
        this.vertex = startpoint;
        vertexCollection = graph.getVertices();

    }

    private void weightMethode(Collection<EdgeOwn> collectionOfEdges, PriorityQueue weight1) {
        for (EdgeOwn edge : collectionOfEdges) {
            String msg = "this Edge has negative Weight!!";
            if (edge.getWeight() < 0) {

                try {
                    throw new NegativeEdgeException(msg);
                } catch (NegativeEdgeException ex) {
                    ex.getMessage();
                }
            }
            Integer edgeWeight= edge.getWeight();
            weight1.add(edgeWeight.doubleValue());
        }
    }

    private void negativeEgdeCheck(Collection<EdgeOwn> collectionOfEdges) {
        for (EdgeOwn edge : collectionOfEdges) {
            String msg = "this Edge has negative Weight!!";
            if (edge.getWeight() < 0) {

                try {
                    throw new NegativeEdgeException(msg);
                } catch (NegativeEdgeException ex) {
                    ex.getMessage();
                }
            }
        }
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
                unvisitedPQ.add(vertexDist);
            }
        }
    }

    /**
     * The method relaxation of an edge(u,v) consists in the examination, if the
     * so far determined shortest path to v * can be improved by taking the
     * current shortest path from s to u and append the edge(u,v) assumes
     * VertexDist are numbered 0, 1, ... n and that the source VertexDist is 0
     *
     * @param vertexA
     * @param vertexB
     * @param egdeWeight
     */
    private void relax(VertexDist vertexA, VertexDist vertexB, Double egdeWeight) {

        fillPriorityQueue();
        Collection<EdgeOwn> incidentEdges = graph.getIncidentEdges(vertexA);
        //Variable um das gewicht des vorgänger zu errechnen 
        Double prevVertexDistWeight = vertexA.getDistance();

        Double vertexWeight = egdeWeight.doubleValue() + prevVertexDistWeight;
//TODO ist der pArt richtig Laut Folien ?? 

        //Der Part stimmt 100% mit den Folien ueberein 
        if ((vertexB.getDistance() >= vertexA.getDistance() + egdeWeight) && (!visitedPQ.contains(vertexB) || visitedPQ.contains(vertexA))) {
            Double newDistance = prevVertexDistWeight + egdeWeight;
            Integer newPredecessor = vertexA.getId();
            //PQ der unvisited 
            unvisitedPQ.remove(vertexB);
            visitedPQ.remove(vertexB);
            vertexB.setDistance(newDistance);
            vertexB.setPrevVertex(newPredecessor.doubleValue());
            visitedPQ.add(vertexB);
        }
    }

    public void initWeightrelaxMethod(VertexDist vertetxU) {
        Collection<EdgeOwn> incidentEdges = graph.getIncidentEdgesOwn(vertetxU);
        negativeEgdeCheck(incidentEdges);
        System.out.println("DAS SIND DIE ANZAHL DER INCIDENTKANTEN: " + incidentEdges);
        for (EdgeOwn edge : incidentEdges) {
            Integer egdeWeight = edge.getWeight();

            VertexDist vertexB;
            VertexDist vertexA;
            if ((edge.getVertexB().getId() < edge.getVertexA().getId())) {
                vertexB = edge.getVertexA();
                vertexA = edge.getVertexB();
            } else {
                vertexB = edge.getVertexB();
                vertexA = edge.getVertexA();

            }
            relax(vertexA, vertexB, egdeWeight.doubleValue());
        }

    }

    public void executeDijkstra() {
        initializeSingleSource2(graph, vertex);
        fillPriorityQueue();
        while (unvisitedPQ.peek() != null) {
//     this is literally the extract-Methode for the PriorityQue it uses the
//     methode poll of the PQ and removes the vertex at the head of the PQ and
//      returns the id from thhe vertex
            VertexDist vertexU = unvisitedPQ.poll();
            initWeightrelaxMethod(vertexU);
        }
    }

}
