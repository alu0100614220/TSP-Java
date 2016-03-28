import java.util.ArrayList;

public class FuerzaBruta {
	public ArrayList<ArrayList<Nodo>> caminos = new ArrayList();
	public ArrayList<Nodo> tsp;
	private int coste = 0;
	private int opcion = -1;
	int minimo = 99999;

	public FuerzaBruta(ArrayList<Nodo> tsp) {
		int coste = 0;

		this.tsp = tsp;
		int[] permutame = new int[tsp.size() - 1];
		String permutacion = "";
		for (int i = 1; i < tsp.size(); i++) {
			permutame[i - 1] = i + 1;
		}

		this.permute(permutame, 0);
		// permutation(permutacion);
		calcula();
	}

	private void calcula() {
		for (int i = 0; i < caminos.size(); i++) {
			coste = 0;
			for (int j = 1; j < caminos.get(i).size(); j++) {
				Nodo nodo = caminos.get(i).get(j - 1);
				Nodo nodo2 = caminos.get(i).get(j);
				coste += nodo.tabla.get(nodo2);

			}
			if (coste < minimo) {
				minimo = coste;
				this.setOpcion(i);
			}
		}
		System.out.println();
		System.out.println("------Fuerza Bruta------");
		System.out.println("Camino minimo: " + caminos.get(this.mejorCamino()));
		System.out.println("Coste: " + minimo);
	}

	public void permute(int[] arr) {
		permute(arr, 0);
	}

	private void permute(int[] arr, int index) {
		if (index >= arr.length - 1) {

			ArrayList<Nodo> aux = new ArrayList<Nodo>();
			aux.add(this.tsp.get(0));
			for (int i = 0; i < arr.length; i++) {
				aux.add(this.tsp.get(arr[i] - 1));
			}
			aux.add(tsp.get(0));
			caminos.add(aux);
			return;
		}

		for (int i = index; i < arr.length; i++) {
			int swap = arr[index];
			arr[index] = arr[i];
			arr[i] = swap;

			permute(arr, index + 1);

			swap = arr[index];
			arr[index] = arr[i];
			arr[i] = swap;
		}
	}

	public int mejorCamino() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}
}
