
import java.util.*; 
import java.lang.*;
import java.io.*;


class floydWarshal
{
	final static int INF = 99999, V = 4;

	void floydWarshall(int grafo[][])
	{
		int distancias[][] = new int[V][V];
		int i, j, k;

		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				distancias[i][j] = grafo[i][j];

			for (k = 0; k < V; k++)
		{
					for (i = 0; i < V; i++)
			{
				for (j = 0; j < V; j++)
				{
					if (distancias[i][k] + distancias[k][j] < distancias[i][j])
						distancias[i][j] = distancias[i][k] + distancias[k][j];
				}
			}
		}

		printSolution(distancias);
	}

	void printSolution(int distancias[][])
	{
		System.out.println("La siguiente matriz muestra las distancias ma corta entre cada par de vértices");
		for (int i=0; i<V; ++i)
		{
			for (int j=0; j<V; ++j)
			{
				if (distancias[i][j]==INF)
					System.out.print("INF ");
				else
					System.out.print(distancias[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void main (String[] args)
	{
		/* 
		(0)------->(3)
		|		 /|\
		5 |		 |
		|		 | 1
		\|/		 |
		(1)------->(2)
		3		 */
		int graph[][] = { {0, 5, INF, 10},
						{INF, 0, 3, INF},
						{INF, INF, 0, 1},
						{INF, INF, INF, 0}
						};
		floydWarshal a = new floydWarshal();

		a.floydWarshall(graph);
	}
}

