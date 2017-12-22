package main;

public class Dijkstra {
	
	private String partida;
	private Grafo grafo;
	private Integer cantNodos;
	private VectorCostos costos;
	private VectorNodosSolucionados nodosSolucionados;
	
	public Dijkstra(String nodoInicio, Grafo grafo)
	{
		partida = nodoInicio;
		this.grafo = grafo;
		cantNodos = grafo.obtenerCantNodos();
		costos = new VectorCostos(grafo.obtenerCantNodos());
		nodosSolucionados = new VectorNodosSolucionados(cantNodos);
		
		costos.cargarVector(cantNodos, grafo.obtenerFila(Integer.parseInt(partida)-1), partida);
	}
	
	
	public String[] resolver()
	{
		String[] dev = new String[cantNodos];
		
		nodosSolucionados.agregarNodo(partida); // Agrego el nodo de partida a "nodosSolucionados" ya que cuando instancie el objeto "costos", esa instanciacion ya incluye el costo de ir a los nodos adyacentes al nodo "partida". //
		boolean seguir = true;
		for(int i = 0 ; i<nodosSolucionados.tamañoDeVectorSolucionados()-1; i++)
		{
			seguir = costos.actualizarCostos(nodosSolucionados, grafo);
			if(seguir==false)
			{
				break;
			}
		}
		
		for(int j = 0; j<nodosSolucionados.tamañoDeVectorSolucionados(); j++)
		{
			String llegada=String.valueOf(j+1);
			
			Integer aux = costos.obtenerCosto(llegada);
			if(aux!=1000000)
			{
				dev[j] = "Destino a "+llegada+". Costo: "+aux+". Camino mas corto, es: "+costos.obtenerCaminoMasCorto(partida, llegada);
			}
			else
			{
				dev[j] = "Destino a "+llegada+". Costo: INEXISTENTE"+". Camino mas corto, es: INEXISTENTE.";  	//		costos.obtenerCaminoMasCorto(partida, llegada);
			}
			
		}
		
		return dev;
	}

}
