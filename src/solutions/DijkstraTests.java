package solutions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.javatuples.Pair;

import tests.RunTests;
import tests.Testable;
import utils.FileUtilities;

public class DijkstraTests implements Testable {

	@Override
	public File generateOutput(File input) throws IOException {
		Scanner in = new Scanner(input);
		
		File file = new File("tests/dijkstra/" + 
			FileUtilities.stripExtension(input.getName()) + ".out.ours");
		
		PrintStream fileStream = new PrintStream(new BufferedOutputStream(
			new FileOutputStream(file)));
		
		int n = in.nextInt();
		in.nextLine();
		
		fileStream.println("-");
		
		GraphListRevenge graph = new GraphListRevenge(n);
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
						
			String[] split = line.split(" ");
			
			String command = split[0];
			int param1 = Integer.parseInt(split[1]);
			int param2 = Integer.parseInt(split[2]);

			if (command.equals("edge")) {
				fileStream.println("-");
				graph.addEdge(param1, param2);
				graph.addEdge(param2, param1);
			}
			else if (command.equals("shortest")) {
				Pair<Integer, List<Integer>> pair = graph.dijkstra(param1, param2);
				
				if (pair.getValue1().isEmpty()) {
					fileStream.print("No path");
				}
				else {
					String prefix = "";
					for (int i: pair.getValue1()) {
						fileStream.print(prefix);
						prefix = " ";
						fileStream.print(i);
					}
					
					fileStream.print(" " + pair.getValue0());
				}

				fileStream.println();
			}	
			else if (command.equals("path")) {
				Pair<Integer, List<Integer>> pair = graph.path(param1, param2);
				
				if (pair.getValue1().isEmpty()) {
					fileStream.print("No path");
				}
				else {
					String prefix = "";
					for (int i: pair.getValue1()) {
						fileStream.print(prefix);
						prefix = " ";
						fileStream.print(i);
					}
					
					fileStream.print(" " + pair.getValue0());
				}

				fileStream.println();
			}
		}
		
		in.close();
		fileStream.close();
		
		return file;	
	}
	
	public static void main(String[] args) {
		System.out.println("GRAFOS\n======================\n\n");
		
		RunTests tests = new RunTests(new GraphTests(), "dijkstra");
		
		try {
			tests.runTests();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
