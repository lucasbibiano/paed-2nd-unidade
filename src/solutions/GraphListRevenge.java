package solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.javatuples.Pair;

public class GraphListRevenge {
	private Node[] nodes;
	
	public GraphListRevenge(int nNodes) {
		nodes = new Node[nNodes];
		
		for (int i = 0; i < nNodes; i++) {
			nodes[i] = new Node(i);
		}
	}
	
	public Pair<Integer, List<Integer>> path(int i, int j) {
		boolean[] visited = new boolean[nodes.length];
		int[] predecessors = new int [nodes.length];
		int[] costs = new int[nodes.length];
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int k = 0; k < nodes.length; k++) {
			predecessors[k] = k;
			costs[k] = 0;
		}
		
		Stack<Node> stack = new Stack<Node>();
		stack.add(nodes[i]);
		
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			
			visited[node.i] = true;			
			
			if (node.i == j) {
				int l = 0;
				int predecessor;
				int aux;
				int[] path = new int[nodes.length];
				
				aux = j;
				predecessor = predecessors[aux];
				
				do {
					path[l++] = aux;
					
					predecessor = aux;
					aux = predecessors[predecessor];
				} while (aux != predecessor);
				
				l--;
				
				for (; l >= 0; l--)
					result.add(path[l]);
				
				return new Pair<Integer, List<Integer>>(costs[j], result);
			}
			
			for (Connection c: node.adjacencyList) {
				if (visited[c.otherNode])
					continue;
				
				predecessors[c.otherNode] = node.i;
				costs[c.otherNode] = costs[node.i] + c.cost;

				stack.add(nodes[c.otherNode]);
			}
		}
		
		return new Pair<Integer, List<Integer>>(costs[j], result);
	}
	
	public Pair<Integer, List<Integer>> shortest(int i, int j) {
		boolean[] visited = new boolean[nodes.length];
		int[] predecessors = new int [nodes.length];
		int[] costs = new int[nodes.length];
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int k = 0; k < nodes.length; k++) {
			predecessors[k] = k;
			costs[k] = 0;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(nodes[i]);
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			visited[node.i] = true;			
			
			if (node.i == j) {
				int l = 0;
				int predecessor;
				int aux;
				int[] path = new int[nodes.length];
				
				aux = j;
				predecessor = predecessors[aux];
				
				do {
					path[l++] = aux;
					
					predecessor = aux;
					aux = predecessors[predecessor];
				} while (aux != predecessor);
				
				l--;
				
				for (; l >= 0; l--)
					result.add(path[l]);
				
				return new Pair<Integer, List<Integer>>(costs[j], result);
			}
			
			for (Connection c: node.adjacencyList) {
				if (visited[c.otherNode])
					continue;
				
				predecessors[c.otherNode] = node.i;
				costs[c.otherNode] = costs[node.i] + c.cost;

				queue.add(nodes[c.otherNode]);
			}
		}
		
		return new Pair<Integer, List<Integer>>(costs[j], result);
	}
	
	public Pair<Integer, List<Integer>> dijkstra(int i, int j) {
		boolean[] visited = new boolean[nodes.length];
		int[] predecessors = new int [nodes.length];
		int[] costs = new int[nodes.length];
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int k = 0; k < nodes.length; k++) {
			predecessors[k] = k;
			costs[k] = 0;
		}
		
		HeapMinRevenge<Node> heap = new HeapMinRevenge<Node>();
		heap.insert(nodes[i], 0);
		
		while (!heap.isEmpty()) {
			Node node = heap.extract().getValue1();
			
			visited[node.i] = true;			
			
			if (node.i == j) {
				int l = 0;
				int predecessor;
				int aux;
				int[] path = new int[nodes.length];
				
				aux = j;
				predecessor = predecessors[aux];
				
				do {
					path[l++] = aux;
					
					predecessor = aux;
					aux = predecessors[predecessor];
				} while (aux != predecessor);
				
				l--;
				
				for (; l >= 0; l--)
					result.add(path[l]);
				
				return new Pair<Integer, List<Integer>>(costs[j], result);
			}
			
			for (Connection c: node.adjacencyList) {
				if (visited[c.otherNode])
					continue;
				
				predecessors[c.otherNode] = node.i;
				costs[c.otherNode] = costs[node.i] + c.cost;

				heap.insert(nodes[c.otherNode], costs[c.otherNode]);
			}
		}
		
		return new Pair<Integer, List<Integer>>(costs[j], result);
	}
	
	//bellman ford
	public Pair<Integer, List<Integer>> bellmanFord(int i, int j) {
		
		int[] predecessors = new int [nodes.length];
		int[] costs = new int[nodes.length];
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int k = 0; k < nodes.length; k++) {
			predecessors[k] = k;
			
			if (k == i)
				costs[k] = 0;
			else
				costs[k] = 999999;
		}
		
		boolean relaxed = true;	
		
		//gerar lista de arestas
		
		for (Node node : nodes){
			
			
		}
		
		
		
		for (int k = 0; k < nodes.length && relaxed; k++) {
			relaxed = false;
			
			for (Edge edge: edges) {
				if (costs[edge.i] + edge.cost < costs[edge.j]) {
					costs[edge.j] = costs[edge.i] + edge.cost;
					predecessors[edge.j] = edge.i;
					relaxed = true;
				}
			}
		}
		
		//---------------------------------------------------------------
		
		for (Edge edge: edges) {
			if (costs[edge.i] + edge.cost < costs[edge.j]) {
				return new Pair<Integer, List<Integer>>(-Integer.MAX_VALUE, result);
			}
		}
		
		int l = 0;
		int predecessor;
		int aux;
		int[] path = new int[adjacencyMatrix.length];
		
		//System.out.println(Arrays.toString(predecessors));
		//System.out.println(Arrays.toString(costs));

		aux = j;
		predecessor = predecessors[aux];
		
		do {
			path[l++] = aux;
			
			predecessor = aux;
			aux = predecessors[predecessor];
			
		} while (aux != predecessor);
		
		l--;
		
		if (aux == i) {
			for (; l >= 0; l--)
				result.add(path[l]);
		}
		
		return new Pair<Integer, List<Integer>>(costs[j], result);
		
		
		
		
	}
	
	//negative cycle
	public boolean hasNegativeCycle() {
	
	}
	
	public void addEdge(int i, int j) {
		nodes[i].adjacencyList.add(new Connection(j, 1));
	}
	
	public void addEdge(int i, int j, int cost) {
		nodes[i].adjacencyList.add(new Connection(j, cost));
	}
	
	private class Node {
		int i;
		List<Connection> adjacencyList;
		
		public Node(int i) {
			this.i = i;
			adjacencyList = new LinkedList<Connection>();
		}
		
		public boolean equals(Object other) {
			Node h = (Node) other;
			return i == h.i;
		}
		
		@Override
		public int hashCode() {
			return new Integer(i).hashCode();
		}
	}
	
	private class Edge {
		int i;
		int j;
		int cost;
		
		public Edge(int i, int j, int cost) {
			this.i = i;
			this.j = j;
			this.cost = cost;
		}
	}
	
	private class Connection {
		int otherNode;
		int cost;
		
		public Connection(int otherNode, int cost) {
			this.otherNode = otherNode;
			this.cost = cost;
		}
	}
}
