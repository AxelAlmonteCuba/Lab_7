import java.util.Scanner;

public class BellmanFord
{
    private int distancias[];
    private int numVertices;
    public static final int VALOR_MAXIMO = 999;
 
    public BellmanFord(int numVertices)
    {
        this.numVertices = numVertices;
        distancias = new int[numVertices + 1];
    }
 
    public void EvaluacionBellmanFord(int origen, int matrizAdyacencia[][])
    {
        for (int node = 1; node <= numVertices; node++)
        {
            distancias[node] = VALOR_MAXIMO;
        }
 
        distancias[origen] = 0;
        for (int node = 1; node <= numVertices - 1; node++)
        {
            for (int nodoorigen = 1; nodoorigen <= numVertices; nodoorigen++)
            {
                for (int nododestino = 1; nododestino <= numVertices; nododestino++)
                {
                    if (matrizAdyacencia[nodoorigen][nododestino] != VALOR_MAXIMO)
                    {
                        if (distancias[nododestino] > distancias[nodoorigen] 
                                + matrizAdyacencia[nodoorigen][nododestino])
                            distancias[nododestino] = distancias[nodoorigen]
                                + matrizAdyacencia[nodoorigen][nododestino];
                    }
                }
            }
        }
 
        for (int nodoorigen = 1; nodoorigen <= numVertices; nodoorigen++)
        {
            for (int nododestino = 1; nododestino <= numVertices; nododestino++)
            {
                if (matrizAdyacencia[nodoorigen][nododestino] != VALOR_MAXIMO)
                {
                    if (distancias[nododestino] > distancias[nodoorigen]
                           + matrizAdyacencia[nodoorigen][nododestino])
                        System.out.println("El gráfico contiene un ciclo de borde negativo");
                }
            }
        }
 
        for (int vertice = 1; vertice <= numVertices; vertice++)
        {
            System.out.println("distancia de origen  " + origen + " a "
                      + vertice + " es " + distancias[vertice]);
        }
    }
 
    public static void main(String args[])
    {
        int numeroVertices = 0;
        int origen;
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("Ingrese el numero de vertices: ");
        numeroVertices = scanner.nextInt();
 
        int matrizAdyacencia[][] = new int[numeroVertices + 1][numeroVertices + 1];
        System.out.println("Ingrese la matriz de adyacencia: ");
        for (int nodoorigen = 1; nodoorigen <= numeroVertices; nodoorigen++)
        {
            for (int nododestino = 1; nododestino <= numeroVertices; nododestino++)
            {
                matrizAdyacencia[nodoorigen][nododestino] = scanner.nextInt();
 	        if (nodoorigen == nododestino)
                {
                    matrizAdyacencia[nodoorigen][nododestino] = 0;
                    continue;
                }
                if (matrizAdyacencia[nodoorigen][nododestino] == 0)
                {
                    matrizAdyacencia[nodoorigen][nododestino] = VALOR_MAXIMO;
                }
            }
        }
 
        System.out.println("Ingrece el vértice de origen");
        origen = scanner.nextInt();
 
        BellmanFord bellmanford = new BellmanFord(numeroVertices);
        bellmanford.EvaluacionBellmanFord(origen, matrizAdyacencia);
        scanner.close();	
    }
}
