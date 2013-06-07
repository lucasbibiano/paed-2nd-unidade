package solutions;

public class teste1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DisjointSet[] conjuntos = new DisjointSet[7];
		
		for (int i = 0; i < 7; i++)
			conjuntos[i] = new DisjointSet(i);
	}

}
