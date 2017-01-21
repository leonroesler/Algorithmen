/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algouebung8;

import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author emil
 */
public class Test {
    
   public  static File file = new File("C:/Users/emil/Documents/NetBeansProjects/AlgoUebung8/src/algouebung8/BeispieleGewichtet/graph8_5.txt");
   public  static     File file1 = new File("C:/Users/emil/Documents/NetBeansProjects/AlgoUebung8/src/algouebung8/BeispieleGewichtet/graphwsu_neu.txt");
    public  static  GraphOwn graph = GraphLesenOwn.FileToWeightedGraphOwn(file.toString(), true);
    public  static Collection<VertexDist> vertexDistCollection = graph.getVertices();
  public static void main(String[] args) {
		    
		//natural ordering example of priority queue
		PriorityQueue<Double> doublePriorityQueue = new PriorityQueue<>(vertexDistCollection.size());
		Random rand = new Random();
		for(int i=0;i<vertexDistCollection.size();i++){
			doublePriorityQueue.add(new Double(rand.nextInt(100)));
		}
		for(int i=0;i<vertexDistCollection.size();i++){
			Double in = doublePriorityQueue.poll();
			System.out.println("Processing Double:"+in);
                      Comparator comp=  doublePriorityQueue.comparator();
                      System.out.println ( "Comparator value is: "+ comp);
		}
		
		//PriorityQueue example with Comparator
		PriorityQueue<VertexDist> customerPriorityQueue = new PriorityQueue<>(vertexDistCollection.size(), idComparator);
		addDataToQueue(customerPriorityQueue);
		    System.out.println("die PQ "+customerPriorityQueue);
                    
		pollDataFromQueue(customerPriorityQueue);
		
	}
	
	//Comparator anonymous class implementation
	public static Comparator<VertexDist> idComparator = new Comparator<VertexDist>(){
		
		@Override
		public int compare(VertexDist c1, VertexDist c2) {
            return (int) (c1.getDistance() - c2.getDistance());
        }
	};

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
	
	//utility method to poll data from queue
	private static void pollDataFromQueue(PriorityQueue<VertexDist> customerPriorityQueue) {
		while(customerPriorityQueue.peek()!=null){
			VertexDist vertex = customerPriorityQueue.poll();
			System.out.println("Processing Vertex with ID="+vertex.getId());
		}
	}

}
        
        
        
//        Double 
//        PriorityQueue<Vertex> pq=
//                new PriorityQueue<Vertex>(5, (a,b) ->  (Double aD=a.getDistance(); Double bD= b.getDistance();) -> aD-bD);
//        pq.add("Apple");
//        pq.add("PineApple");
//        pq.add("Custard Apple");
//        while (pq.size() != 0)
//        {
//            System.out.println(pq.remove());
//        }
    

