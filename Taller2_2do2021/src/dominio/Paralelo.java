package dominio;
import logica.*;

import logica.ListaPersonas;

public class Paralelo {
	private int numeroParalelo;
	private String asignatura;
	private String rutProfesor;
	private ListaPersonas listaEstudiantes;
	private int cantEstudiantes;
	
	public Paralelo(int numeroParalelo,String asignatura,String rutProfesor) {
		this.numeroParalelo = numeroParalelo;
		this.asignatura = asignatura;
		this.rutProfesor = rutProfesor;
		cantEstudiantes =0;
		listaEstudiantes = new ListaPersonas(100);
	}

	public int getNumeroParalelo() {
		return numeroParalelo;
	}
	public void setNumeroParalelo(int numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
	}
	
	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String getRutProfesor() {
		return rutProfesor;
	}

	public void setRutProfesor(String rutProfesor) {
		this.rutProfesor = rutProfesor;
	}

	public int getCantEstudiantes() {
		return cantEstudiantes;
	}

	public void setCantEstudiantes(int cantEstudiantes) {
		this.cantEstudiantes = cantEstudiantes;
	}
	
	public ListaPersonas getListaEstudiantes() {
		return listaEstudiantes;
	}
	
	public void setListaEstudiantes(ListaPersonas listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

	
}
