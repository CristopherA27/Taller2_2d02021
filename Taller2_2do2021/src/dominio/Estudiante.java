package dominio;
import logica.*;

public class Estudiante extends Persona{
	private int nivelAlumno;
	private int creditos ;
	private ListaAsignaturas asignaturasCursadas;
	private ListaAsignaturas asignaturasInscritas;

	public Estudiante(String rut, String correo, String contraseña,int nivelAlumno) {
		super(rut, correo, contraseña);
		nivelAlumno =0;
		asignaturasCursadas = new ListaAsignaturas(100);
		asignaturasInscritas = new ListaAsignaturas(100);
		creditos =0;
	}

	public int getCreditos() {
		return creditos;
	}


	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}


	public int getNivelAlumno() {
		return nivelAlumno;
	}

	public void setNivelAlumno(int nivelAlumno) {
		this.nivelAlumno = nivelAlumno;
	}
	public ListaAsignaturas getAsignaturasCursadas() {
		return asignaturasCursadas;
	}

	public ListaAsignaturas getAsignaturasInscritas() {
		return asignaturasInscritas;
	}

	
	

}
