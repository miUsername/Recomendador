package recomendador;

public class Rango {
	public Rango(){}
	
	public double getRango(double d, double e, int diferencia){
		if ((d-diferencia<=e) && (d+diferencia>=e)){
			return 1;
		}
		return 0;
	}

}
