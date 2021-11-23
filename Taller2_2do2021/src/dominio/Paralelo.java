package dominio;

public class Paralelo {
	private int numeroParalelo;
	private Asignatura codigoAsignatura;
	private Profesor profesor;
	
	public Paralelo(int numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
		codigoAsignatura = null;
		profesor = null;
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

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	

}
