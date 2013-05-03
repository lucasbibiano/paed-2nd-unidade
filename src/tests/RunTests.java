package tests;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RunTests {
	
	private String dir;
	private StringBuffer out;
	
	public RunTests(Testable testable, String dir) {
		this.dir = dir;
	}
	
	public void appendToOutput(String s) {
		out.append(s);
	}
	
	public void outputFile() throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dir + "/"));
		out.close();
	}
	
	public void runTests() {
		File folder = new File(dir);
		File[] files = folder.listFiles();
		
		for (File file: files) {
			
		}
	}
}
