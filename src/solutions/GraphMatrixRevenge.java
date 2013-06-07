package solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.javatuples.Pair;


public class GraphMatrixRevenge {
	private int[][] adjacencyMatrix;
	
	public GraphMatrixRevenge(int nNodes) {
		adjacencyMatrix = new int[nNodes][nNodes];
	}
	
	public Pair<Integer, List<Integer>> path(int i, int j) {
		boolean[] visited = new boolean[adjacencyMatrix.length];
		int[] predecessors = new int [adjacencyMatrix.length];
		int[] costs = new int[adjacencyMatrix.length];
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int k = 0; k < adjacencyMatrix.length; k++) {
			predecessors[k] = k;
			costs[k] = 0;
		}
		
		Stack<Integer> queue = new Stack<Integer>();
		queue.add(i);
		
		while (!queue.isEmpty()) {
			int node = queue.pop();
			
			visited[node] = true;			
			
			if (node == j) {
				int l = 0;
				int[] path = new int[adjacencyMatrix.length];
				
				path[l++] = j;
				
				while (predecessors[l - 1] != predecessors[l - 1]) {
					path[l++] = predecessors[l - 1];
				}
				
				path[l] = i;
				
				for (; l >= 0; l--)
					result.add(path[l]);
				
				return new Pair<Integer, List<Integer>>(costs[j], result);
			}
			
			for (int k = 0; k < adjacencyMatrix.length; k++) {
				if (visited[k] || adjacencyMatrix[node][k] == 0)
					continue;
				
				predecessors[k] = node;
				costs[k] = costs[node] + adjacencyMatrix[node][k];

				queue.add(k);
			}
		}
		
		return new Pair<Integer, List<Integer>>(costs[j], result);
	}
	
	public Pair<Integer, List<Integer>> shortest(int i, int j) {
		boolean[] visited = new boolean[adjacencyMatrix.length];
		int[] predecessors = new int [adjacencyMatrix.length];
		int[] costs = new int[adjacencyMatrix.length];
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int k = 0; k < adjacencyMatrix.length; k++) {
			predecessors[k] = k;
			costs[k] = 0;
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(i);
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			
			visited[node] = true;			
			
			if (node == j) {
				int l = 0;
				int[] path = new int[adjacencyMatrix.length];
				
				path[l++] = j;
				
				while (predecessors[l - 1] != predecessors[l - 1]) {
					path[l++] = predecessors[l - 1];
				}
				
				path[l] = i;
				
				for (; l >= 0; l--)
					result.add(path[l]);
				
				return new Pair<Integer, List<Integer>>(costs[j], result);
			}
			
			for (int k = 0; k < adjacencyMatrix.length; k++) {
				if (visited[k] || adjacencyMatrix[node][k] == 0)
					continue;
				
				predecessors[k] = node;
				costs[k] = costs[node] + adjacencyMatrix[node][k];

				queue.add(k);
			}
		}
		
		return new Pair<Integer, List<Integer>>(costs[j], result);
	}
	
	public Pair<Integer, List<Integer>> dijkstra(int i, int j) {
		boolean[] visited = new boolean[adjacencyMatrix.length];
		int[] predecessors = new int [adjacencyMatrix.length];
		int[] costs = new int[adjacencyMatrix.length];
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int k = 0; k < adjacencyMatrix.length; k++) {
			predecessors[k] = k;
			costs[k] = 0;
		}
		
		HeapMinRevenge<Integer> heap = new HeapMinRevenge<Integer>();
		heap.insert(i, costs[i]);
		
		while (!heap.isEmpty()) {
			int node = heap.extract().getValue0();
			
			visited[node] = true;			
			
			if (node == j) {
				int l = 0;
				int[] path = new int[adjacencyMatrix.length];
				
				path[l++] = j;
				
				while (predecessors[l - 1] != predecessors[l - 1]) {
					path[l++] = predecessors[l - 1];
				}
				
				path[l] = i;
				
				for (; l >= 0; l--)
					result.add(path[l]);
				
				return new Pair<Integer, List<Integer>>(costs[j], result);
			}
			
			for (int k = 0; k < adjacencyMatrix.length; k++) {
				if (visited[k] || adjacencyMatrix[node][k] == 0)
					continue;
				
				predecessors[k] = node;
				costs[k] = costs[node] + adjacencyMatrix[node][k];

				heap.insert(k, costs[k]);
			}
		}
		
		return new Pair<Integer, List<Integer>>(costs[j], result);
	}
	
	public void addEdge(int i, int j) {
		adjacencyMatrix[i][j] = 1;
	}
	
	public void addEdge(int i, int j, int cost) {
		adjacencyMatrix[i][j] = cost;
	}
	
	private class Node {
		int i;
		List<Connection> adjacencyList;
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
