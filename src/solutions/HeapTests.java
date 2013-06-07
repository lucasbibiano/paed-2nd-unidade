package solutions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Scanner;

import org.javatuples.Pair;

import tests.RunTests;
import tests.Testable;
import utils.FileUtilities;

public class HeapTests implements Testable {
	
	@Override
	public File generateOutput(File input) throws IOException {
		Scanner in = new Scanner(input);
		
		File file = new File("tests/filaprioridade/" + 
			FileUtilities.stripExtension(input.getName()) + ".out.ours");
		
		PrintStream fileStream = new PrintStream(new BufferedOutputStream(
			new FileOutputStream(file)));
		
		String type_heap = in.nextLine();
				
		fileStream.println("-");
		
		HeapRevenge<Integer> heap;
		
		if(type_heap.equals("MIN"))
			heap = new HeapMinRevenge<Integer>();
		else
			heap = new HeapMaxRevenge<Integer>();
		
		heap.setAcceptDuplicates(false);
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			
			String[] split = line.split(" ");
			
			String command = split[0];
			int param1 = 0;
			int param2 = 0;
			
			if(Array.getLength(split) > 1){
				param1 = Integer.parseInt(split[1]);
				param2 = Integer.parseInt(split[2]);
			}
			
			if (command.equals("insert")) {
				if (heap.insert(param1, param2))
					fileStream.println("-");
				else
					fileStream.println("notinserted");
			}
			else if (command.equals("extract")) {
				Pair<Integer, Integer> extract = heap.extract();
				
				if(extract == null)
					fileStream.println("empty");
				else
					fileStream.println(extract.getValue1() + " " + extract.getValue0());
			}
			else if (command.equals("decrease") || command.equals("increase")) {
				if(heap.increasePriority(param1, param2))
					fileStream.println("-");
				else
					fileStream.println("notupdated");
			}
		}

		in.close();
		fileStream.close();
		
		
		return file;	
	}
	
	public static void main(String[] args) {
		System.out.println("HEAP\n======================\n\n");
		
		RunTests tests = new RunTests(new HeapTests(), "filaprioridade");
		
		try {
			tests.runTests();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
