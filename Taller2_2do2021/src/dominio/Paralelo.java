package dominio;
import logica.*;

import logica.ListaPersonas;

public class Paralelo {
	private int numeroParalelo;
	private Asignatura asignatura;
	private Profesor profesor;
	private ListaPersonas listaEstudiantes;
	private int cantEstudiantes;
	
	public Paralelo(int numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
		asignatura = null;
		profesor = null;
		cantEstudiantes =0;
		listaEstudiantes = new ListaPersonas(100);
	}

	public int getNumeroParalelo() {
		return numeroParalelo;
	}
	public void setNumeroParalelo(int numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
	}
	

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
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


	public ListaPersonas getListaEstudiantes() {
		return listaEstudiantes;
	}
}
