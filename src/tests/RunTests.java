package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import solutions.DisjointSetTests;
import utils.FileUtilities;

public class RunTests {

	private String dir;
	private Testable testable;
	
	private boolean ignorePos;

	public RunTests(Testable testable, String dir) {
		this.dir = "tests/" + dir;
		this.testable = testable;
		
		ignorePos = false;
	}

	public RunTests(Testable testable, String dir, boolean b) {
		this(testable, dir);
		
		ignorePos = b;
	}

	public void runCmd(String command) throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec(command);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String s;
		
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}
	}

	public void runTests() throws IOException, InterruptedException {
		File folder = new File(dir);
		File[] files = folder.listFiles();
		
		for (File file : files) {
			File outputFile = null;

			if (file.getName().endsWith(".in")) {
				outputFile = testable.generateOutput(file);
				
			
				System.out.println("Test " + file.getName() + "\n======================\n");
				
				String cmd;
				
				if (ignorePos) {
					cmd = "diff <(sort" + dir + "/" + outputFile.getName() + ") <(sort " + dir + "/" + 
							FileUtilities.stripExtension(outputFile.getName() + ")");
				} else {
					cmd = "diff " + dir + "/" + outputFile.getName() + " " + dir + "/" + 
						FileUtilities.stripExtension(outputFile.getName());
				}
				
				System.out.println("Comparing the two files using diff (" + cmd + ")");
				System.out.println("Output:");
				
				runCmd(cmd);
				
				System.out.println("\n======================");
			}
		}
	}
}
