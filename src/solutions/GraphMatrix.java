package solutions;

import java.util.Queue;

public class GraphMatrix {
	private int n;
	
	int[][] nodes = new int[n][n];
	
	int predecessors[];
	
	public GraphMatrix(int numberOfNodes) {
		this.n=numberOfNodes;
	}

	public void addEdge(int i, int j, int weight ) {
		this.nodes[i][j]=weight;
	}
	
	public String shortest(int i, int j) {

		Queue<Integer> adjacents;
		
		return "";
	}
	
	public String path(int i, int j) {
		return null;
	}

	public boolean auxPath(int i, int j) {	
		return true;
	}
	
	private void printByPredecessor(int j) {

	}
	
	public void printPred() {

	}
	
	
}
