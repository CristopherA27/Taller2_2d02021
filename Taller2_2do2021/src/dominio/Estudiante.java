package dominio;

public class Estudiante extends Persona{
	private int nivelAlumno;

	public Estudiante(String rut, String correo, String contrase�a,int nivelAlumno) {
		super(rut, correo, contrase�a);
		nivelAlumno =0;
	}

	public int getNivelAlumno() {
		return nivelAlumno;
	}

	public void setNivelAlumno(int nivelAlumno) {
		this.nivelAlumno = nivelAlumno;
	}

}
