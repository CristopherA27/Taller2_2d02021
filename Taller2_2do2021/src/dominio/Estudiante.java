package dominio;

public class Estudiante extends Persona{
	private int nivelAlumno;

	public Estudiante(String rut, String correo, String contraseņa,int nivelAlumno) {
		super(rut, correo, contraseņa);
		nivelAlumno =0;
	}

	public int getNivelAlumno() {
		return nivelAlumno;
	}

	public void setNivelAlumno(int nivelAlumno) {
		this.nivelAlumno = nivelAlumno;
	}

}
