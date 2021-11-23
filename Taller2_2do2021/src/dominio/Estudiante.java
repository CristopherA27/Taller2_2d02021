package dominio;

public class Estudiante extends Persona{
	private int nivelAlumno;

	public Estudiante(String rut, String correo, String contraseña,int nivelAlumno) {
		super(rut, correo, contraseña);
		nivelAlumno =0;
	}

	public int getNivelAlumno() {
		return nivelAlumno;
	}

	public void setNivelAlumno(int nivelAlumno) {
		this.nivelAlumno = nivelAlumno;
	}

}
