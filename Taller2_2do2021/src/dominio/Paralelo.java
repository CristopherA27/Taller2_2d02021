package dominio;
import logica.*;

import logica.ListaPersonas;

public class Paralelo {
	private int numeroParalelo;
	private Asignatura codigoAsignatura;
	private Profesor rutProfesor;
	private ListaPersonas listaEstudiantes;
	private int cantEstudiantes;
	
	public Paralelo(int numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
		codigoAsignatura = null;
		rutProfesor = null;
		cantEstudiantes =0;
		listaEstudiantes = new ListaPersonas(100);
	}

	public int getNumeroParalelo() {
		return numeroParalelo;
	}
	public void setNumeroParalelo(int numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
	}

	public Asignatura getCodigoAsignatura() {
		return codigoAsignatura;
	}

	public void setCodigoAsignatura(Asignatura codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}
	
	public int getCantEstudiantes() {
		return cantEstudiantes;
	}

	public void setCantEstudiantes(int cantEstudiantes) {
		this.cantEstudiantes = cantEstudiantes;
	}

	public void setListaEstudiantes(ListaPersonas listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

	public Profesor getRutProfesor() {
		return rutProfesor;
	}

	public void setRutProfesor(Profesor rutProfesor) {
		this.rutProfesor = rutProfesor;
	}

	public ListaPersonas getListaEstudiantes() {
		return listaEstudiantes;
	}
}
