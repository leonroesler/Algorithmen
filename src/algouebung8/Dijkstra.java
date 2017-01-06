/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algouebung8;

import algouebung8.AlgoUebung8.*;
import java.util.*;

/**
 *
 * @author emil
 */
public class Dijkstra {

    private Graph graph;
    private Vertex vertex;
    private Edge<Vertex> edge;
    private PriorityQueue priorityQueue;
    private double MAXDDOUBLE = Double.POSITIVE_INFINITY;

//    private public Dijkstra(Graph graph, Vertex vertex, Edge<Vertex> edge) {
//        this.graph = graph;
//        this.vertex = vertex;
//        this.edge = edge;
//    }

    public Dijkstra(Graph graph, double distanceFromUandV, Edge edge) {
//        initializeSingleSource
        this.graph = graph;
        // u(vector) - v(vector?)
        this.edge = edge;
//         distanceFromUandV= Double.POSITIVE_INFINITY;

        priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(graph.getVertices());
    }

    //HIER SIND WIR 
    private PriorityQueue initializeSingleSource(Graph graph, Vertex startpoint) {
        startpoint = null;
        PriorityQueue newPriorityQueue = new PriorityQueue();
        newPriorityQueue.addAll(graph.getVertices());
        for (int i = 0; i < newPriorityQueue.size(); i++) {
//             = arr[i];

        }

//        newPriorityQueue.addAll(graph.getVertices());
        return newPriorityQueue;
    }

//    //TODO
//    private int Relax(Vertex u, Vertex v, int weight) {
//
//    }

}
