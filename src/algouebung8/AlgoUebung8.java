/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algouebung8;

import com.sun.javafx.geom.Curve;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author emil
 */
public class AlgoUebung8 {
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
        // TODO code application logic here
//        Vertex u ;
//        Vertex v;
//               
//        weightFunction( u,v);
//      
        int zahl[] = new int[3];
        zahl[0] = 3;

//HAsmapp die die Distance ausgibt
//      key:ID value:distance to s temp
        HashMap<Integer, Double> distance = new HashMap<>();
        HashMap<Integer, Double> prevDistance = new HashMap<>();
//      HashMap die ber den den Index auf die ID zugreift ? 
        HashMap<Integer, Double> zahl2 = new HashMap<>();
        PriorityQueue<Vertex> unvisteited = new PriorityQueue<Vertex>();
        //key is distance
//        HashMap<Double,HashMap<Integer
//        HashMap<Integer,HashMap<Vertex,Double>> 
//Graph graph = new Graph(vertexset, edgeset);
//Key Vertex Value distance
        zahl2.put(0, 3.0);
        Vertex vertexID;
        vertexID = new Vertex(0);
//        unvisteited.add(vertexID.getId());
        unvisteited.add(vertexID);
        distance.put(vertexID.getId(), Double.POSITIVE_INFINITY);
        prevDistance.put(vertexID.getId(), 1.0);
        vertexID = new Vertex(1);

        //*********************HIER IST ES ********************************
//        unvisteited.add(vertexID);
        PriorityQueue<String> pq = new PriorityQueue<String>();
        pq.add("carrot");
        pq.add("apple");
        pq.add("banana");
        System.out.println(pq.peek());
        System.out.println(pq.poll() + ":" + pq.peek());
//        System.out.println(pq.peek());
//        System.out.println(pq.poll() + ":" + pq.peek());
//        System.out.println(pq.peek());
//        System.out.println(pq.poll() + ":" + pq.peek());
        
// prints apple and banana rather than apple and apple
        //******************HIER ENDET ES *********************************
//        distance.put(vertexID.getId(), Double.POSITIVE_INFINITY);
//        prevDistance.put(vertexID.getId(),2.0);
//        
//        System.out.println(unvisteited.peek());
//        
//        System.out.println("HIER KOMMEN DIE HASHMAPS");
//        System.out.println(distance.get(vertexID.getId()-1));
//        System.out.println(prevDistance.get(vertexID.getId()-1));
//        
//        
////        System.out.println(zahl[0]);
////        System.out.println(zahl2.get(0));
////        System.out.println(vertexID);
//        System.out.println("HIER KOMMEN DIE HASHMAPS 2.0");
//        System.out.println(distance.get(vertexID.getId()));
//        System.out.println(prevDistance.get(vertexID.getId()));
    }

}
