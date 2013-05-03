import java.util.Scanner;


public class DisjointSet {
	private int representative;
	private int key;

	public DisjointSet(int key) {
		setRepresentative(key);
		setKey(key);
	}
	
	public DisjointSet(int representative, int key) {
		setRepresentative(representative);
		setKey(key);
	}
	
	public void union(DisjointSet other) {
		setRepresentative(other.getRepresentative());
	}
	
	public boolean compare(DisjointSet other) {
		return this.getRepresentative() == other.getRepresentative();
	}
	
	public int getRepresentative() {
		return representative;
	}

	public void setRepresentative(int representative) {
		this.representative = representative;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
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
