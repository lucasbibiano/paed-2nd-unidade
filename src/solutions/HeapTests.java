package solutions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Scanner;

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
		
		Heap heap;
		
		if(type_heap == "MIN")
			heap = new HeapMin();
		else
			heap = new HeapMax();
			
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
				heap.insert(new Node<Integer>(param1, param2));
				fileStream.println("-");
			}
			else if (command.equals("extract")) {
				Node extract = heap.extract();
				if(extract == null)
					fileStream.println("empty");
				else
					fileStream.println(extract.getId() + " " + extract.getValue());
			}
			else if (command.equals("decrease") || command.equals("increase")) {
				Node update = heap.update(param1, param2);
				if(update == null)
					fileStream.println("notupdated");
				else
					fileStream.println(update.getId() + " " + update.getValue());
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
