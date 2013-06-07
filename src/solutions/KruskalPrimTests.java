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
		
		fileStream.println("-");
		
		DisjointSet[] sets = new DisjointSet[n];
		
		for (int i = 0; i < n; i++)	{
			sets[i] = new DisjointSet(i);
		}
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
						
			String[] split = line.split(" ");
			
			String command = split[0];
			int param1 = Integer.parseInt(split[1]);
			int param2 = Integer.parseInt(split[2]);

			if (command.equals("compare")) {
				fileStream.println(sets[param1].compare(sets[param2]));
			}
			else if (command.equals("union")) {
				fileStream.println("-");
				sets[param1].union(sets[param2]);
			}	
		}
		
		in.close();
		fileStream.close();
		
		return file;	
	}
	
	public static void main(String[] args) {
		System.out.println("CONJUNTOS\n======================\n\n");
		
		RunTests tests = new RunTests(new DisjointSetTests(), "conjuntos", true);
		
		try {
			tests.runTests();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
