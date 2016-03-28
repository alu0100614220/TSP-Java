import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TSP {
	ArrayList<Nodo> tsp = new ArrayList<Nodo>();
	private BufferedReader br;

	public TSP(int size) {
		for (int i = 0; i < size; i++) {
			tsp.add(new Nodo(i + 1));
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i != j) {

					int coste = (int) (Math.random() * 10) + 1;
					tsp.get(i).sucesores.add(tsp.get(j));
					tsp.get(i).costes.add(coste);
					tsp.get(i).tabla.put(tsp.get(j), coste);

				}
			}
		}
		//this.showMatrix();
	}

	public TSP() {
		try {
			br = new BufferedReader(new FileReader("tsp1.txt"));
			String linea = br.readLine();
			int N = Integer.parseInt(linea);
			for (int i = 0; i < 5; i++) {
				tsp.add(new Nodo(i + 1));
			}
			linea = br.readLine();
			while (linea != null) {
				String[] aux = linea.split(" ");
				int nodo = Integer.parseInt(aux[0]);
				for (int i = 0; i < tsp.size(); i++) {
					if (nodo == tsp.get(i).getNumber()) {
						int sucesor = Integer.parseInt(aux[1]);
						int coste = Integer.parseInt(aux[2]);
						tsp.get(i).sucesores.add(tsp.get(sucesor - 1));
						tsp.get(i).costes.add(coste);
						tsp.get(i).tabla.put(tsp.get(sucesor - 1), coste);
						tsp.get(sucesor - 1).sucesores.add(tsp.get(i));
						tsp.get(sucesor - 1).costes.add(coste);
						tsp.get(sucesor - 1).tabla.put(tsp.get(i), coste);
					}
				}
				linea = br.readLine();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.showMatrix();
		Timer timer = new Timer();
	}

	public void showMatrix() {
		System.out.println("--TSP--");
		System.out.print("N");
		for (int i = 1; i <= tsp.size(); i++) {
			System.out.print("\t" + i);
		}
		System.out.println();
		for (int i = 1; i <= tsp.size(); i++) {
			System.out.print("---------");
		}
		System.out.println();
		for (int i = 0; i < tsp.size(); i++) {
			System.out.print((i + 1) + "\t");
			for (int j = 0; j < tsp.size() - 1; j++) {
				if (i == j) {
					System.out.print("0\t" + tsp.get(i).costes.get(j) + "\t");
				} else {
					System.out.print(tsp.get(i).costes.get(j) + "\t");

				}
				if (i == tsp.size() - 1 && j == tsp.size() - 2) {
					System.out.print("0");
				}
			}
			System.out.println();
		}

	}

	public static void main(String[] args) {
		int size = 4;
		for (int i = 1; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				TSP travel = new TSP(size * i);
				Timer timer = new Timer();

				timer.start();
				NoExacto noexacto = new NoExacto(travel.tsp);
				timer.stop();
				System.out.println(timer.getTiempo());

				timer.start();
				FuerzaBruta bruteForce = new FuerzaBruta(travel.tsp);
				timer.stop();
				System.out.println(timer.getTiempo());

				timer.start();
				ProgramacionDinamica dinamica = new ProgramacionDinamica(travel.tsp);
				timer.stop();
				System.out.println(timer.getTiempo());
			}
		}

	}
}
