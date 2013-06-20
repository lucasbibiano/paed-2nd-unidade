package solutions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import solutions.GraphMatrixRevenge.Edge;
import tests.RunTests;
import tests.Testable;
import utils.FileUtilities;

public class KruskalPrimTests implements Testable {

	@Override
	public File generateOutput(File input) throws IOException {
		Scanner in = new Scanner(input);
		
		File file = new File("tests/kruskalprim/" + 
			FileUtilities.stripExtension(input.getName()) + ".out.ours");
		
		PrintStream fileStream = new PrintStream(new BufferedOutputStream(
			new FileOutputStream(file)));
		
		int n = in.nextInt();
		in.nextLine();
				
		GraphMatrixRevenge graph = new GraphMatrixRevenge(n);
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
						
			String[] split = line.split(" ");
			
			String command = split[0];

			if (command.equals("edge")) {
				int param1 = Integer.parseInt(split[1]);
				int param2 = Integer.parseInt(split[2]);				
				int param3 = Integer.parseInt(split[3]);

				graph.addEdge(param1, param2, param3);
				graph.addEdge(param2, param1, param3);
			}
			else if (command.equals("kruskal")) {
				int totalCost = 0;
				for (Edge edge: graph.kruskal()) {
					totalCost += edge.cost;
					fileStream.println(edge.i + " " + edge.j);
				}
				
				fileStream.println(totalCost);
			}
			else if (command.equals("prim")) {
				int totalCost = 0;
				for (Edge edge: graph.prim()) {
					totalCost += edge.cost;
					fileStream.println(edge.i + " " + edge.j);
				}
				
				fileStream.println(totalCost);
			}	
		}
		
		in.close();
		fileStream.close();
		
		return file;	
	}
	
	public static void main(String[] args) {
		System.out.println("KRUSKAL E PRIM\n======================\n\n");
		
		RunTests tests = new RunTests(new KruskalPrimTests(), "kruskalprim", true);
		
		try {
			tests.runTests();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
