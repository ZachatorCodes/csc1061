package MyGraph;

import java.util.Set;
import java.util.LinkedHashSet;

public class GraphHelperTest {

	public static void main(String[] args) throws Exception{
		
		// Graphs - depthFirstTraversal()
        UnweightedGraphHelper<Integer> graph1 = new UnweightedGraphHelper<>();
        graph1.addVertex(1);
        graph1.addVertex(2);
		graph1.addVertex(3);
        graph1.addVertex(4);
        graph1.addVertex(5);
        graph1.addEdge(1,2);
        graph1.addEdge(1,3);
        graph1.addEdge(1,5);
        graph1.addEdge(2,3);
        graph1.addEdge(3,4);
        UnweightedGraphHelper<Integer>.GraphIterator graph1Itr = graph1.iterator();
        
        System.out.println("Adjacency vertex lists:");
        while (graph1Itr.hasNext())
            System.out.println(graph1Itr.next());
        
		System.out.println("\nPerforming a depth first traversal of the graph");
		System.out.printf("%s \n\n", depthFirstTraversal(graph1, 2));        
        
        UnweightedGraphHelper<String> graph2 = new UnweightedGraphHelper<>();
		graph2.addVertex("Bob");
		graph2.addVertex("Alice");
		graph2.addVertex("Mark");
		graph2.addVertex("Rob");
		graph2.addVertex("Maria");
		graph2.addEdge("Bob", "Alice");
		graph2.addEdge("Bob", "Rob");
		graph2.addEdge("Alice", "Mark");
		graph2.addEdge("Rob", "Mark");
		graph2.addEdge("Alice", "Maria");
		graph2.addEdge("Rob", "Maria");
        
        UnweightedGraphHelper<String>.GraphIterator itr = graph2.iterator();
        
        System.out.println("Adjacency vertex lists:");
        while (itr.hasNext())
            System.out.println(itr.next());
        
		System.out.println("\nPerforming a depth first traversal of the graph2");
		System.out.println(depthFirstTraversal(graph2, "Bob"));
	}
	
//*******************************************Graphs***************************************************

	// TODO fill in these methods
	static <T> Set<T> depthFirstTraversal(UnweightedGraphHelper<T> graph, T root)throws Exception {
	        LinkedHashSet<T> dftSet = new LinkedHashSet<>();
	        dfs(graph, root, dftSet);
	        return dftSet;
	    }
    
    static <T> void dfs(UnweightedGraphHelper<T> graph, T root, LinkedHashSet<T> dftSet) throws Exception{
		return;
	}
}


