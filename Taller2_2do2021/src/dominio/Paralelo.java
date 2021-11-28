package dominio;

public class Paralelo {
	private int numeroParalelo;
	private Asignatura codigoAsignatura;
	private Profesor rutProfesor;
	
	public Paralelo(int numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
		codigoAsignatura = null;
		rutProfesor = null;
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

	public Profesor getRutProfesor() {
		return rutProfesor;
	}

	public void setRutProfesor(Profesor rutProfesor) {
		this.rutProfesor = rutProfesor;
	}

	

}
