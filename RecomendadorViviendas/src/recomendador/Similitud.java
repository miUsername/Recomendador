package recomendador;


import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.method.retrieve.NNretrieval.similarity.InContextLocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString;

//nos basamos en el iguales
public class Similitud extends InContextLocalSimilarityFunction { 
		public String parametro;
		
		public Similitud(String parametro){
			this.parametro=parametro;
		}
		
		
		/** 
		 * SIMILARITY.LOCAL
 * Equal(): This function returns 1 if both individuals are equal, otherwise returns 0
 * EnumCyclicDistance(): This function computes the similarity between two enum values as their cyclic distance.
 * EnumDistance(): This function returns the similarity of two enum values as the their distance sim(x,y)=|ord(x) - ord(y)|
 * EqualsStringIgnoreCase(): This function returns 1 if both String are the same despite case letters, 0 in the other case
 * Interval():  This function returns the similarity of two number inside an interval sim(x,y)=1-(|x-y|/interval)
 * MaxString(): This function returns a similarity value depending of the biggest substring that belong to both strings.
 * Table(): Similarity function that uses a table to obtain the similarity between two values. 
 * 			Allowed values are Strings or Enums. 
 * 			The table is read from a text file with the following format:
				 * <ul>
				 * <li>1st line: coma separated n categories
				 * <li>following n lines: n double values separated by comma. 
				 * </ul> 
	Threshold(): This function returns 1 if the difference between two numbers is less than a threshold, 0 in the other case.
	SIMILARITY.LOCAL.RECOMMENDER
	InrecaLessIsBetter(): This function returns the similarity of two numbers (or enums) following the INRECA - Less is Better formula
 *							sim(c.a,q.a)= if(c.a < q.a) then 1 else  jump (max(a) - c.a) / (max(a) - q.a)
 * 							jump and max(a) must be defined by the designer.
 * InrecaMoreIsBetter():This function returns the similarity of two numbers (or Enums) following the INRECA - More is Better formulae
 * 							sim(c.a,q.a)= if(c.a > q.a) then 1 else  jump * (1- (q.a - c.a) / q.a))
 * 							jump must be defined by the designer.
 * McSherryLessIsBetter():This function returns the similarity of two numbers following the McSherry - Less is Better formulae
 * 							sim(c.a,q.a)= (max(a) - c.a) / (max(a)-min(a))
 * 							min(a) and max(a) must be defined by the designer. q.a is not taken into account.
 * McSherryMoreIsBetter(): This function returns the similarity of two numbers following  the McSherry - More is Better formulae
							 sim(c.a,q.a)= 1 - ((max(a) - c.a) / (max(a)-min(a)))
							 min(a) and max(a) must be defined by the designer. q.a is not taken into account.
 */
		
	    public double compute(Object o1, Object o2) throws jcolibri.exception.NoApplicableSimilarityFunctionException{

	    		/**
		         * componentOfCase:component of the case that this attribute belongs to
		         * componentOfQuery:component of the query that this attribute belongs to
		         * 
		         */
		    	
		    	DescripcionVivienda dv1 = (DescripcionVivienda) this.componentOfQuery;	    	
		    	DescripcionVivienda dv2= (DescripcionVivienda)this.componentOfCase;
		    	if (dv1 != null){
			    	Equal _equal;
			    	EnumDistance _enumDistance;
			    	Interval _interval;
			    	MaxString _maxString;
			        if (parametro == "habitaciones"){
			        	_equal=new Equal();
				    	return _equal.compute(dv1.getHabitaciones(), dv2.getHabitaciones());
				    	}
			       else if (parametro == "precio"){
			        	if (dv1.getPrecio()<=dv2.getPrecio())
			        		return 1;
				    	}
			       else if (parametro == "banios"){
			        	if (dv1.getBanios()>=dv2.getBanios())
			        		return 1;
			        	else return 0;
				    	}
			        if (parametro == "descripcion"){
			        	_maxString=new MaxString();
					    	return _maxString.compute(dv1.getDescripcion(), dv2.getDescripcion());
			        	}
			        if (parametro == "localizacion"){
			        	_maxString=new MaxString();
					    	return _maxString.compute(dv1.getLocalizacion(), dv2.getLocalizacion());
			        	}
			        if (parametro == "estado"){
			        	_enumDistance=new EnumDistance();
				    	return _enumDistance.compute(dv1.getEstado(), dv2.getEstado());
				    	}
			        if (parametro == "precioZona"){
			        	if (dv1.getPrecioZona()<=dv2.getPrecioZona())
			        		return 1;
				    	}
			        if (parametro == "precioMedio"){
			        	if (dv1.getPrecioMedio()<=dv2.getPrecioMedio())
			        		return 1;
				    	}
			        if (parametro == "coordenada"){
			        	double lat=0,longi=0;
			        	if ((dv1.getCoordenada().getLatitud()-100<=dv2.getCoordenada().getLatitud())
			        			&& (dv1.getCoordenada().getLatitud()+100>=dv2.getCoordenada().getLatitud())){
			        		lat=0.5;
				    	}
			        if ((dv1.getCoordenada().getLongitud()-100<=dv2.getCoordenada().getLongitud())
		        			&& (dv1.getCoordenada().getLongitud()+100>=dv2.getCoordenada().getLongitud())){
		        		longi=0.5;
			    		}
			        return lat+longi;
			        }
		    	}
	    
	    else if (dv2!=null){
	    	if (parametro == "urlFoto"){
			       	return 1;       
		     	}
	    	else if (parametro == "descripcion"){
	    		return 1;  
        	}
	    }

       /* 	        else if (parametro == "localizacion"){
	        	_maxString=new MaxString();
		    	return _maxString.compute(valor1, valor2);
		    	}
	        
	        
	        else if (parametro == "titulo"){
	        	_maxString=new MaxString();
		    	return _maxString.compute(valor1, valor2);
		    	}
	        else if (parametro == "coordenada"){
	        	return 0;/*
	        	_interval=new Interval(100);
		    	return _interval.compute(valor1, valor2);
		    	*/
	/*	    	}
	/*        else if (parametro == "precioMedio"){
	        	_interval=new Interval(100);
		    	return _interval.compute(valor1, valor2);
		    	}
	        else if (parametro == "precioZona"){
	        	_interval=new Interval(100);
		    	return _interval.compute(valor1, valor2);
		    	}
	        else if (parametro == "extrasBasicos"){
	        	return 0;/*
	        	_maxString=new MaxString();
		    	return _maxString.compute(valor1, valor2);
		    	*/
	/*	    	}
	/*        else if (parametro == "extrasFinca"){
	        	return 0;/*
	        }
	        	_maxString=new MaxString();
		    	return _maxString.compute(valor1, valor2);
		    	*/
/*		    	}
			else if (parametro == "extrasOtros"){
	        	return 0;/*
	        	_maxString=new MaxString();
		    	return _maxString.compute(valor1, valor2);
		    	*/
/*		    	}
/*	        else if (parametro == "descripcion"){
	        	_maxString=new MaxString();
		    	return _maxString.compute(valor1, valor2);
		    	}
	        else if (parametro == "url"){
		    	return 0;
		    	}
	        
	*/       
	    	
			return 0;
	    	
	       
	    }
	    
		public boolean isApplicable(Object o1, Object o2){
			return true;
		}

}
