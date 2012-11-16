
public class Individuo implements Comparable<Individuo>{
	private double valorParaOperar, porcentajeAptitud;
	
	public Individuo(double valor)
	{
		valorParaOperar=valor;
	}
	
	public void setPorcentaje(double porcentaje)
	{
		porcentajeAptitud=porcentaje;
	}
	
	public void setValor(double valor)
	{
		valorParaOperar=valor;
	}
	
	public double getValor()
	{
		return valorParaOperar;
	}
	
	public double getPorcentaje()
	{
		return porcentajeAptitud;
	}
	
	public void mutar()
	{
		double valor = this.getValor();
		double ramd;
		//Le agrego un minimo al random dado que sino decrece rapidamente
		do{
			ramd=Math.random();
		}while(ramd<0.8);
		valor= Math.round( (valor*ramd) *100)/100.0d;
		this.setValor(valor);
	}
	
	public int compareTo(Individuo indiAComparar)
	{
		int res=0;
		if(this.getPorcentaje()>indiAComparar.getPorcentaje()) res=-1;
		else
		if(this.getPorcentaje()<indiAComparar.getPorcentaje()) res=1;
		else
		if(this.getPorcentaje()==indiAComparar.getPorcentaje()) res=0;
		return res;
	}
	


}
