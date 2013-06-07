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
		
		Stack<Node> queue = new Stack<Node>();
		queue.add(nodes[i]);
		
		while (!queue.isEmpty()) {
			Node node = queue.pop();
			
			visited[node.i] = true;			
			
			if (node.i == j) {
				int l = 0;
				int[] path = new int[nodes.length];
				
				path[l++] = j;
				
				while (predecessors[l - 1] != predecessors[l - 1]) {
					path[l++] = predecessors[l - 1];
				}
				
				path[l] = i;
				
				for (; l >= 0; l--)
					result.add(path[l]);
				
				return new Pair<Integer, List<Integer>>(costs[j], result);
			}
			
			for (Connection c: node.adjacencyList) {
				if (visited[c.otherNode])
					continue;
				
				predecessors[c.otherNode] = node.i;
				costs[c.otherNode] = costs[node.i] + 1;

				queue.add(nodes[c.otherNode]);
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
				int[] path = new int[nodes.length];
				
				path[l++] = j;
				
				while (predecessors[l - 1] != predecessors[l - 1]) {
					path[l++] = predecessors[l - 1];
				}
				
				path[l] = i;
				
				for (; l >= 0; l--)
					result.add(path[l]);
				
				return new Pair<Integer, List<Integer>>(costs[j], result);
			}
			
			for (Connection c: node.adjacencyList) {
				if (visited[c.otherNode])
					continue;
				
				predecessors[c.otherNode] = node.i;
				costs[c.otherNode] = costs[node.i] + 1;

				queue.add(nodes[c.otherNode]);
			}
		}
		
		return new Pair<Integer, List<Integer>>(costs[j], result);
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
