import java.util.ArrayList;
import java.util.Hashtable;

public class Nodo {
	public ArrayList<Nodo> sucesores = new ArrayList();
	public ArrayList<Integer> costes = new ArrayList();
	public Hashtable<Nodo,Integer> tabla = new Hashtable();
	private int number;
	private boolean visited = false;

	public Nodo(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String toString() {
		return "[" + this.number + "]";
	}

	public void showSucesores() {
		System.out.println(" Costes: ");
		for (int i = 0; i < this.sucesores.size(); i++) {
			System.out.print("Nodo: " + this.sucesores.get(i));
			System.out.println(" (" + this.costes.get(i) + ")");
		}
	}

	
}
