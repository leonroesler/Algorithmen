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
import java.util.Map;
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
        GraphLesenOwn graphLesen = new GraphLesenOwn();
        File file4 = new File("C:/Users/emil/Documents/NetBeansProjects/AlgoUebung8/src/algouebung8/BeispieleGewichtet/graph8_4.txt");
        File file = new File("C:/Users/emil/Documents/NetBeansProjects/AlgoUebung8/src/algouebung8/BeispieleGewichtet/graph8_2.txt");
        File file5 = new File("C:/Users/emil/Documents/NetBeansProjects/AlgoUebung8/src/algouebung8/BeispieleGewichtet/graph8_5.txt");
        File file1 = new File("C:/Users/emil/Documents/NetBeansProjects/AlgoUebung8/src/algouebung8/BeispieleGewichtet/graphwsu_neu.txt");
//        C:\Users\emil\Documents\NetBeansProjects\AlgoUebung8\src\algouebung8\BeispieleGewichtet\graphwsu_neu.txt
        String dat = "BeispieleGewichtet/graph8_2.txt";
//        C:/Users/emil/Documents/NetBeansProjects/AlgoUebung8/src/algouebung8/graph8_2.txt
        //@todo Test Graph 
        final Comparator<VertexDist> shortestDistanceComparator = new Comparator<VertexDist>() {
            public int compare1(VertexDist left, VertexDist right) {
                // note that this trick doesn't work for huge distances, close to Integer.MAX_VALUE
                Double leftTemp = left.getDistance();
                Double rigthTemp = right.getDistance();
                Double resultTemp = getShortestDistance(left) - getShortestDistance(right);
                int result = resultTemp.intValue();

                return (result == 0) ? left.compareTo(right) : result;
            }

            @Override
            public int compare(VertexDist aktuell, VertexDist prev) {
                Double resultD = getShortestDistance(aktuell) - getShortestDistance(prev);
                int result = resultD.intValue();
                return (result == 0) ? aktuell.compareTo(prev) : result;
            }

            //Comparator anonymous class implementation
//	public static Comparator<VertexDist> idComparator = new Comparator<VertexDist>(){
//		
//		@Override
//		public int compare(VertexDist c1, VertexDist c2) {
//            return (int) (c1.getDistance() - c2.getDistance());
//        }
//	};
            /**
             * @return the shortest distance from the source to the given
             * VertexDist, if there is no route to the destination returns
             * infinity
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
        GraphOwn graph;
        Graph graphOri;
//        System.out.println("DAs kein PLAN MAN :"+graphLesen.FileToWeightedGraphOwn(dat, true));   
        graph = graphLesen.FileToWeightedGraphOwn(file.toString(), true);
        PriorityQueue<VertexDist> unvisitedSet;
        PriorityQueue<VertexDist> visitedSet = new PriorityQueue<VertexDist>(shortestDistanceComparator);
        unvisitedSet = new PriorityQueue<VertexDist>(shortestDistanceComparator);
        HashMap<VertexDist, Double> errorControll = new HashMap<VertexDist, Double>();

        Collection<VertexDist> vertexDistCollection = graph.getVertices();
//        unvisitedSet.addAll(vertexDistCollection);
//        for(VertexDist vertexDist:vertexDistCollection){
//            Double distance = vertexDist.getDistance();
//            System.out.println("Vertex is "+vertexDist);
//            System.out.println("Distance : "+distance);
//            unvisitedSet.add(distance);
//        }

        Collection<VertexDist> vertexCollection = graph.getVertices();

        for (VertexDist vertexDist : vertexCollection) {
            if (vertexDist.getId() == 0.0) {
                vertexDist.setDistance(0);
                vertexDist.setPrevVertex(null);
                Integer vertexId = vertexDist.getId();
//                unvisitedSet.add(vertexDist.getDistance());
//                unvisitedSet.add(vertexDist);
            } //            else if (vertexDist.getId() == 4.0) {
            //                vertexDist.setDistance(8);
            ////                unvisitedSet.add(vertexDist.getDistance());
            //                Integer vertexId = vertexDist.getId();
            //                unvisitedSet.add(vertexDist);
            //            } 
            else {
                Double distance = vertexDist.getDistance();
                Integer vertexId = vertexDist.getId();
                System.out.println("Vertex is " + vertexDist);
                System.out.println("Distance : " + distance);
//            unvisitedSet.add(distance);
                unvisitedSet.add(vertexDist);
            }
        }
//        System.out.println("DAS SIND IDE VERTICES AUS DEM GRAPHEN " + graphOri.getVertices());
        System.out.println("DAS SIND IDE VERTICES AUS DEM GRAPHENOWN " + graph.getVertices());
        System.out.println("*****************das ist die PQ die hure!! " + unvisitedSet);

//HAsmapp die die Distance ausgibt
//      key:ID value:distance to s temp
        HashMap<Integer, Double> distance = new HashMap<>();
        HashMap<Integer, Double> distanceMap = new HashMap<>();
        HashMap<Integer, Double> prevDistanceMap = new HashMap<>();
//      HashMap die ber den den Index auf die ID zugreift ? 
        HashMap<Integer, Double> zahl2 = new HashMap<>();
//        PriorityQueue<Vertex> unvisteited = new PriorityQueue<Vertex>();
        PriorityQueue<Double> visitedSetHash = new PriorityQueue<Double>(graph.getNumberVertices());

        VertexDist startpoint = new VertexDist(0);
        startpoint.setDistance(0);
        Double MAXDOUBLE = Double.POSITIVE_INFINITY;
        System.out.println("DAS IST  MAXDOUBLE: " + MAXDOUBLE);
        //key is distance
//        HashMap<Double,HashMap<Integer
//        HashMap<Integer,HashMap<Vertex,Double>> 
        //**** TESTEN OB DER GRAPH gefuelt wurde ***//
        if (graph.getNumberVertices() != 0 && graph != null) {

            System.out.println("das ist die anzehl der Vertices:" + graph.getNumberVertices());
            System.out.println("das ist die anzehl der Edges:" + graph.getEdges());

            PriorityQueue<Double> filledPriorityQueue = new PriorityQueue<Double>(graph.getNumberVertices());
//            PriorityQueue<Double> unvisitedSet = new PriorityQueue<Double>(graph.getNumberVertices());

//            PriorityQueue<Vertex> unvisitedSet = new PriorityQueue<Vertex>(graph.getNumberVertices());
            Collection<VertexDist> vertexIDcollection = graph.getVertices();

            for (VertexDist vertex : vertexIDcollection) {
                Integer vertexId = vertex.getId();
                if (vertex.getId() == startpoint.getId()) {
                    System.out.println("das ist der start vertex" + vertex);
                }
//                System.out.println("das ist der start vertex" + vertex.getId());
                if (vertex.getId() != startpoint.getId()) {
                    System.out.println("das ist der start vertex" + vertex);
                    //zur Queuefür die unbesuchten Knoten hinzufügen
                    System.out.println("hier ist die UNVISITED PQ " + unvisitedSet);
                }
            }
//********* hier beginnt die relax Methode *******//////
/////************KÖNNTE EINE EIGEN METHODE SEIN ************
            for (VertexDist vertex : vertexIDcollection) {
                Collection<EdgeOwn> incidentEdges = graph.getIncidentEdgesOwn(vertex);
                System.out.println("DAS SIND DIE ANZAHL DER INCIDENTKANTEN: " + incidentEdges);
                for (EdgeOwn edge : incidentEdges) {
                    Integer egdeWeight = edge.getWeight();
                    System.out.println("WEIGHT DER KANTE !!: " + egdeWeight);
                    System.out.println("das ist der fehler bei VertexA : " + edge.getVertexA());

                    VertexDist vertexB;
                    VertexDist vertexA;
                    if ((edge.getVertexB().getId() < edge.getVertexA().getId())) {
                        vertexB = edge.getVertexA();
                        vertexA = edge.getVertexB();
                    } else {
                        vertexB = edge.getVertexB();
                        vertexA = edge.getVertexA();
//                    Collection<VertexDist> allNeighbours = graph.getNeighbours(vertex);
//                    for(VertexDist vertexNeighbour : allNeighbours){
                    }
//                    edge.getWeight();
                    //Variable um das gewicht des vorgänger zu errechnen 
                    Double prevVertexDistWeight = vertexA.getDistance();
//                    Double prevSonderFall = vertexB.getDistance();
//                    if (vertexB.getId() == (vertexB.getId())) {
                    System.out.println("*************Hier Fängt es ANNN!!!!!************");

                    //@TODO
                    Double vertexWeight = egdeWeight.doubleValue() + prevVertexDistWeight;
                    Double weight = vertexB.getDistance();
                    System.out.println("das ist GEWICHT DER KANTE: " + egdeWeight);
                    System.out.println("das ist Weight : " + weight);
                    /////************KÖNNTE EINE EIGEN METHODE SEIN -ENDE- ************
//                    if (vertexB.getDistance() >= vertexWeight) {
//                        
//                    }
                        System.out.println("HIer komme ich rein ");
                        if ((vertexB.getDistance() >= vertexA.getDistance() + egdeWeight) && (!visitedSet.contains(vertexB) || visitedSet.contains(vertexA))) {
                            System.out.println("*******ICH BIN DIRNNE ********");
                            Integer newPredecessor = vertexA.getId();
                            unvisitedSet.remove(vertexB);
                            visitedSet.remove(vertexB);
                            System.out.println("Distance unvistPQ direkt NACH remove" + unvisitedSet);
                            //Nur al Hilfe
                            errorControll.remove(vertexB);
                            //hier geht weiter 
                            vertexB.setDistance(vertexWeight);
                            vertexB.setPrevVertex(newPredecessor.doubleValue());
                            visitedSet.add(vertexB);
                            //nur als Hilfe
                            errorControll.put(vertexB, vertexB.getDistance());
                            System.out.println("ERROR CONTROLL !!: " + errorControll);
                            System.out.println("visited set UPDATED= " + visitedSet);
                            System.out.println("DISTANCE get UPDATED= " + vertexB.getDistance());
                            System.out.println("UNVISITED Map" + unvisitedSet);
                            System.out.println("weigth Value ist " + weight);
                            System.out.println("*************OK NÄCHSTER SCHRITT!!!!!************");
                        }

                    System.out.println("AUSSERHALB DER SCHLEIFE");
                    System.out.println("visited set=  AUSSERHALB DER SCHLEIFE " + visitedSet);
                    System.out.println("unvisited set=  AUSSERHALB DER SCHLEIFE " + unvisitedSet);

//                }
//                    distanceMap.values();
                }

            }
            while (!visitedSet.isEmpty()) {
                System.out.println("visisted Komisches verhalten ?:" + visitedSet);
                visitedSet.poll();
                System.out.println("die reihenfolge müsste sich ändern " + visitedSet);
            }

            for (VertexDist visDist : visitedSet) {

            }
            filledPriorityQueue.clear();
//            distanceMap.remove(startpoint.getId());
            filledPriorityQueue.addAll(distanceMap.values());

            System.out.println("die richtige PQ GEFÜLLT MIT DEN RICHTIGEN GEWICHTEN !!" + filledPriorityQueue);
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
