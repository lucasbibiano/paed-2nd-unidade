package solutions;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
	private Node[] nodes;
	
	private StringBuilder buffer;
	private boolean[] visited;
	
	public Graph(int numberOfNodes) {
		nodes = new Node[numberOfNodes];
		
		for (int i = 0; i < numberOfNodes; i++)
			nodes[i] = new Node(i);
		
		buffer = new StringBuilder();
	}
	
	public void addEdge(int i, int j) {
		nodes[i].neighbors.add(nodes[j]);
	}
	
	public String shortest(int i, int j) {
		visited = new boolean[nodes.length];
		
		for (int k = 0; k < nodes.length; k++) {
			nodes[k].predecessor = k;
			nodes[k].cost = 0;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(nodes[i]);
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			visited[node.key] = true;
			
			if (node.key == j) {
				buffer.setLength(0);
				printByPredecessor(j);
				buffer.append("=> cost = " + node.cost);
				return buffer.toString().trim();
			}
			
			for (Node n: node.neighbors) {
				if (visited[n.key])
					continue;
				
				n.predecessor = node.key;
				n.cost = node.cost + 1;
				queue.add(n);
			}
		}
		
	return "No path";
	}
	
	public String path(int i, int j) {
		visited = new boolean[nodes.length];
		
		for (int k = 0; k < nodes.length; k++) {
			nodes[k].predecessor = k;
		}
		
		buffer.setLength(0);
		
		if (auxPath(i, j))
			printByPredecessor(j);
		else
			buffer.append("No path");
		
		return buffer.toString().trim();
	}
	
	public boolean auxPath(int i, int j) {	
		visited[i] = true;
		
		if (i == j) {
			return true;
		}
		
		boolean found = true;
		
		for (Node n: nodes[i].neighbors) {
			if (visited[n.key])
				continue;
			
			n.predecessor = i;			
			return found && auxPath(n.key, j);
		}
		
		return false;
	}
	
	private void printByPredecessor(int j) {
		if (j == nodes[j].predecessor) {
			buffer.append(nodes[j].key + " ");
			return;
		}
		
		printByPredecessor(nodes[j].predecessor);
		buffer.append(nodes[j].key + " ");
	}
	
	public void printPred() {
		for (int i = 0; i < nodes.length; i++) {
			System.out.print(nodes[i].predecessor + " ");
		}
		
		System.out.println();
	}

	private class Node {
		private int key;
		private int predecessor;
		private int cost;
		private List<Node> neighbors;
		
		public Node(int key) {
			this.key = key;
			this.predecessor = key;
			neighbors = new LinkedList<Node>();
		}
	}
}
