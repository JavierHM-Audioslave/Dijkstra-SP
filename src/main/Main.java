package main;

import java.util.Scanner;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc;
		File archIn;
		
		try
		{
			archIn = new File("in9.txt");
			sc = new Scanner(archIn);
			Integer cantNodos = sc.nextInt();
			Integer cantAristas = sc.nextInt();
			String nodoInicio = String.valueOf(sc.nextInt());
			Grafo grafo = new Grafo(cantNodos, cantAristas);
			grafo.cargarGrafoNodirigido(sc);
			Dijkstra dijkstra = new Dijkstra(nodoInicio, grafo);
			String[] res = dijkstra.resolver();
			for(int i = 0; i<res.length; i++)
			{
				System.out.println(res[i]);
			}
			try
			{
				sc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}

	}

}
