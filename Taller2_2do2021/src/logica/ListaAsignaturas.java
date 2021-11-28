package logica;
import dominio.*;


public class ListaAsignaturas {
	private int cant;
	private int max;
	private Asignatura [] listaAsignaturas;
	
	public ListaAsignaturas(int max) {
		cant =0;
		this.max = max;
		listaAsignaturas = new Asignatura[max];
	}
	
	public int getCant() {
		return cant;
	}
	
	public boolean ingresar(Asignatura asignatura) {
		if(cant<max) {
			listaAsignaturas[cant] = asignatura;
			cant++;
			return true;
		}
		return false;
	}
	
	public Asignatura buscar(String codigoAsignatura) {
		for(int i=0;i<cant;i++) {
			if(listaAsignaturas[i].getCodigoAsignatura().equals(codigoAsignatura)) {
				return listaAsignaturas[i];
			}
		}
		return null;
	}
	
	public boolean eliminar(String codigoAsignatura){
		int i;
		for(i=0;i<cant;i++){
			if(listaAsignaturas[i].getCodigoAsignatura().equals(codigoAsignatura)){
				break;
			}
		}
		if(i==cant){
			return false;
		}
		else{
			for(int k=i;k<cant;k++){
				listaAsignaturas[k]=listaAsignaturas[k+1];
			}
			cant--;
			return true;
		}
	}
	
	public Asignatura getElementoI(int posicion) {
		return listaAsignaturas[posicion];
	}    
	
	
}
