import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AlgoritmoGenetico {

	public static void main(String[] args) throws IOException {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("############################################");
		System.out.println("Tamanio de la poblacion a Evaluar");
		String str = in.readLine();
		int tamanio = Integer.parseInt(str);
		System.out.println("Porcentaje de Aptitud Aceptado");
		String apt = in.readLine();
		int pctApt = Integer.parseInt(apt);
		
		System.out.println("Porcentaje de Seleccion a Aplicar");
		String slc = in.readLine();
		int pctSlc = Integer.parseInt(slc);
		System.out.println("Porcentaje de Reproduccion a Aplicar");
		String reprod = in.readLine();
		int pctReprod = Integer.parseInt(reprod);
		System.out.println("Porcentaje de Mutacion a Aplicar");
		String mutac = in.readLine();
		int pctMutac = Integer.parseInt(mutac);
		
		
		
		int pctCargaUsuario= pctApt;
		Population pobla = new Population();
		System.out.println("###########################################Populacion###########################################");
		pobla.generarPoblacion(tamanio);
		pobla.obtenerResultadoFormula();
		pobla.imprimirPoblacion();
		pobla.ordenoPoblacion();
		
		System.out.println("###########################################Seleccion###########################################");
		pobla.seleccion(pctSlc);
		
		pobla.imprimirPoblacion();
		System.out.println("###########################################Reproduccion###########################################");
		ArrayList<Individuo> salidaReproduccion1 = pobla.repoduccion(pctReprod);
		ArrayList<Individuo> salidaMutacion1 = pobla.mutacion(pctMutac);
		pobla.agregarPoblacion(salidaReproduccion1);
		pobla.obtenerResultadoFormula();
		
		pobla.imprimirPoblacion();
		pobla.agregarPoblacion(salidaMutacion1);
		pobla.obtenerResultadoFormula();
		System.out.println("###########################################Mutacion###########################################");
		pobla.imprimirPoblacion();
		
		int x =1;
		while (x<pobla.maximoDeVueltas() && pctCargaUsuario>=pobla.porcentajeMasAlto())
		{
			pobla.ordenoPoblacion();
			System.out.println("############################################");
			System.out.print("VUELTA NUMERO: ");
			System.out.println(x);
			System.out.print("Porcentaje Hasta El Momento: ");
			System.out.print(pobla.porcentajeMasAlto());
			System.out.println(" %");
			System.out.println("############################################");
			
			System.out.println("###########################################Seleccion###########################################");
			pobla.seleccion(pctSlc);
			
			pobla.imprimirPoblacion();
			ArrayList<Individuo> salidaReproduccion = pobla.repoduccion(pctReprod);
			ArrayList<Individuo> salidaMutacion = pobla.mutacion(pctMutac);
			pobla.agregarPoblacion(salidaReproduccion);
			pobla.obtenerResultadoFormula();
			System.out.println("###########################################Reproduccion###########################################");
			pobla.imprimirPoblacion();
			pobla.agregarPoblacion(salidaMutacion);
			pobla.obtenerResultadoFormula();
			System.out.println("###########################################Mutacion###########################################");
			pobla.imprimirPoblacion();
			x++;
		}
		pobla.mostrarElMejor();
		
		
		

	}

}
