package solutions;


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
}
