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
	    	
	    	Equal _equal;
	    	EnumDistance _enumDistance;
	    	Interval _interval;
	    	MaxString _maxString;
	    	Rango _rango;
		    	DescripcionVivienda dv1 = (DescripcionVivienda) this.componentOfQuery;	    	
		    	DescripcionVivienda dv2= (DescripcionVivienda)this.componentOfCase;
//		    	if (dv1 != null && dv2!= null){
			        if (parametro == "habitaciones"){
			        	_equal=new Equal();
				    	return _equal.compute(dv1.getHabitaciones(), dv2.getHabitaciones());
				    	}
			       else if (parametro == "precio" && dv1.getPrecio()!=null && dv2.getPrecio()!= null){
			    	   	double r1=0,r2=0,r3=0,r4=0;
			        	_rango = new Rango();
			        	r1=_rango.getRango(dv1.getPrecio(),dv2.getPrecio(),1000);
			        	r2=_rango.getRango(dv1.getPrecio(),dv2.getPrecio(),5000);
			        	r3=_rango.getRango(dv1.getPrecio(),dv2.getPrecio(),10000);
			        	r4=_rango.getRango(dv1.getPrecio(),dv2.getPrecio(),15000);
			        	if (r1 == 1)
			        		return 1;
			        	else if (r2==1)
			        		return 0.8;
			        	else if (r3==1)
			        		return 0.65;
			        	else if (r4==1)
			        		return 0.45;
				    	}
			       else if (parametro == "banios" && dv1.getBanios()!=null && dv2.getBanios()!= null){
			        	if (dv1.getBanios()==dv2.getBanios())
			        		return 1;
			        	else if (dv2.getBanios()-dv1.getBanios()==1)
			        		return 0.8;
			        	else if (dv2.getBanios()-dv1.getBanios()==2)
			        		return 0.5;
			        	return 0;
				    	}
			       else if (parametro == "estado"){
			        	_enumDistance=new EnumDistance();
				    	return _enumDistance.compute(dv1.getEstado(), dv2.getEstado());
				    	}
			       else if (parametro == "precioZona" && dv1.getPrecioZona()!=null && dv2.getPrecioZona()!= null){
			        	double r1=0,r2=0;
			        	_rango = new Rango();
			        	r1=_rango.getRango(dv1.getPrecioZona(),dv2.getPrecioZona(),5000);
			        	r2=_rango.getRango(dv1.getPrecioZona(),dv2.getPrecioZona(),10000);
			        	if (r1 == 1)
			        		return 0.3;
			        	else if (r2==1)
			        		return 0.1;
				    	}
			       else if (parametro == "precioMedio" && dv1.getPrecioMedio()!=null && dv2.getPrecioMedio()!= null){
			        	double r1=0,r2=0;
			        	_rango = new Rango();
			        	r1=_rango.getRango(dv1.getPrecioMedio(),dv2.getPrecioMedio(),5000);
			        	r2=_rango.getRango(dv1.getPrecioMedio(),dv2.getPrecioMedio(),10000);
			        	if (r1 == 1)
			        		return 0.3;
			        	else if (r2==1)
			        		return 0.1;
				    	}
			       else if (parametro == "coordenada" && dv1.getCoordenada()!=null && dv2.getCoordenada()!= null){
			        	double lat=0,longi=0;
			        	_rango = new Rango();
			        	lat=_rango.getRango(dv1.getCoordenada().getLatitud(),dv2.getCoordenada().getLatitud(),100);
			        	longi=_rango.getRango(dv1.getCoordenada().getLongitud(),dv2.getCoordenada().getLongitud(),100);
			        	return (lat+longi)/2;
			        }
			       else if (parametro == "urlFoto" && dv2.getUrlFoto()!=null)
			    	   return 0.9;
			       else if (parametro == "url" && dv2.getUrl()!=null)
			    	   return 0.85;
			      
			        
//		    	}
	    
//	    else if (dv2!=null){
	    	
			       else if (parametro == "descripcion"){
			        	_maxString=new MaxString();
					    	return _maxString.compute(dv1.getDescripcion(), dv2.getDescripcion());
			        	}
			       else if (parametro == "localizacion"){
			        	_maxString=new MaxString();
					    	return _maxString.compute(dv1.getLocalizacion(), dv2.getLocalizacion());
			        	}
	        
	//    }

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
