package dominio;

import logica.*;

public class AsignaturaObligatoria extends Asignatura{
	
	private int nivelMalla;
	private int cantAsignaturasPrerrequisito;
	private ListaAsignaturas listaAsignaturas;

	public AsignaturaObligatoria(String codigoAsignatura, String nombreAsignatura, int cantCreditos,int nivelMalla,int cantAsignaturasPrerrequisito) {
		super(codigoAsignatura, nombreAsignatura, cantCreditos);
		this.nivelMalla = nivelMalla;
		listaAsignaturas = new ListaAsignaturas(cantAsignaturasPrerrequisito);
	}

	public int getNivelMalla() {
		return nivelMalla;
	}

	public void setNivelMalla(int nivelMalla) {
		this.nivelMalla = nivelMalla;
	}
	

	public int getCantAsignaturasPrerrequisito() {
		return cantAsignaturasPrerrequisito;
	}

	public void setCantAsignaturasPrerrequisito(int cantAsignaturasPrerrequisito) {
		this.cantAsignaturasPrerrequisito = cantAsignaturasPrerrequisito;
	}

	public ListaAsignaturas getListaAsignaturas() {
		return listaAsignaturas;
	}

	public void setListaAsignaturas(ListaAsignaturas listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
	}
	
	
	
}
