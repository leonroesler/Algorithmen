/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algouebung8;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


/**
 * this class realize the DijsktraAlgorithmus with a PiorityQueue/ PQ. the PQ
 * sorte the Vertecie, with priority sorted by weight acsending
 *
 */
public class Dijkstra {

    private GraphOwn graph;
    private VertexDist startVertex;
    private Double MAXDOUBLE = Double.POSITIVE_INFINITY;

    //filled with all vertecies from graph
    Collection<VertexDist> vertexCollection;

    //Der Comparator wird für die PQ benutzt, um angeben zu könnnen, wie sie den Inhalt sortieren soll
    // muss generische klasse bekommen, mit welcher man vergleichen will
    // compare classe wird verändert (nach was verglichen werden soll)
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

    // PQ um die Vertecies darin abzulegen, die noch nicht besucht wurden
    private PriorityQueue<VertexDist> unvisitedPQ = new PriorityQueue<>(7, shortestDistanceComparator);
    // PQ um die Vertecies darin abzulegen, die schon besucht wurden
    private PriorityQueue<VertexDist> visitedPQ = new PriorityQueue<VertexDist>(7, shortestDistanceComparator);
    private PriorityQueue weight = new PriorityQueue<VertexDist>(7, shortestDistanceComparator);


    /**
     * Konstruktor
     * Füllt die collectionOfEdges mit allen Kanten des Graphen
     * Füllt die PQ der Kantengewichte mit den Gewichten aller Kanten
     * Ruft die Methode zum Überprüfen auf negative Kantengewichte auf
     * Füllt die PQ mit den unbesuchten Knoten auf
     *
     * @param  graph
     * @param  weight
     * @param  startVertex
     */
    public Dijkstra(GraphOwn graph, PriorityQueue weight, VertexDist startVertex) throws NegativeEdgeException {
        this.graph = graph;
        //Kanten aus dem Graphen in die collectionOfEdges legen
        Collection<EdgeOwn> collectionOfEdges = graph.getEdges();
        weightMethode(collectionOfEdges, weight);
        //Kantengewichte überprüfen
        negativeEdgeCheck(collectionOfEdges);
        this.weight = weight;
        this.startVertex = startVertex;
        //Knoten aus dem Graphen in die vertexCollection legen
        vertexCollection = graph.getVertices();

        System.out.println("Alle Knonten in diesem Graphen: " + vertexCollection + "\n");

        initializeSingleSource(graph, startVertex);
        //unvisitedPQ initialisieren
        initUnvisitedPQ();
    }

    /**
     *  Weight Methode für Dijkstra Algorithmus
     * @param collectionOfEdges
     * @param weight
     */
    private void weightMethode(Collection<EdgeOwn> collectionOfEdges, PriorityQueue weight) {
        for (EdgeOwn edge : collectionOfEdges) {
            String msg = "this Edge has negative Weight!!";
            if (edge.getWeight() < 0) {

                try {
                    throw new NegativeEdgeException(msg);
                } catch (NegativeEdgeException ex) {
                    ex.getMessage();
                }
            }
            Integer edgeWeight = edge.getWeight();
            weight.add(edgeWeight.doubleValue());
        }
    }

    /**
     * Alle Ecken aus der collectionOfEdges durchgehen und überprüfen, ob eine Kante ein negatives Gewicht hat
     *      Wenn ja, wird eine Exception geworfen
     * @param collectionOfEdges
     * @throws NegativeEdgeException
     */
    private void negativeEdgeCheck(Collection<EdgeOwn> collectionOfEdges) throws NegativeEdgeException {
        for (EdgeOwn edge : collectionOfEdges) {
            String msg = "Dijkstra won't work with Edges with negative weight!";
            if (edge.getWeight() < 0) {
                throw new NegativeEdgeException();

            }
        }
    }

    /**
     * Geht alle Vertecies des Graphen durch und setzt die Standardwerte für dist auf maxdouble und für prevVertex auf null
     * @param graph
     * @param startpoint
     */
    private void initializeSingleSource(GraphOwn graph, VertexDist startpoint) {

        for (VertexDist vertexFromGraph : vertexCollection) {
            Integer vertexId = vertexFromGraph.getId();
            //System.out.println("VertexID: " + vertexId);
            if (vertexFromGraph.getId() != startpoint.getId()) {
                vertexFromGraph.setDistance(MAXDOUBLE);
                vertexFromGraph.setPrevVertex(null);
            }
        }
        startpoint.setDistance(0.0);
    }

    /**
     * befüllt die unvisitedPQ zu beginn einmal mit allen Knoten des Graphen
     */
    private void initUnvisitedPQ() {
        for (VertexDist vertexDist : vertexCollection) {
            unvisitedPQ.add(vertexDist);
        }
        //System.out.println("Unvisited PQ NACH INIT: " + unvisitedPQ);
    }

    /**
     * Führt den Dijkstra-algorithmus aus, indem über die initWeightrelaxMethod die relax Methode ausgerufen wird
     * @throws NegativeEdgeException
     */
    public void executeDijkstra() throws NegativeEdgeException {

        // solange die unvisitedPQ nicht leer ist
        while (unvisitedPQ.peek() != null) {
            //Den ersten Knoten aus der Liste der noch nicht besuchten Knoten herausholen und damit die initWeightrelaxMethod aufrufen
            VertexDist vertexU = unvisitedPQ.poll();
            initWeightrelaxMethod(vertexU);
        }
        //System.out.println("Das ist die VISITED sortierte PQ" + visitedPQ);
    }

    /**
     * Holt sich alle Kanten ausgehend vom übergebenen Knoten
     * überprüft, ob die Vertexe A & B auch die richtigen, wie gedacht sind, um damit dann relax richtig aufrufen zu können
     * @param vertexU
     */
    public void initWeightrelaxMethod(VertexDist vertexU){
        //Alle Kanten ausgehend des übergebenen Knoten holen
        Collection<EdgeOwn> incidentEdges = graph.getIncidentEdgesOwn(vertexU);
        Integer egdeWeight;

        //Jede Kante, die mit dem Knoten verbunden ist durchgehen
        for (EdgeOwn edge : incidentEdges) {
            egdeWeight = edge.getWeight();
            VertexDist vertexA, vertexB;

            //Wenn die ID des VertexB kleiner als die ID des VertexA ist, werden die Variablen vertauscht,
            // ansonsten bleiben sie so und es kann relax aufgerufen werden
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

    /**
     * Überprüft ob der bisherige Weg zu VertexA kleiner ist, als der Weg über VertexB zu VertexA
     * Dazu wird der kürzeste Weg von VertexA mit dem kürzesten Weg zu VertexB plus dem Weg zwischen VertexA & B verglichen
     * Wenn der Weg über VertexB zu VertexA kürzer ist, werden die dist und prev Attribute aktualisiert
     * @param vertexA
     * @param vertexB
     * @param egdeWeight
     */
    private void relax(VertexDist vertexA, VertexDist vertexB, Double egdeWeight) {
        //Distanz zum VertexA
        Double prevVertexDistWeight = vertexA.getDistance();

        //Wenn die Distanz zum VertexB größergleich der Distanz zum VertexA plus der Distanz zwischen den beiden Vertecies ist,
        // dann werden die Attribute für den Vorgänger und die Distanz des Knotens in der visitedPQ aktualisiert
        if ((vertexB.getDistance() >= vertexA.getDistance() + egdeWeight)) {
            Double newDistance = prevVertexDistWeight + egdeWeight;
            VertexDist newPrevVertex = vertexA;
            visitedPQ.remove(vertexB);
            vertexB.setDistance(newDistance);
            vertexB.setPrevVertex(newPrevVertex);
            visitedPQ.add(vertexB);
            //System.out.println("visited PQ NACH UPDATE: " + visitedPQ);
        }
    }

    /*
     * Dokumentiert den Weg zwischen dem Startknoten und dem Zielknoten und gibt den Weg als Liste zurück,
     * die durchgegangen werden kann zum Ausgeben des Pfades
     */
    public LinkedList<VertexDist> getPath(VertexDist target) {
        LinkedList<VertexDist> path = new LinkedList<VertexDist>();
        VertexDist waypoint = target;
        //Wenn der waypoint keinen Vorgänger hat, wird null zurückgegebenm, da es sich dann um den Startknoten handelt
        if (waypoint.getPrevVertex() == null) {
            return null;
        }

        //das Ziel als ersten Wegpunkt festlegen
        path.add(waypoint);
        //solange die Entfernung des aktuellen Wegpunktes nicht der Entfernung des Startpunktes entspricht,
        //wird der aktuelle Wegpunkt des Weges hinzugefügt
        while (waypoint.getPrevVertex().getDistance() != startVertex.getDistance()) {
            waypoint = waypoint.getPrevVertex();
            path.add(waypoint);
        }

        //Reihenfolge des Pfades umdrehen, damit er beim Startknoten startet
        Collections.reverse(path);

        System.out.println("Das ist der kürzeste Weg zum Zielknoten: " + target);
        System.out.println(startVertex);
        //Alle Knoten des Pfades durchgehen und ausgeben
        String pathFromVertex;
        for (VertexDist vertexPath : path) {
            pathFromVertex = vertexPath.toString();

            System.out.println(pathFromVertex);
        }
        return path;
    }

    // Getter für die visitedPQ
    public PriorityQueue<VertexDist> getVisitedPQ() {
        return visitedPQ;
    }
}
