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
 *
 * @author emil
 */
public class DijsktraAlgorithmusPQ {

    private GraphOwn graph;
    private VertexDist vertex;
    private Double MAXDOUBLE = Double.POSITIVE_INFINITY;

    private HashMap<HashMap<Double, Integer>, Double> shortesvertexDistance;
    private HashMap<Integer, Double> distanceMap = new HashMap<>();
    private HashMap<Integer, Double> prevDistanceMap = new HashMap<>();

    //hier wird nur die ID als referenz gespeichert 
    private PriorityQueue<VertexDist> unvisitedSet;
    private final Comparator<VertexDist> shortestDistanceComparator = new Comparator<VertexDist>() {
        @Override
		public int compare(VertexDist v1, VertexDist v2) {
              int result= (int)(v1.getDistance() - v2.getDistance());
            return  (result == 0) ? v1.compareTo(v2) : result;
        }
        public int compare1(VertexDist left, VertexDist right) {
            // note that this trick doesn't work for huge distances, close to Integer.MAX_VALUE
            int result = getShortestDistance(left) - getShortestDistance(right);

            return (result == 0) ? left.compareTo(right) : result;
        }
        //TODO NEEDED???????
        /**
         * @return the shortest distance from the source to the given
         * VertexDist, if there is no route to the destination returns infinity
         *
         */
        private int getShortestDistance(VertexDist aktuell) {
            Double shortesDistance = aktuell.getDistance();
            int shortesIntDistance = shortesDistance.intValue();
            // tow options Double.POSITIVE_INFINITY or a number
            return shortesIntDistance;
        }
    };
    private PriorityQueue<VertexDist> pq =new  PriorityQueue<VertexDist>(0, shortestDistanceComparator);

    private void initializeSingleSource2(GraphOwn graph, VertexDist startpoint) {
        //collection die alle Vertices enthält
        Collection<VertexDist> allVerteciesInGraph = graph.getVertices();
        //queue die alle unbesuchten/unbearbeiteten vertices enthalten sollte 
//        unvisitedSet = new PriorityQueue<Integer>(graph.getNumberVertices());

        for (VertexDist vertexfromGraph : allVerteciesInGraph) {
            Integer vertexId = vertexfromGraph.getId();
            if (vertexfromGraph.getId() != startpoint.getId()) {

                vertexfromGraph.setDistance(MAXDOUBLE);
                vertexfromGraph.setPrevVertex(null);

            }

        }
        startpoint.setDistance(0.0);
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
        //asume u is start vertex and v ist goal vertex
        // PQ unvisted 
        //graph 
        unvisitedSet = new PriorityQueue<VertexDist>(shortestDistanceComparator);
        Collection<EdgeOwn> incidentEdges = graph.getIncidentEdges(u);
        for (EdgeOwn edge : incidentEdges) {
            Integer egdeWeight = edge.getWeight();

            VertexDist vertexB = edge.getVertexB();
            VertexDist vertexA = edge.getVertexA();
            Double prevVertexDistWeight = distanceMap.get(vertexA.getId());
            Double vertexWeight = egdeWeight.doubleValue() + prevVertexDistWeight;

            if (vertexB.getId() == (v.getId()) && vertexWeight <= distanceMap.get(vertexB.getId())) {
                // TODO Nachgucken ob Hashmap doppelte Einträge generiert 
                //LOESUNG MIT HASHSET ASUTAUSCHEN !!!! 
                distanceMap.remove(vertexB.getId());
                distanceMap.put(vertexB.getId(), vertexWeight);
                //TODO UNDO //                
//                visitedSet.add(vertexB.getId());
////                visitedSet.addAll(unvisitedSet);
//                System.out.println("visited set= " + visitedSet);
//                visitedSet.add(vertexB.getId());
            }
        }

//        vertex später an erstestelle 
//        [startVertexDist,,2,3,4,5,6]
// private HashMap<vertex.ID, gewicht/distance>> vertexDistance;
// private HashMap<HashMap<Double, VertexDist.id>, Double> vertexDistance;
// private HashMap<Integer, HashMap<Double, Double>> vertexDistance;
        Integer uID = u.getId();
//        HashMap<Integer, Double> vDistance = new HashMap<>();
        if (distanceMap.get(u.getId()) > distanceMap.get(v.getId()) + weight) {
            distanceMap.put(v.getId(), distanceMap.get(v.getId()) + weight);
            prevDistanceMap.put(v.getId(), uID.doubleValue());
        }
        //TODO UNDO //
//        if (distance[u.getId()] > distance[v.getId()] + weight) {
//            distance[v.getId()] = distance[v.getId()] + weight;
//            prevDistance[v.getId()] = uID.doubleValue();
////            vDistance.put(v.getId(), v.getId() + weight);
//        }
//        return vDistance;
    }
    
    //utility method to poll data from queue
    
	private static void pollDataFromQueue(PriorityQueue<VertexDist> customerPriorityQueue) {
		while(true){
			VertexDist vertex = customerPriorityQueue.poll();
			if(vertex == null) break;
			System.out.println("Processing Vertex with ID="+vertex.getId());
		}
	}
        
        //utility method to add random data to Queue
	private static void addDataToQueue(PriorityQueue<VertexDist> customerPriorityQueue) {
		Random rand = new Random();
		for(int i=0; i<vertexDistCollection.size(); i++){
			int id = rand.nextInt(100);
                        int dist = rand.nextInt(100);
                        VertexDist  newVertexDist= new VertexDist(id);
                        newVertexDist.setDistance(dist);
			customerPriorityQueue.add(newVertexDist);
		}
	}

}
