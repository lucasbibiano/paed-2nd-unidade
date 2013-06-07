package solutions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import tests.RunTests;
import tests.Testable;
import utils.FileUtilities;

public class GraphTestsOld implements Testable {

	@Override
	public File generateOutput(File input) throws IOException {
		Scanner in = new Scanner(input);
		
		File file = new File("tests/grafos/" + 
			FileUtilities.stripExtension(input.getName()) + ".out.ours");
		
		PrintStream fileStream = new PrintStream(new BufferedOutputStream(
			new FileOutputStream(file)));
		
		int n = in.nextInt();
		in.nextLine();
		
		fileStream.println("-");
		
		Graph graph = new Graph(n);
		
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
				fileStream.println(graph.shortest(param1, param2));
			}	
			else if (command.equals("path")) {
				fileStream.println(graph.path(param1, param2));
			}
		}
		
		in.close();
		fileStream.close();
		
		return file;	
	}
	
	public static void main(String[] args) {
		System.out.println("GRAFOS\n======================\n\n");
		
		RunTests tests = new RunTests(new GraphTestsOld(), "grafos");
		
		try {
			tests.runTests();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
