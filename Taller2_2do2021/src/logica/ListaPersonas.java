package logica;
import dominio.*;

public class ListaPersonas {
	private int cant;
	private int max;
	private Persona [] listaPersonas;
	
	public ListaPersonas(int max) {
		cant =0;
		this.max = max;
		listaPersonas = new Persona[max];
	}
	
	public int getCant() {
		return cant;
	}
	
	public boolean ingresar(Persona persona) {
		if(cant<max) {
			listaPersonas[cant] = persona;
			cant++;
			return true;
		}
		return false;
	}
	
	public Persona buscar(String rut) {
		for(int i=0;i<cant;i++) {
			if(listaPersonas[i].getRut().equals(rut)) {
				return listaPersonas[i];
			}
		}
		return null;
	}
	
	public boolean eliminar(String rut) {
		int i;
		for(i=0;i<cant;i++) {
			if(listaPersonas[i].getRut().equals(rut)) {
				break;
			}
		}
		if(i==cant) {
			return false;
		}else {
			for(int k=i;k<cant;k++) {
				listaPersonas[k] = listaPersonas[k+1];
			}
			cant--;
			return false;
		}
	}
	
	public Persona getElemento(int posicion) {
		return listaPersonas[posicion];
	}

}
