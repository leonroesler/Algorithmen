import java.util.*;

/**
 * Die Klasse Main testet den Dijkstra Algorithmus mit den Beispielen aus dem BeispieleGewichtet Ordner.
 *
 * @author Emil Steinkopf, Leon Rössler, Stephan Dünkel, Ersin Yildirim
 */
public class Main {

    /**
     * Diese Methode liest die Beispieldaten, löst diese
     * nach dem Dijkstra-Algorithmus und gibt die Ergebnisse aus.
     * Die Beispieldaten werden einmal gerichtet und einmal ungerichtet gelöst.
     *
     * @param args Befehlszeilenargument
     */
    public static void main(final String[] args) {

        // Startzeitpunkt
        final long timeStart = System.currentTimeMillis();

        // Initialisiere Dijkstra-Algorithmus
        final Dijkstra dijkstra;

        // Beispieldaten Test:

        // Testfall 1: Es testet die Datei graph8_2.txt, der Graph ist gerichtet.
        GraphLesen.FileToGraph("src/BeispieleGewichtet/graph8_2.txt", true); // true wenn gerichtet
        GraphLesen.FileToWeightedGraph("src/BeispieleGewichtet/graph8_2.txt", true); // true wenn gerichtet
        System.out.println("______________________________________");

        // Testfall 1: Es testet die Datei graph8_2.txt, der Graph ist ungerichtet.
        GraphLesen.FileToGraph("src/BeispieleGewichtet/graph8_2.txt", false); // false wenn ungerichtet
        GraphLesen.FileToWeightedGraph("src/BeispieleGewichtet/graph8_2.txt", false); // false wenn ungerichtet
        System.out.println("______________________________________");

        // Testfall 2: Es testet die Datei graph8_4.txt, der Graph ist gerichtet.
        GraphLesen.FileToGraph("src/BeispieleGewichtet/graph8_4.txt", true);
        GraphLesen.FileToWeightedGraph("src/BeispieleGewichtet/graph8_4.txt", true);
        System.out.println("______________________________________");

        // Testfall 3: Es testet die Datei graph8_5.txt, der Graph ist gerichtet.
        GraphLesen.FileToGraph("src/BeispieleGewichtet/graph8_5.txt", true);
        GraphLesen.FileToWeightedGraph("src/BeispieleGewichtet/graph8_5.txt", true);
        System.out.println("______________________________________");

        // Testfall 4: Es testet die Datei graphwbf.txt, der Graph ist gerichtet.
        GraphLesen.FileToGraph("src/BeispieleGewichtet/graphwbf.txt", true);
        GraphLesen.FileToWeightedGraph("src/BeispieleGewichtet/graphwbf.txt", true);
        System.out.println("______________________________________");

        // Testfall 5: Es testet die Datei graphwfw.txt, der Graph ist gerichtet.
        GraphLesen.FileToGraph("src/BeispieleGewichtet/graphwfw.txt", true);
        GraphLesen.FileToWeightedGraph("src/BeispieleGewichtet/graphwfw.txt", true);
        System.out.println("______________________________________");

        // Testfall 6: Es testet die Datei graphwsu.txt, der Graph ist gerichtet.
        GraphLesen.FileToGraph("src/BeispieleGewichtet/graphwsu.txt", true);
        GraphLesen.FileToWeightedGraph("src/BeispieleGewichtet/graphwsu.txt", true);
        System.out.println("______________________________________");


        // Priority Queue Funktionstest
        final PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(1);
        q.add(20);

        System.out.println("Die erstellte Priority Queue wird ausgegeben:");
        System.out.println(q);
        System.out.println("______________________________________");

        // Endzeitpunkt
        final long timeEnd = System.currentTimeMillis();

        // Ausgabe der Compilierungsdauer
        System.out.println("Der Test hat insgesamt " + (timeEnd - timeStart) + "ms gedauert.");
    }

}