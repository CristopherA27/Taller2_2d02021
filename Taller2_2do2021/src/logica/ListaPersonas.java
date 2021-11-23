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
	
	

}
