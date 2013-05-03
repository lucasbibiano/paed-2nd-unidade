package tests;
import java.io.File;
import java.io.IOException;


public interface Testable {
	public File generateOutput(File input) throws IOException;
}
