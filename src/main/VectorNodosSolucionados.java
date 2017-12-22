package main;

public class VectorNodosSolucionados {
	
	private String[] vecSolucionados;
	private int posicion;
	
	public VectorNodosSolucionados(Integer cantNodos)
	{
		vecSolucionados = new String[cantNodos];
		posicion = 0;
	}
	
	public void agregarNodo(String nodo)
	{
		vecSolucionados[posicion] = nodo;
		posicion++;
	}
	
	public boolean perteneceASolucion(String nodo)
	{
		for(int i = 0; i<posicion; i++)
		{
			if(vecSolucionados[i].equals(nodo))
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	public Integer tamañoDeVectorSolucionados()
	{
		return vecSolucionados.length;
	}
	
	
	public void resetearAtributos()
	{
		for(int i = 0; i<vecSolucionados.length; i++)
		{
			vecSolucionados[i] = "";
		}
		
		posicion = 0;
	}

}
