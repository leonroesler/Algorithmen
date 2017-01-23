/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algouebung8;

import java.io.File;
import java.util.PriorityQueue;

/**
 * Main Class to run and test Dijkstra Algorithm
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Graphen einlesen, um sie benutzen zu können
        GraphLesenOwn graphLesen = new GraphLesenOwn();
        File file = new File("graphDat/graph8_4.txt");
        File file2 = new File("graphDat/graph8_2.txt");
        File file3 = new File("graphDat/graph8_5.txt");
        File file4 = new File("graphDat/graphwsu_neu.txt");

        //Neuen Graphen erzeugen und mit Kanten und Ecken aus der Datei füllen
        GraphOwn graph;
        graph = graphLesen.FileToWeightedGraphOwn(file4.toString(), true);

        //Versuchen den Algorithmus auszuführen, wenn eine Exception geworfen wurde, wird diese gefangen und die Nachricht ausgegeben
        PriorityQueue<VertexDist> weight = new PriorityQueue<VertexDist>();
        try {
            Dijkstra dijkstra = new Dijkstra(graph, weight, graph.getVertexDist(0));
            dijkstra.executeDijkstra();
            //Die Liste mit den besuchten Knoten durchgehen, um alle kürzeste Pfade auszugeben
            for (VertexDist vertexDist : dijkstra.getVisitedPQ()) {
                dijkstra.getPath(vertexDist);
            }
        } catch (NegativeEdgeException ex) {
            System.err.printf("" + ex.getMessage() + "\n");
        }

    }

}
