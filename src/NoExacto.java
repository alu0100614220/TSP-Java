import java.util.ArrayList;

public class NoExacto {
	public NoExacto(ArrayList<Nodo> tsp) {
		int coste = 0;
		ArrayList<Nodo> camino = new ArrayList<Nodo>();
		int minimo = 99999;
		Nodo nuevo = tsp.get(0);
		camino.add(nuevo);
		nuevo.setVisited(true);
		Nodo aux = null;
		for (int j = 0; j < tsp.size() - 1; j++) {
			minimo = 99999;
			for (int i = 0; i < nuevo.costes.size(); i++) {

				if (nuevo.costes.get(i) < minimo && nuevo.sucesores.get(i).isVisited() == false) {
					minimo = nuevo.costes.get(i);
					aux = nuevo.sucesores.get(i);
				}
			}

			coste += minimo;
			nuevo = aux;
			nuevo.setVisited(true);

			camino.add(nuevo);
		}
		camino.add(tsp.get(0));
		coste += nuevo.costes.get(0);
		System.out.println();
		System.out.println("------No exacto------");
		System.out.println("Camino minimo: " + camino);
		System.out.println("Coste: " + coste);
	}
}
