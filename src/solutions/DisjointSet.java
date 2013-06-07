package solutions;

import java.util.ArrayList;


public class DisjointSet {
	private DisjointSet representative;
	private int key;
	private int order;

	public DisjointSet(int key) {
		setRepresentative(this);
		setKey(key);
	}
	
	public DisjointSet(DisjointSet representative, int key) {
		setRepresentative(representative);
		setKey(key);
	}
	
	public void union(DisjointSet other) {
		if (this.getOrder() > other.getOrder() ) {
			this.getRepresentative().setRepresentative(other.getRepresentative());
			other.getRepresentative().setOrder(this.getOrder());			
		}
		else{
			other.getRepresentative().setRepresentative(this.getRepresentative());
			this.getRepresentative().setOrder(other.getOrder());
		}		
		
		if (this.getOrder() == other.getOrder() ){
			this.getRepresentative().increaseOrder();
			other.getRepresentative().increaseOrder();
		}
	}
	
	public boolean compare(DisjointSet other) {
		return this.getRepresentative().getKey() == other.getRepresentative().getKey();
	}
	
	public DisjointSet getRepresentative() {
		DisjointSet lastRep = this;
		
		if (representative.getKey() != lastRep.getKey()) {
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

	public int getOrder() {
		if (this.getRepresentative() == this){
			return this.order;
		}
		else return this.getRepresentative().getOrder();		
	}

	public void setOrder(int order){
		this.order=order;
	}
	
	public void increaseOrder() {
		this.order++;
	}
}
