import java.util.ArrayList;

public class ProgramacionDinamica {
	int size, coste;
	final int MAXVALUE = Integer.MAX_VALUE;
	ArrayList<Nodo> tsp;
	ArrayList<Nodo> camino = new ArrayList();

	public ProgramacionDinamica(ArrayList<Nodo> tsp) {
		this.size = tsp.size();
		this.tsp = tsp;
		for (int i = 1; i < size; i++) {
			camino.add(new Nodo(0));
		}
		eval();
	}

	public int calculaCoste(int nodoActual, int setActual[], int setSize) {
		if (setSize == 0)
			return this.tsp.get(nodoActual).tabla.get(this.tsp.get(0));
		int min = MAXVALUE;
		int setToBePassedOnToNextCallOfCOST[] = new int[size - 1];
		for (int i = 0; i < setSize; i++) {
			int k = 0;// initialise new set
			for (int j = 0; j < setSize; j++) {
				if (setActual[i] != setActual[j])
					setToBePassedOnToNextCallOfCOST[k++] = setActual[j];
			}
			int temp = calculaCoste(setActual[i], setToBePassedOnToNextCallOfCOST, setSize - 1);
			if (this.tsp.get(nodoActual).tabla.get(this.tsp.get(setActual[i])) + temp < min) {
				min = this.tsp.get(nodoActual).tabla.get(this.tsp.get(setActual[i])) + temp;
			}
		}
		return min;
	}

	public int MIN(int nodoActual, int auxSet[], int setSize) {
		if (setSize == 0)
			return this.tsp.get(nodoActual).tabla.get(this.tsp.get(0));
		int min = MAXVALUE, minindex = 0;
		int proximoSet[] = new int[size - 1];
		for (int i = 0; i < setSize; i++) {
			int k = 0;
			for (int j = 0; j < setSize; j++) {
				if (auxSet[i] != auxSet[j])
					proximoSet[k++] = auxSet[j];
			}
			int temp = calculaCoste(auxSet[i], proximoSet, setSize - 1);
			if (this.tsp.get(nodoActual).tabla.get(this.tsp.get(auxSet[i])) + temp < min) {
				min = this.tsp.get(nodoActual).tabla.get(this.tsp.get(auxSet[i])) + temp;
				minindex = auxSet[i];
			}
		}
		return minindex;
	}

	public void eval() {
		int setInicial[] = new int[size - 1];
		for (int i = 1; i < size; i++)
			setInicial[i - 1] = i;
		creaTour();
	}

	public void creaTour() {
		int setPrevio[] = new int[size - 1];
		int setSig[] = new int[size - 2];
		for (int i = 1; i < size; i++)
			setPrevio[i - 1] = i;
		int setSize = size - 1;
		camino.set(0, tsp.get(MIN(0, setPrevio, setSize)));
		for (int i = 1; i < size - 1; i++) {
			int k = 0;
			for (int j = 0; j < setSize; j++) {
				if (camino.get(i - 1).getNumber() - 1 != setPrevio[j])
					setSig[k++] = setPrevio[j];
			}
			setSize--;
			camino.set(i, tsp.get(MIN(camino.get(i - 1).getNumber() - 1, setSig, setSize)));
			for (int j = 0; j < setSize; j++)
				setPrevio[j] = setSig[j];
		}

		show();
	}

	public void show() {

		System.out.println();
		camino.add(0, tsp.get(0));
		camino.add(tsp.get(0));

		for (int i = 1; i < camino.size(); i++) {
			Nodo nodo = camino.get(i - 1);
			Nodo nodo2 = camino.get(i);

			coste += nodo.tabla.get(nodo2);
		}

		System.out.println();
		System.out.println("------Programacion Dinamica------");
		System.out.println("Camino minimo: " + camino);
		System.out.println("Coste: " + coste);

	}
}
