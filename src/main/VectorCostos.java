package main;

import java.util.Map;

public class VectorCostos {
	
	private Integer[] costos; // Asumo que los nodos son numericos empezando desde el 0 el cual seria el nodo 1. //
	private String[] procedentes; // Refiere a que String1 procede de String2. // D->B significa que D procede de B, o sea, se llega a D pasando por B. //
	
	public VectorCostos(Integer cantNodos) // vecInicio es la primera fila de la matriz de adyacencia (MatrizAdyacencia). //
	{
		costos = new Integer[cantNodos];
		procedentes = new String[cantNodos];
		
	}
	
	
	public void cargarVector(Integer cantNodos, Integer[] vecInicio, String partida)
	{

		for(int i = 0; i<cantNodos; i++)
		{
			costos[i] = vecInicio[i];
			
			if(vecInicio[i]!=1000000 ) // && vecInicio[i]!=0  //
			{
				procedentes[i] = partida; // Digo que el nodo i+1 proviene del nodo 1 (el cual es el primero). //
			}
			else
			{
				procedentes[i] = "NADA";
			}
			
		}
	}
	
	
	public boolean actualizarCostos(VectorNodosSolucionados nodosSolucionados, Grafo grafo)
	{		
		Integer caminoMenosCostoso = 1000000;
		String nodoATomar = "-10"; // Le pongo ese -10 porque si no patalea el compilador el cual significa que en ningun momento se entro al if del while. //
		
		
		for(int i = 0; i<costos.length; i++)
		{
			Integer value = costos[i];
			if(value!=0 && value<caminoMenosCostoso && !nodosSolucionados.perteneceASolucion(String.valueOf(i+1)))
			{
				caminoMenosCostoso = value;
				nodoATomar = String.valueOf(i+1);
			}
		}
		
		
		if(!nodoATomar.equals("-10"))
		{
			// En este punto ya tengo cual es nodo que tengo que tomar junto con su costo (el cual es el menor de todos los costos que marca el Hash de "costos"). //
			
			nodosSolucionados.agregarNodo(nodoATomar); // Agrego el nodo de menor costo encontrado en el while de arriba. //
			
			// Por lo tanto, solo falta actualizar el vector de "costos" desde el nodo tomado. //
			
			Integer costoBase = costos[Integer.parseInt(nodoATomar)-1];
			
			for(int i = 0; i<grafo.obtenerLargoDegrafo(); i++)
			{
				if(grafo.esDistintoA1000000(Integer.parseInt(nodoATomar)-1, i) && !nodosSolucionados.perteneceASolucion(String.valueOf(i+1)) )  //  && !nodosSolucionados.perteneceASolucion(nodoATomar) //
				{
					Integer nuevoCosto = costoBase + grafo.obtenerValor(Integer.parseInt(nodoATomar)-1, i);
					
					if(nuevoCosto<costos[i]) // Si el nuevoCosto calculado para llegar al nodo "i" es menor al costo que marca el Hash de "costos" ... //
					{
						costos[i] = nuevoCosto; // ...reemplazo el viejo costo del Hash de "costos" por el nuevo costo.
						procedentes[i] = String.valueOf(Integer.parseInt(nodoATomar));
					}
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	
	public String obtenerCaminoMasCorto(String inicio, String destino)
	{
		String[] aux = new String[costos.length];
		aux[0] = destino; 
		aux[1] = procedentes[Integer.parseInt(destino)-1]; // Si el hash de "destino" no existe, el get devuelve null. //
		if(aux[0].equals(aux[1])) // Hago este if porque si me llegase a preguntar por el camino de 1 a 1 y no hago ésto, el programa me devolveria "1 1". Entonces, con ésto, la devolucion es "1".
		{
			String caminoFinal = aux[0];
			return caminoFinal;
		}
		
		if(aux[1]!=null)
		{
			int i;
			for(i = 2; i<costos.length; i++)
			{
				if( !aux[i-1].equals("NADA") && !procedentes[Integer.parseInt(aux[i-1])-1].equals(inicio) )
				{
					aux[i] = procedentes[Integer.parseInt(aux[i-1])-1];
				}
				else
				{
					if(!aux[i-1].equals(inicio))
					{
						aux[i] = inicio;
					}					
					break;
				}
			}
			
			if(!aux[i-1].equals("NADA"))
			{
				int indexAux = aux.length-1;
				//ArrayList<String> caminoFinal = new ArrayList<String>();
				String caminoFinal="";
				for(int j = 0; j<aux.length; j++)
				{
					if(aux[indexAux]!=null)
					{
						//caminoFinal.add(aux[indexAux]);
						if(caminoFinal!="")
						{
							caminoFinal = caminoFinal+" "+aux[indexAux];
						}
						else
						{
							caminoFinal = aux[indexAux];
						}
						
					}
					indexAux--;
				}
				
				return caminoFinal;
			}			
		}
		
		//ArrayList<String> caminoFinal = new ArrayList<String>(1);
		String caminoFinal;
		//caminoFinal.add("No existe tal camino");
		caminoFinal = "No existe tal camino";
		return caminoFinal;
	}
	
	
	public Integer obtenerCosto(String destino)
	{
		return costos[ Integer.parseInt(destino)-1 ];
	}

}
