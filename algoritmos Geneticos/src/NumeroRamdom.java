
public class NumeroRamdom {
	private int maxRandom=50;
	private double valor;
	public NumeroRamdom()
	{
		valor= (double) Math.round((Math.random()*maxRandom)*100)/100.0d;
	}
	public double getRamdom()
	{
		return valor;
	}

}
