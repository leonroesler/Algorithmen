/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algouebung8;

import com.sun.javafx.geom.Curve;
import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author emil
 */
public class Main {
//private  Double weightFunction(Vertex u,Vertex v){
//        Edge distanceFromUandV = new Edge(vertex, vertex);
//        Integer tempWeight=distanceFromUandV.getWeight();
//        Double weight;
//        weight= tempWeight.doubleValue();
//        return weight;
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GraphLesen graphLesen = new GraphLesen();
        File file2 = new File("C:/Users/emil/Documents/NetBeansProjects/AlgoUebung8/src/algouebung8/BeispieleGewichtet/graph8_5.txt");
        File file = new File("C:/Users/emil/Documents/NetBeansProjects/AlgoUebung8/src/algouebung8/BeispieleGewichtet/graphwsu_neu.txt");
//        C:\Users\emil\Documents\NetBeansProjects\AlgoUebung8\src\algouebung8\BeispieleGewichtet\graphwsu_neu.txt
        String dat = "BeispieleGewichtet/graph8_2.txt";
//        C:/Users/emil/Documents/NetBeansProjects/AlgoUebung8/src/algouebung8/graph8_2.txt
        //@todo Test Graph 
        Graph graph = graphLesen.FileToWeightedGraph(file.toString(), true);
        Comparator<VertexDist> shortestDistanceComparator = new Comparator<VertexDist>() {
        @Override
        public int compare(VertexDist aktuell, VertexDist prev) {
            Double resultD = getShortestDistance(aktuell) - getShortestDistance(prev);
            int result = resultD.intValue();
            return (result == 0) ? aktuell.compareTo(prev) : result;
        };
        

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
        Collection<EdgeOwn>edge=graph.getEdges();
        PriorityQueue<Double> weight = new PriorityQueue<Double>();
        Dijkstra dijkstra= new Dijkstra(graph, weight, edge);
       
    }

}
