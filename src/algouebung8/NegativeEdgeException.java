/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algouebung8;

/**
 *
 * @author emil
 */
public class NegativeEdgeException extends Exception {

    /**
     * Creates a new instance of <code>NegativeEdgeException</code> without
     * detail message.
     */
    public NegativeEdgeException() {
        super("Eine NegativeKante wurde gefunden");
    }

    /**
     * Constructs an instance of <code>NegativeEdgeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NegativeEdgeException(String msg) {
        super(msg);
        
    }
    
//    public String exceptionMessage(){
//        
//    }
}
