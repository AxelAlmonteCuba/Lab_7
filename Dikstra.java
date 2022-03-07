import java.util.*;

public class Grafo {
	private int distancias[];
	private Set<Integer> settled;
	private PriorityQueue<Node> prioritys;
	private int Vertices;
	List<List<Node> > adjacencia;

	public Grafo(int V)
	{

		this.Vertices = V;
		distancias = new int[V];
		settled = new HashSet<Integer>();
		prioritys = new PriorityQueue<Node>(V, new Node());
	}

	public void dijkstra(List<List<Node> > adj, int src)
	{
		this.adjacencia = adj;

		for (int i = 0; i < Vertices; i++)
			distancias[i] = Integer.MAX_VALUE;

		prioritys.add(new Node(src, 0));

		distancias[src] = 0;

		while (settled.size() != Vertices) {

			if (prioritys.isEmpty())
				return;

			int u = prioritys.remove().node;

			if (settled.contains(u))
				continue;
			settled.add(u);

			e_vecinos(u);
		}
	}
	
	private void e_vecinos(int u)
	{

		int arista_distancia = -1;
		int nueva_distancia = -1;

		for (int i = 0; i < adjacencia.get(u).size(); i++) {
			Node v = adjacencia.get(u).get(i);

			if (!settled.contains(v.node)) {
				arista_distancia = v.costo;
				nueva_distancia = distancias[u] + arista_distancia;

				if (nueva_distancia < distancias[v.node])
					distancias[v.node] = nueva_distancia;

				prioritys.add(new Node(v.node, distancias[v.node]));
			}
		}
	}

	public static void main(String arg[])
	{

		int Vertices = 5;
		int source = 0;

		List<List<Node> > adjacencia
			= new ArrayList<List<Node> >();

		for (int i = 0; i < Vertices; i++) {
			List<Node> item = new ArrayList<Node>();
			adjacencia.add(item);
		}

		adjacencia.get(0).add(new Node(1, 9));
		adjacencia.get(0).add(new Node(2, 6));
		adjacencia.get(0).add(new Node(3, 5));
		adjacencia.get(0).add(new Node(4, 3));

		adjacencia.get(2).add(new Node(1, 2));
		adjacencia.get(2).add(new Node(3, 4));

		Grafo dpq = new Grafo(Vertices);
		dpq.dijkstra(adjacencia, source);

		System.out.println("El camino acortado desde el nodo :");

		for (int i = 0; i < dpq.distancias.length; i++)
			System.out.println(source + " a " + i + " es " + dpq.distancias[i]);
	}
}

class Node implements Comparator<Node> {

	public int node;
	public int costo;

	public Node() {}

	public Node(int node, int cost)
	{

		this.node = node;
		this.costo = cost;
	}

	@Override public int compare(Node node1, Node node2)
	{

		if (node1.costo < node2.costo)
			return -1;

		if (node1.costo > node2.costo)
			return 1;

		return 0;
	}
}
