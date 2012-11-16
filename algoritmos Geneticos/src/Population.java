import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Population {

	private int maxRandom, maxVueltas;
	private double maximoValorFormula;//Se aplica la formula a 50 que es el valor maximo
	private int tamanioPoblacion;
	private ArrayList<Individuo> poblacion;
	
	public Population(){
		maxVueltas= 50;
		maxRandom=50;
		maximoValorFormula=135096;
		poblacion = new ArrayList<Individuo>();
	}
	
	public int maximoDeVueltas()
	{
		return maxVueltas;
	}
	public void generarPoblacion (int tamanio)
	{
		tamanioPoblacion=tamanio;
		for(int x=0; x<tamanio; x++)
		{
			this.agregarIndividuo();
		}
		//System.out.println("#######################");
		//System.out.println(poblacion.size());
		//for(int i=0; i<poblacion.size(); i++)
			//System.out.println(poblacion.get(i).getValor());
		//System.out.println("#######################");
	}
	
	public void agregarIndividuo()
	{
		double valorRam = (double) Math.round((Math.random()*maxRandom)*100)/100.0d;
		Individuo individuoAgrego = new Individuo(valorRam);
		this.poblacion.add(individuoAgrego);
	}
	
	public void obtenerResultadoFormula()
	{

		Iterator<Individuo> iterador = poblacion.listIterator();
		while( iterador.hasNext() ) {
			Individuo individuoAUsar = (Individuo) iterador.next();
			aplicoFormula(individuoAUsar);
		} 
	}
		
	public void aplicoFormula(Individuo individuo) {
		double aux=(double) individuo.getValor();
		//X3+4X2+2X-4
		double pot3=(double) Math.pow(aux,3);
		double pot2=(double) Math.pow(aux,2);
		double resultado=pot3+(4*pot2)+(2*aux)-4;
		double aptitud = calculoAptitud(resultado);
		individuo.setPorcentaje(aptitud);
	}

	public double calculoAptitud(double resultado) {
		//calculo el porcentaje de semejanza al menor valor
		double aptitud = (double)(resultado*100)/maximoValorFormula;
		return Math.round( ((double)100-aptitud) *100)/100.0d;
		}

	public void ordenoPoblacion()
	{
		Collections.sort(poblacion,new Comparator<Individuo>(){
            public int compare(Individuo indi1, Individuo indi2) {
                return indi1.compare(indi1);
            }});
	}
	
	public void seleccion(int porcentaje)
	{
		int numero= tamanioPoblacion;
		int cantidadAObtener = (int) (numero*porcentaje/100);
		//Si no es par, aumento una unidad para que cuando haga la reproduccion tenga parejas
		if((cantidadAObtener%2)!=0){cantidadAObtener++;}
		
		ArrayList<Individuo> poblacionASeleccionar = new ArrayList<Individuo>();
		System.out.println("Cantidad de seleccion");
		System.out.println(cantidadAObtener);
		for(int y=0;y<cantidadAObtener; y++)
		{
			poblacionASeleccionar.add(poblacion.get(y));
		}
		poblacion.clear();
		poblacion.addAll(poblacionASeleccionar);
	}
	
	public ArrayList<Individuo> repoduccion(int porcentaje)
	{
		int numero= tamanioPoblacion;
		int cantidadAObtener = (int) (numero*porcentaje/100);
				
		System.out.println("Cantidad de Reproduccion");
		System.out.println(cantidadAObtener);
		ArrayList<Individuo> poblacionAReproducir = new ArrayList<Individuo>();
		for(int y=0;y<cantidadAObtener; y++)
		{
			if (poblacion.size()<y)y=0;
			Individuo individuoParaReproduccion1 = poblacion.get(y);
			Individuo individuoParaReproduccion2 = poblacion.get(y+1);
			Individuo unHijo = crearIndividuoPorReproduccion(individuoParaReproduccion1,individuoParaReproduccion2);
			poblacionAReproducir.add(unHijo);
		}
		
		return poblacionAReproducir;
	}
	
	public ArrayList<Individuo> mutacion(int porcentaje)
	{
		int numero= tamanioPoblacion;
		int cantidadAObtener = (int) (numero*porcentaje/100);
				
		System.out.println("Cantidad de mutacion");
		System.out.println(cantidadAObtener);
		ArrayList<Individuo> poblacionAMutar = new ArrayList<Individuo>();
		for(int y=0;y<cantidadAObtener; y++)
		{
			if (poblacion.size()<y)y=0;
			Individuo individuoParaMutar = poblacion.get(y);
			individuoParaMutar.mutar();
			poblacionAMutar.add(individuoParaMutar);
		}
		return poblacionAMutar;
	}
	
	public Individuo crearIndividuoPorReproduccion(Individuo uno, Individuo dos)
	{
		double valorPromedio =  Math.round(((double)(uno.getValor()+dos.getValor())/2)*100)/100.0d;
		Individuo unoNuevo = new Individuo(valorPromedio);
		return unoNuevo;
	}
	
	public void mostrarElMejor()
	{
		System.out.println("######################################################");
		System.out.print("Valor: ");
		System.out.print(poblacion.get(0).getValor());
		System.out.print("  Porcentaje de Aptitud: ");
		System.out.print(poblacion.get(0).getPorcentaje());
		System.out.println(" % ");
		System.out.println("######################################################");
	}
	
	public void agregarPoblacion(ArrayList<Individuo> indiAgregar)
	{
		this.poblacion.addAll(indiAgregar);
	}
	
	public int porcentajeMasAlto()
	{
		return (int) this.poblacion.get(0).getPorcentaje();
	}
	
	public void imprimirPoblacion()
	{
		Iterator<Individuo> iterador = poblacion.listIterator();
		while( iterador.hasNext() ) {
			Individuo individuoAUsar = (Individuo) iterador.next();
			System.out.println("------------------");
			System.out.print("Valor de Evaluacion: ");
	         System.out.print(individuoAUsar.getValor());
	         System.out.print(" Porcentaje de Aptitud: ");
	         System.out.print(individuoAUsar.getPorcentaje());
	         System.out.println(" %");
	         
		} 
		
	}

}
