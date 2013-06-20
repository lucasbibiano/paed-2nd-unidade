package solutions;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.javatuples.Pair;


public class GraphMatrixRevenge {
	private int[][] adjacencyMatrix;
	private List<Edge> edges;
	
	public GraphMatrixRevenge(int nNodes) {
		adjacencyMatrix = new int[nNodes][nNodes];
		edges = new ArrayList<Edge>();
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
				int predecessor;
				int aux;
				int[] path = new int[adjacencyMatrix.length];
				
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
				int predecessor;
				int aux;
				int[] path = new int[adjacencyMatrix.length];
				
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
		int[] predecessors = new int [adjacencyMatrix.length];
		int[] costs = new int[adjacencyMatrix.length];

		List<Integer> result = new ArrayList<Integer>();
		
		HeapMinRevenge<Integer> heap = new HeapMinRevenge<Integer>();
		
		for (int k = 0; k < adjacencyMatrix.length; k++) {
			predecessors[k] = k;
			costs[k] = Integer.MAX_VALUE;
			
			if (k == i)
				costs[k] = 0;
			
			heap.insert(k, costs[k]);
		}
		
		while (!heap.isEmpty()) {
			int node = heap.extract().getValue1();

			if (costs[node] == Integer.MAX_VALUE)
				break;
			
			if (node == j) {
				int l = 0;
				int predecessor;
				int aux;
				int[] path = new int[adjacencyMatrix.length];
				
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
			
			for (int k = 0; k < adjacencyMatrix.length; k++) {
				if (adjacencyMatrix[node][k] == 0)
					continue;
				
				int cost = costs[node] + adjacencyMatrix[node][k];
				
				if (cost < costs[k]) {
					predecessors[k] = node;
					costs[k] = cost;
					heap.increasePriority(k, costs[k]);
				}

			}
		}
		
		return new Pair<Integer, List<Integer>>(costs[j], result);
	}
	
	public Pair<Integer, List<Integer>> bellmanFord(int i, int j) {
		int[] predecessors = new int [adjacencyMatrix.length];
		int[] costs = new int[adjacencyMatrix.length];
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int k = 0; k < adjacencyMatrix.length; k++) {
			predecessors[k] = k;
			
			if (k == i)
				costs[k] = 0;
			else
				costs[k] = 999999;
		}
		
		boolean relaxed = true;
		
		for (int k = 0; k < adjacencyMatrix.length && relaxed; k++) {
			relaxed = false;
			
			for (Edge edge: edges) {
				if (costs[edge.i] + edge.cost < costs[edge.j]) {
					costs[edge.j] = costs[edge.i] + edge.cost;
					predecessors[edge.j] = edge.i;
					relaxed = true;
				}
			}
		}
		
		for (Edge edge: edges) {
			if (costs[edge.i] + edge.cost < costs[edge.j]) {
				return new Pair<Integer, List<Integer>>(-Integer.MAX_VALUE, result);
			}
		}
		
		int l = 0;
		int predecessor;
		int aux;
		int[] path = new int[adjacencyMatrix.length];

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
	
	public boolean hasNegativeCycle() {
		if (hasNegativeCycleHelper(0))
			return true;
		
		return false;
	}
	
	private boolean hasNegativeCycleHelper(int i) {
		int[] predecessors = new int [adjacencyMatrix.length];
		int[] costs = new int[adjacencyMatrix.length];
		
		for (int k = 0; k < adjacencyMatrix.length; k++) {
			predecessors[k] = k;
			
			if (k == i)
				costs[k] = 0;
			else
				costs[k] = 999999;
		}
		
		boolean relaxed = true;
		
		for (int k = 0; k < adjacencyMatrix.length && relaxed; k++) {
			relaxed = false;
			
			for (Edge edge: edges) {
				if (costs[edge.i] + edge.cost < costs[edge.j]) {
					costs[edge.j] = costs[edge.i] + edge.cost;
					predecessors[edge.j] = edge.i;
					relaxed = true;
				}
			}
		}
		
		for (Edge edge: edges) {
			if (costs[edge.i] + edge.cost < costs[edge.j]) {
				return true;
			}
		}
		
		return false;
	}
	
	public Pair<Integer, List<Integer>> floydWarshall(int i, int j) {
		int[][] costs = new int[adjacencyMatrix.length][adjacencyMatrix.length];
		int[][] nexts = new int[adjacencyMatrix.length][adjacencyMatrix.length];
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (int k = 0; k < adjacencyMatrix.length; k++) {
			for (int l = 0; l < adjacencyMatrix.length; l++) {
				costs[k][l] = k == l ? 0 : 99999;
				nexts[k][l] = -1;
			}
		}
		
		for (Edge edge: edges) {
			costs[edge.i][edge.j] = edge.cost;
		}
		
		for (int k = adjacencyMatrix.length - 1; k >= 0; k--)
			for (int l = adjacencyMatrix.length - 1; l >= 0; l--)
				for (int m = adjacencyMatrix.length - 1; m >= 0; m--) {
					if (costs[l][k] + costs[k][m] < costs[l][m]) {
						costs[l][m] = costs[l][k] + costs[k][m];
						nexts[l][m] = k;
					}
				}

		if (costs[i][j] == 99999) {
			return new Pair<Integer, List<Integer>>(-1, result);
		}
		
		findCheapestPath(i, j, nexts, result);

		result.add(j);
		
		return new Pair<Integer, List<Integer>>(costs[i][j], result);
	}	
		
	public List<Integer> findCheapestPath(int u, int v, int[][] nexts, List<Integer> pathList)
	{
	    int intermediate = nexts[u][v];
	    
	    if (intermediate == -1) {
	        pathList.add(u);
	        return pathList;
	    } else {
	        findCheapestPath(u, intermediate, nexts, pathList);
	        findCheapestPath(intermediate, v, nexts, pathList);
	    }
	    
	    return pathList;
	}
	
	public Pair<Integer, List<Integer>> floydPath(int[][] costs, int[][] nexts, int i, int j, LinkedHashSet<Integer> path) {
		if (costs[i][j] == 99999)
			return new Pair<Integer, List<Integer>>(-1, new ArrayList<Integer>());
		
		int intermediate = nexts[i][j];
		
		if (intermediate == -1) {
			ArrayList<Integer> list = new ArrayList<Integer>(path);
			return new Pair<Integer, List<Integer>>(costs[i][j], list);
		}
		else {
			path.addAll(floydPath(costs, nexts, i, intermediate, path).getValue1());
			path.addAll(floydPath(costs, nexts, intermediate, j, path).getValue1());

			ArrayList<Integer> list = new ArrayList<Integer>(path);
			return new Pair<Integer, List<Integer>>(costs[i][j], list);
		}
	}
	
	public void addEdge(int i, int j) {
		adjacencyMatrix[i][j] = 1;
		edges.add(new Edge(i, j, 1));
	}
	
	public void addEdge(int i, int j, int cost) {
		adjacencyMatrix[i][j] = cost;
		edges.add(new Edge(i, j, cost));
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
