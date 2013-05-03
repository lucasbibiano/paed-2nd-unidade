import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class DisjointSet {
	private DisjointSet representative;
	private int key;

	public DisjointSet(int key) {
		setRepresentative(this);
		setKey(key);
	}
	
	public DisjointSet(DisjointSet representative, int key) {
		setRepresentative(representative);
		setKey(key);
	}
	
	public void union(DisjointSet other) {
		this.getRepresentative().setRepresentative(other.getRepresentative());
	}
	
	public boolean compare(DisjointSet other) {
		return this.getRepresentative().getKey() == other.getRepresentative().getKey();
	}
	
	public DisjointSet getRepresentative() {
		DisjointSet lastRep = this;
				
		while (representative.getKey() != lastRep.getKey()) {
			lastRep = representative;
			setRepresentative(representative.getRepresentative());
		}
		
		return representative;
	}

	public void setRepresentative(DisjointSet representative) {
		this.representative = representative;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public static void main(String[] args) {
		Scanner in = null;
		try {
			in = new Scanner(new File("tests/conjuntos/conjuntos1.in"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = in.nextInt();
		
		PrintStream out = System.out;
		
		System.out.println("-");
		in.nextLine();
		
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
				System.out.println(sets[param1].compare(sets[param2]));
			}
			else if (command.equals("union")) {
				System.out.println("-");
				sets[param1].union(sets[param2]);
			}	
		}
		
		in.close();
	}
}
