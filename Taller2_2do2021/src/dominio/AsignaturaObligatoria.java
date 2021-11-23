package dominio;

import logica.*;

public class AsignaturaObligatoria extends Asignatura{
	
	private int nivelMalla;
	private ListaAsignaturas listaAsignaturas;

	public AsignaturaObligatoria(String codigoAsignatura, String nombreAsignatura, int cantCreditos,int nivelMalla) {
		super(codigoAsignatura, nombreAsignatura, cantCreditos);
		this.nivelMalla = nivelMalla;
		listaAsignaturas = new ListaAsignaturas(100);
	}

	public int getNivelMalla() {
		return nivelMalla;
	}

	public void setNivelMalla(int nivelMalla) {
		this.nivelMalla = nivelMalla;
	}

	public ListaAsignaturas getListaAsignaturas() {
		return listaAsignaturas;
	}

	public void setListaAsignaturas(ListaAsignaturas listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
	}
	
	
	
}
