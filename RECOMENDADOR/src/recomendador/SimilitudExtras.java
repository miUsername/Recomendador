package recomendador;

import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasBasicos;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasFinca;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasOtros;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.InContextLocalSimilarityFunction;

public class SimilitudExtras extends InContextLocalSimilarityFunction{
	public String parametro;
	
	public SimilitudExtras(String parametro) {
		this.parametro=parametro;
	}

	@Override
	public double compute(Object o1, Object o2)throws NoApplicableSimilarityFunctionException {
		DescripcionVivienda dva = (DescripcionVivienda) this.componentOfQuery;
		DescripcionVivienda dvb = (DescripcionVivienda)this.componentOfCase;
		if (o1 != null && o2!=null){
			if (parametro == "extrasOtros"){
				ExtrasOtros dv1 = dva.getExtrasOtros();	    	
				ExtrasOtros dv2 = dvb.getExtrasOtros();
				double res = 0;
				if(dv1.isPatio() && dv2.isPatio()) res++;
				if(dv1.isBalcon() && dv2.isBalcon()) res++;
				if(dv1.isZonaDeportiva() && dv2.isZonaDeportiva()) res++;
				if(dv1.isZonaComunitaria() && dv2.isZonaComunitaria()) res++;
				if(dv1.isTerraza() && dv2.isTerraza()) res++;
				if(dv1.isPiscinaComunitaria() && dv2.isPiscinaComunitaria()) res++;
				if(dv1.isPiscina() && dv2.isPiscina()) res++;
				if(dv1.isJardinPrivado() && dv2.isJardinPrivado()) res++;
				if(dv1.isZonaInfantil() && dv2.isZonaInfantil()) res++;
				return res/9;
			}else if(parametro == "extrasBasicos"){
				ExtrasBasicos dv1 = dva.getExtrasBasicos();	    	
				ExtrasBasicos dv2= dva.getExtrasBasicos();
				double res = 0;
				if(dv1.isLavadero() && dv2.isLavadero()) res++;
				if(dv1.isInternet() && dv2.isInternet()) res++;
				if(dv1.isMicroondas() && dv2.isMicroondas()) res++;
				if(dv1.isHorno() && dv2.isHorno()) res++;
				if(dv1.isAmueblado() && dv2.isAmueblado()) res++;
				if(dv1.isCocinaOffice() && dv2.isCocinaOffice()) res++;
				if(dv1.isParquet() && dv2.isParquet()) res++;
				if(dv1.isDomotica() && dv2.isDomotica()) res++;
				if(dv1.isArmarios() && dv2.isArmarios()) res++;
				if(dv1.isTv() && dv2.isTv()) res++;
				if(dv1.isLavadora() && dv2.isLavadora()) res++;
				if(dv1.isElectrodomesticos() && dv2.isElectrodomesticos()) res++;
				if(dv1.isSuiteConBanio() && dv2.isSuiteConBanio()) res++;
				if(dv1.isPuertaBlindada() && dv2.isPuertaBlindada()) res++;
				if(dv1.isGresCeramica() && dv2.isGresCeramica()) res++;
				if(dv1.isCalefaccion() && dv2.isCalefaccion()) res++;
				if(dv1.isAireAcondicionado() && dv2.isAireAcondicionado()) res++;
				if(dv1.isNevera() && dv2.isNevera()) res++;
				return res/18;
			}else if(parametro == "extrasFinca"){
				ExtrasFinca dv1 = dva.getExtrasFinca();
				ExtrasFinca dv2= dvb.getExtrasFinca();
				double res = 0;
				if(dv1.isAscensor() && dv2.isAscensor()) res++;
				if(dv1.isTrastero() && dv2.isTrastero()) res++;
				if(dv1.isEnergiaSolar() && dv2.isEnergiaSolar()) res++;
				if(dv1.isServPorteria() && dv2.isServPorteria()) res++;
				if(dv1.isParkingComunitario() && dv2.isParkingComunitario()) res++;
				if(dv1.isGarajePrivado() && dv2.isGarajePrivado()) res++;
				if(dv1.isVideoportero() && dv2.isVideoportero()) res++;
				return res/7;			
			}
		}
		
		return 0;
	}

	@Override
	public boolean isApplicable(Object caseObject, Object queryObject) {
		return true;
	}
	
}
