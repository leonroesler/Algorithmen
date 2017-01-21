/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algouebung8;

import com.sun.javafx.geom.Curve;
import java.io.File;
import java.util.Collection;
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

//HAsmapp die die Distance ausgibt
//      key:ID value:distance to s temp
        HashMap<Integer, Double> distance = new HashMap<>();
        HashMap<Integer, Double> distanceMap = new HashMap<>();
        HashMap<Integer, Double> prevDistanceMap = new HashMap<>();
//      HashMap die ber den den Index auf die ID zugreift ? 
        HashMap<Integer, Double> zahl2 = new HashMap<>();
//        PriorityQueue<Vertex> unvisteited = new PriorityQueue<Vertex>();
        PriorityQueue<Double> visitedSetHash = new PriorityQueue<Double>(graph.getNumberVertices());

        Vertex startpoint = new Vertex(0);
        Double MAXDOUBLE = Double.POSITIVE_INFINITY;
        System.out.println("DAS IST  MAXDOUBLE: " + MAXDOUBLE);
        //key is distance
//        HashMap<Double,HashMap<Integer
//        HashMap<Integer,HashMap<Vertex,Double>> 
        //**** TESTEN OB DER GRAPH gefuelt wurde ***//
        if (graph.getNumberVertices() != 0 && graph != null) {

            System.out.println("das ist die anzehl der Vertices:" + graph.getNumberVertices());
            System.out.println("das ist die anzehl der Edges:" + graph.getEdges().size());

            PriorityQueue<Double> filledPriorityQueue = new PriorityQueue<Double>(graph.getNumberVertices());
            PriorityQueue<Double> unvisitedSet = new PriorityQueue<Double>(graph.getNumberVertices());
            PriorityQueue<Integer> visitedSet = new PriorityQueue<Integer>(graph.getNumberVertices());
//            PriorityQueue<Vertex> unvisitedSet = new PriorityQueue<Vertex>(graph.getNumberVertices());

            Collection<Vertex> vertexIDcollection = graph.getVertices();

            for (Vertex vertex : vertexIDcollection) {
                Integer vertexId = vertex.getId();
                if (vertex.getId() == startpoint.getId()) {
                    System.out.println("das ist der start vertex" + vertex);
                }
//                System.out.println("das ist der start vertex" + vertex.getId());
                if (vertex.getId() != startpoint.getId()) {
                    //HASHMAP variante
                    //  die distance mit dem VertexID... bekommt den wert  Maxdouble
                    distanceMap.put(vertexId, MAXDOUBLE);
                    //  die prevdistance mit dem VertexID... bekommt den wert  null
                    prevDistanceMap.put(vertexId, null);
                    //HASHMAP variante ENDE
                    System.out.println("das ist der start vertex" + vertex);
                    //zur Queuefür die unbesuchten Knoten hinzufügen

//                    unvisitedSet.add(vertexId.doubleValue());
                    unvisitedSet.add(vertexId.doubleValue());
                    System.out.println("hier ist die UNVISITED PQ " + unvisitedSet);
//                    System.out.println(unvisitedSet.peek());
//                    System.out.println(unvisitedSet.poll() + ":" + unvisitedSet.peek());
                }

            }
            distanceMap.put(startpoint.getId(), 0.0);
//********* hier beginnt die relax Methode *******//////
            for (Vertex vertex : vertexIDcollection) {
                Collection<Edge> incidentEdges = graph.getIncidentEdges(vertex);
                for (Edge edge : incidentEdges) {
                    Integer egdeWeight = edge.getWeight();
                    System.out.println("das ist der fehler bei VertexA : " + edge.getVertexA());
                    Vertex vertexB = edge.getVertexB();
                    Vertex vertexA = edge.getVertexA();
                    Double prevVertexWeight = distanceMap.get(vertexA.getId());
                    if (vertexB.getId() == (vertexB.getId())) {
                        System.out.println("*************Hier Fängt es ANNN!!!!!************");
                        // TODO Nachgucken ob Hashmap doppelte Einträge generiert 
                        //LOESUNG MIT HASHSET ASUTAUSCHEN !!!! 
                        System.out.println("Distance Map vor remove" + distanceMap);

                        //@TODO
                        Double vertexWeight = egdeWeight.doubleValue() + prevVertexWeight;
                        Double vertexWeightHASH = egdeWeight.doubleValue() + prevVertexWeight;
                        Double weight = distanceMap.get(vertexB.getId());
                        System.out.println("das ist Weight : " + weight);
                        if (vertexWeight < distanceMap.get(vertexB.getId())) {
                            unvisitedSet.
                            distanceMap.remove(vertexB.getId());
                            System.out.println("Distance Map direkt NACH remove" + distanceMap);
                            distanceMap.put(vertexB.getId(), egdeWeight.doubleValue() + prevVertexWeight);
//                        visitedSetHash.add(distanceMap.get(vertexB.getId()));
                            //nach dem hinzufügen in der PQ wird der Vertex entfernt um aufaddierung der werte zu verhindern

                            visitedSet.add(vertexB.getId());
//                  visitedSet.addAll(unvisitedSet);
                            System.out.println("visited set UPDATED= " + visitedSet);
                            System.out.println("Distance Map" + distanceMap);
                            Integer idVertex = vertexB.getId();
//                        Double weight= distanceMap.get(vertexB.getId());
//                        Double weight= distanceMap.get(vertexB.getId());
//                        visitedSetHash.add(distanceMap.get(vertexB.getId()));
                            visitedSetHash.add(vertexWeight);
                            System.out.println("PQ mit GEWICHTEN " + visitedSetHash);
                            System.out.println("PQ mit GEWICHTEN AUS DER DISTANCEMAP " + visitedSetHash);

//                        weight.intValue();
                            System.out.println("weigth Value ist " + weight);
                            filledPriorityQueue.add(vertexWeight);
//                            filledPriorityQueue.add(egdeWeight.doubleValue());
                            System.out.println("PQ" + filledPriorityQueue);
                           
                            System.out.println("*************OK NÄCHSTER SCHRITT!!!!!************");
                        }

                    }
//                    distanceMap.values();

                }

            }filledPriorityQueue.clear();
//            distanceMap.remove(startpoint.getId());
                   filledPriorityQueue.addAll(distanceMap.values());
                    System.out.println("die richtige PQ GEFÜLLT MIT DEN RICHTIGEN GEWICHTEN !!"+filledPriorityQueue);
        }

//        /*******TEMPORÄRE METHODE ***************/
//        //collection die alle Vertices enthält
//        Collection<Vertex> allVerteciesInGraph = graph.getVertices();
//        //queue die alle unbesuchten/unbearbeiteten vertices enthalten sollte 
//        PriorityQueue<Integer>unvisitedSet = new PriorityQueue<Integer>(graph.getNumberVertices());
//
//        for (Vertex vertexfromGraph : vertexIDcollection) {
//            Integer vertexId = vertexfromGraph.getId();
//            if (vertexfromGraph.getId() != startpoint) {
//                //HASHMAP variante
//                //  die distance mit dem VertexID... bekommt den wert  Maxdouble
//                distanceMap.put(vertexId, MAXDOUBLE);
//                //  die prevdistance mit dem VertexID... bekommt den wert  null
//                prevDistanceMap.put(vertexId, null);
//                distanceMap.put(startpoint.getId(), 0.0);
//                //HASHMAP variante ENDE
//                //zur Queuefür die unbesuchten Knoten hinzufügen 
//                unvisitedSet.add(vertexfromGraph.getId());
//
//            }
//
//        }
//        /*******TEMPORÄRE METHODE ENDE ***************/
        // TODO code application logic here
//        Vertex u ;
//        Vertex v;
//               
//        weightFunction( u,v);
//      
        int zahl[] = new int[3];
        zahl[0] = 3;

//Graph graph = new Graph(vertexset, edgeset);
//Key Vertex Value distance
        zahl2.put(0, 3.0);
        Vertex vertexID;
        vertexID = new Vertex(0);
        Vertex vertexB = new Vertex(5);
//        unvisteited.add(vertexID.getId());
//        unvisteited.add(vertexID);
        distance.put(vertexID.getId(), Double.POSITIVE_INFINITY);
        prevDistanceMap.put(vertexID.getId(), 1.0);
        vertexID = new Vertex(1);

        //*********************HIER IST ES ********************************
//        unvisteited.add(vertexID);
//        PriorityQueue<String> pq = new PriorityQueue<String>();
//        pq.add("carrot");
//        pq.add("apple");
//        pq.add("banana");
//        System.out.println("hier ist die PQ " + pq);
//        System.out.println(pq.peek());
//        System.out.println(pq.poll() + ":" + pq.peek());
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
//        Edge e = new Edge(vertexID, vertexB);
//        System.out.println("das ist die Lösung " + e.getVertexB().getId());
    }

}
